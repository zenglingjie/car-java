$(function () {
    $("#test1").val('2016-01-01');
    $("#test2").val('2016-03-03');
})

var testStrategy = new function () {
    var xData = [];
    var rData = [];
    var bData = [];
    var dataLen =0;
    var rChart = null;
    var option = null;
    initChart();
    var editor = null;
    if ( document.getElementById('editor') != null) {
        editor =  initAce("editor");
    }
    var getRun  = new GetRun();
    var ws_url = getUrl();
    var ws;
    var sock = connect();

    function GetRun() {
        var isRun = false;
        function openRun(){
            isRun = true;
        };
        function closeRun(){
            isRun = false;
        }
        function isRun(){
            return isRun;
        }
        return {
            closeRun : function(){
                $(".loading").hide();
                closeRun();
            },
            run: function(type) {
                if(isRun){
                    console.log("程序正在运行，请运行完成后再试");
                    return;
                }
                initChart();
                openRun();
                var codeStr = '';
                if (type == 2) {
                    codeStr = editor.getValue();
                } else {
                    codeStr = getBackTestJson();
                }
                var id = $("#sid").val();
                var name = $("#sname").val();
                var startDate = $("#test1").val();
                var endDate = $("#test2").val();

                var data ={"codeStr" : codeStr,
                           "id" : id,
                           "type": type,
                           "name": name,
                           "startDate": startDate,
                           "endDate": endDate
                           };
                send(data);


            }
        }
    }
    function getUrl() {
        var ret;
        if (window.location.protocol == 'https:') {
            ret = 'wss://' + weburl + '/websocket?user='+ $("#user").val() + '&straId=' + $("#sid").val();
        } else {
            ret = 'ws://' + weburl + '/websocket?user='+ $("#user").val() + '&straId=' + $("#sid").val();
        }
        return ret;
    }
    function connect() {
        if (!window.WebSocket) {
             if (window.MozWebSocket) {
                 window.WebSocket = window.MozWebSocket;
             } else {
                 alert("browser doesn't support WebSockets.");
                 return;
             }
        }
        ws = new WebSocket(ws_url);
        ws.onopen = function () {
            console.log('Info: connection opened.');
        };

        ws.onmessage = function (event) {
            // 进度条
            dataLen++;
            //var days = (new Date(endDate).getTime() - new Date(startDate).getTime()) / (24 * 60 * 60 * 1000);
            var days = 10;
            var propress = (dataLen / days).toFixed(2);
            $(".loading .progress-bar").width(propress + "%").html(propress + "%");
            var text = event.data;
            var strArray = text.toString().split("EEE");
            if(strArray.length === 2) {
                if (strArray[0] === "LOG") {
                    $("#log").text(strArray[1]);
                } else if (strArray[0] === "ELOG"){
                    $("#errorlog").text(strArray[1]);
                }
            }else {
                var d = text.replace(/NaN/g, 0).replace(/Infinity/g, 0);
                var json = $.parseJSON(d);
                switch (json.type) {
                        case "curve":
                            console.log(new Date().toLocaleString() + ' Received: part');
                            drawStrategyPartChart(json);

                            break;
                        case "summary":
                            var data = json.data;
                            $("#total_returns").text(Number(data.total_returns * 100).toFixed(2) + "%");
                            $("#benchmark_total_returns").text(Number(data.benchmark_annualized_returns * 100).toFixed(2) + "%");
                            $("#max_withdraw").text(Number(data.max_drawdown * 100).toFixed(2) + "%");
                            $("#beta").text(data.beta);
                            $("#alpha").text(data.alpha);
                            $("#sharpe").text(data.sharpe);

                            break;
                        case "testid":
                            var data = json.data;
                            $("#bid").val(data.bid);
                            $("#sid").val(data.sid);
                            getRun.closeRun();
                            break;
                        default:
                            alert("key is error!")
                    }
            }
        };
        ws.onclose = function (event) {

            $(".loading").hide();
            console.log('Info: connection closed.');
            console.log(event);
            console.log(ws);
        };
    }
    function disconnect() {
            if (ws != null) {
                ws.close();
                ws = null;
            }
        };
    function send(d) {
        	if (ws != null) {
        		$("#log1").empty();
        		$("#log2").empty();
        		logLen = 0;
        		chartLen = -1;
        		$(".loading").show();
        		$(".loading .progress-bar").width("0.00%").html("0.00%");
        		var startDate = $('#test1').val();
                var endDate = $('#test2').val();
                var jsonStr = JSON.stringify(d);
        		console.log(new Date().toLocaleString() + ' Sent: ' + jsonStr);
        		if(ws.readyState != 1){
        			ws.close();
        			connect();
        			setTimeout(function(){
        				ws.send(jsonStr);
        			},300)
        		}else{
        			ws.send(jsonStr);
        		}

        	} else {
        		alert('please connect again.');
        	}
        }
    function getRun() {
        return new GetRun();
    }
    function drawStrategyPartChart(json) {
        xData.push(json.data.dt);
        bData.push(json.data.Roi);
        rData.push(json.data.benchRoi);
        option.xAxis[0].data = xData;
        option.xAxis[1].data = xData;
        option.series[0].data = rData; // 设置图表
        option.series[1].data = bData; // 设置图表
        rChart.setOption(option);// 重新加载图表
    }
    function initChart() {
       xData.length = 0;
       rData.length = 0;
       bData.length = 0;
       rChart = echarts.init(document.getElementById('data-chart'));
       var vu = {};
       vu.xTimeData = xData;
       vu.roiData = rData;
       vu.benchRoiData = bData;
       option = getOption(vu);
       rChart.setOption(option);
    }
    function testHistory(type) {
        var id = $("#sid").val();
        window.location.href="history.html?sid=" + id +"&type="+ type;
    }
    function testResults() {
        var id = $("#bid").val();
        window.location.href="testResults.html?bid=" + id;
    }
    return {
        getRun: getRun,
        testResults: testResults,
        testHistory: testHistory
    }
}
