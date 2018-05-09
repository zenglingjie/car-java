//策略生成器-查看快照
var snapshotArray = [];
function snapshot(param) {
    $.ajax({
        url : hosturl + "/select/snapshot", // 后台处理接口
        timeout : 15000,
        type : "get", // 数据接收方式
        async : false,
        data : {
            "id" : param
        },
        dataType : "json", // 接受数据格式
        success : function(data) {
            $.each(data.list, function(snapshotKey,snapshotValue) {
                var itemSnapshot = snapshotValue.snapshot.split("\"");
                if(itemSnapshot[0] === "{") {
                    var snapshotJson = $.parseJSON(snapshotValue.snapshot);                        
                    $.each(snapshotJson, function (k, v) {
                        var transactionSet = $("#creatStrategy_cont1 h2 span").html();
                        var stockPool = $("#creatStrategy_cont2 h2 span").html();
                        var chooseCondition = $("#creatStrategy_cont3 h2 span").html();
                        var sortOrder = $("#creatStrategy_cont4 h2 span").html();
                        var stocksStop = $("#creatStrategy_cont5 h2 span").html();
                        var marketstop = $("#creatStrategy_cont6 h2 span").html();
                        var snapshotContent1 = $("#creatStrategy_cont1 .creat_select");
                        var snapshotContent2 = $("#creatStrategy_cont2 .creat_select");
                        var snapshotContent3 = $("#creatStrategy_cont3 .creat_table tbody");
                        var snapshotContent4 = $("#creatStrategy_cont4 .creat_table tbody");
                        var snapshotContent5 = $("#creatStrategy_cont5 .creat_table tbody");
                        var snapshotContent6 = $("#creatStrategy_cont6 .creat_table tbody");
                        var snapshotHtml1 = "";
                        var snapshotHtml2 = "";
                        var snapshotHtml3 = "";
                        var snapshotHtml4 = "";
                        var snapshotHtml5 = "";
                        var snapshotHtml6 = "";
//                      if (snapshotArray.indexOf(v) == -1) {
                            for (var i = 0; i < v.length; i++) {
                                if (k == transactionSet) {
                                    snapshotHtml1 += '<ul>'
                                        + '<li class="creat_select_li">'
                                        + '<p>基准指数</p>'
                                        + '<div class="nice_select nice_selects">'
                                        + '<input value = ' + v[i].liData1 + ' readonly >'
                                        + '</div>'
                                        + '</li>'
                                        + '<li class="creat_select_li">'
                                        + '<p>持仓数量</p>'
                                        + '<div class="nice_select nice_selects">'
                                        + '<input value = ' + v[i].liData2 + ' readonly>'
                                        + '</div>'
                                        + '</li>'
                                        + '<li class="creat_select_li">'
                                        + '<p>加权方式</p>'
                                        + '<div class="nice_select nice_selects">'
                                        + '<input value = ' + v[i].liData3 + ' readonly>'
                                        + '</div>'
                                        + '</li>'
                                        + '<li class="creat_select_li">'
                                        + '<p>交易价格</p>'
                                        + '<div class="nice_select nice_selects">'
                                        + '<input value = ' + v[i].liData4 + ' readonly>'
                                        + '</div>'
                                        + '</li>'
                                        + '<li class="creat_select_li">'
                                        + '<p>调仓周期</p>'
                                        + '<div class="nice_select nice_selects">'
                                        + '<input value = ' + v[i].liData5 + ' readonly>'
                                        + '</div>'
                                        + '</li>'
                                        + '<li class="creat_select_li">'
                                        + '<p>调仓跨度</p>'
                                        + '<div class="nice_select nice_selects">'
                                        + '<input value = ' + v[i].liData6 + ' readonly>'
                                        + '</div>'
                                        + '</li>'
                                        + '<li class="creat_select_li">'
                                        + '<p>调仓日序号</p>'
                                        + '<div class="nice_select nice_selects">'
                                        + '<input value = ' + v[i].liData7 + ' readonly>'
                                        + '</div>'
                                        + '</li>'
                                        + '</ul>'
                                } else if (k == stockPool) {
                                    snapshotHtml2 += '<ul>'
                                    snapshotHtml2 += '<li class="creat_select_li">'
                                    snapshotHtml2 += '<p>指数成分</p>'
                                    snapshotHtml2 += '<div class="nice_select nice_selects">'
                                    snapshotHtml2 += '<input value = ' + v[i].liData1 + ' readonly >'
                                    snapshotHtml2 += '</div>'
                                    snapshotHtml2 += '</li>'
                                    snapshotHtml2 += '<li class="creat_select_li">'
                                    snapshotHtml2 += '<p>板块</p>'
                                    snapshotHtml2 += '<div class="nice_select nice_selects">'
                                    snapshotHtml2 += '<input value = ' + v[i].liData2 + ' readonly>'
                                    snapshotHtml2 += '</div>'
                                    snapshotHtml2 += '</li>'
                                    snapshotHtml2 += '<li class="creat_select_li">'
                                    snapshotHtml2 += '<p>行业</p>'
                                    snapshotHtml2 += '<div class="nice_select nice_selects">'
                                    snapshotHtml2 += '<input value = ' + v[i].liData3 + ' readonly>'
                                    snapshotHtml2 += '</div>'
                                    snapshotHtml2 += '</li>'
                                    snapshotHtml2 += '<li class="creat_select_li">'
                                    snapshotHtml2 += '<p>交易所</p>'
                                    snapshotHtml2 += '<div class="nice_select nice_selects">'
                                    snapshotHtml2 += '<input value = ' + v[i].liData4 + ' readonly>'
                                    snapshotHtml2 += '</div>'
                                    snapshotHtml2 += '</li>'
                                    snapshotHtml2 += '<li class="creat_select_li">'
                                    snapshotHtml2 += '<p>融资融券</p>'
                                    snapshotHtml2 += '<div class="nice_select nice_selects">'
                                    snapshotHtml2 += '<input value = ' + v[i].liData5 + ' readonly>'
                                    snapshotHtml2 += '</div>'
                                    snapshotHtml2 += '</li>'
                                    snapshotHtml2 += '<li class="creat_select_li">'
                                    snapshotHtml2 += '<p>剔除ST、*ST</p>'
                                    snapshotHtml2 += '<div class="nice_select nice_selectx">'
                                    if (v[i].liData6 == false) {
                                        snapshotHtml2 += '<input style="width:15px; height:15px;margin-top:6px;" type="checkbox" disabled="disabled">'
                                    } else {
                                        snapshotHtml2 += '<input style="width:15px; height:15px; margin-top:6px;" type="checkbox" disabled="disabled" checked>'
                                    }
                                    snapshotHtml2 += '</div>'
                                    snapshotHtml2 += '</li>'
                                    snapshotHtml2 += '</ul>'
                                } else if (k == chooseCondition) {
                                    snapshotHtml3 += '<tr>'
                                    snapshotHtml3 += '<td>' + v[i].tdData1 + '</td>'
                                    snapshotHtml3 += '<td>'
                                    snapshotHtml3 += '<div class="nice_select nice_selects">'
                                    snapshotHtml3 += '<input value = ' + v[i].tdData2 + ' readonly>'
                                    snapshotHtml3 += '</div>'
                                    snapshotHtml3 += '</td>'
                                    snapshotHtml3 += '<td>'
                                    snapshotHtml3 += '<div class="nice_select nice_selects">'
                                    snapshotHtml3 += '<input value = ' + v[i].tdData3 + ' readonly>'
                                    snapshotHtml3 += '</div>'
                                    snapshotHtml3 += '</td>'
                                    snapshotHtml3 += '<td>'
                                    snapshotHtml3 += '<div class="nice_select nice_selects">'
                                    snapshotHtml3 += '<input value = ' + v[i].tdData4 + ' readonly>'
                                    snapshotHtml3 += '</div>'
                                    snapshotHtml3 += '</td>'
                                    snapshotHtml3 += '</tr>'
                                } else if (k == sortOrder) {
                                    snapshotHtml4 += '<tr>'
                                    snapshotHtml4 += '<td>' + v[i].tdData1 + '</td>'
                                    snapshotHtml4 += '<td>'
                                    snapshotHtml4 += '<div class="nice_select nice_selects">'
                                    snapshotHtml4 += '<input value = ' + v[i].tdData2 + ' readonly>'
                                    snapshotHtml4 += '</div>'
                                    snapshotHtml4 += '</td>'
                                    snapshotHtml4 += '<td>'
                                    snapshotHtml4 += '<div class="nice_select nice_selects">'
                                    snapshotHtml4 += '<input value = ' + v[i].tdData3 + ' readonly>'
                                    snapshotHtml4 += '</div>'
                                    snapshotHtml4 += '</td>'
                                    snapshotHtml4 += '<td>'
                                    snapshotHtml4 += '<div class="nice_select nice_selects">'
                                    snapshotHtml4 += '<input value = ' + v[i].tdData4 + ' readonly>'
                                    snapshotHtml4 += '</div>'
                                    snapshotHtml4 += '</td>'
                                    snapshotHtml4 += '</tr>'
                                } else if (k == stocksStop) {
                                    snapshotHtml5 += '<tr>'
                                    snapshotHtml5 += '<td>' + v[i].tdData1 + '</td>'
                                    snapshotHtml5 += '<td>'
                                    snapshotHtml5 += '<div class="nice_select nice_selects">'
                                    snapshotHtml5 += '<input value = ' + v[i].tdData2 + ' readonly>'
                                    snapshotHtml5 += '</div>'
                                    snapshotHtml5 += '</td>'
                                    snapshotHtml5 += '<td>'
                                    snapshotHtml5 += '<div class="nice_select nice_selects">'
                                    snapshotHtml5 += '<input value = ' + v[i].tdData3 + ' readonly>'
                                    snapshotHtml5 += '</div>'
                                    snapshotHtml5 += '</td>'
                                    snapshotHtml5 += '<td>'
                                    snapshotHtml5 += '<div class="nice_select nice_selects">'
                                    snapshotHtml5 += '<input value = ' + v[i].tdData4 + ' readonly>'
                                    snapshotHtml5 += '</div>'
                                    snapshotHtml5 += '</td>'
                                    snapshotHtml5 += '</tr>'
                                } else if (k == marketstop) {
                                    snapshotHtml6 += '<tr>'
                                    snapshotHtml6 += '<td>' + v[i].tdData1 + '</td>'
                                    snapshotHtml6 += '<td>'
                                    snapshotHtml6 += '<div class="nice_select nice_selects">'
                                    snapshotHtml6 += '<input value = ' + v[i].tdData2 + ' readonly>'
                                    snapshotHtml6 += '</div>'
                                    snapshotHtml6 += '</td>'
                                    snapshotHtml6 += '<td>'
                                    snapshotHtml6 += '<div class="nice_select nice_selects">'
                                    snapshotHtml6 += '<input value = ' + v[i].tdData3 + ' readonly>'
                                    snapshotHtml6 += '</div>'
                                    snapshotHtml6 += '</td>'
                                    snapshotHtml6 += '<td>'
                                    snapshotHtml6 += '<div class="nice_select nice_selects">'
                                    snapshotHtml6 += '<input value = ' + v[i].tdData4 + ' readonly>'
                                    snapshotHtml6 += '</div>'
                                    snapshotHtml6 += '</td>'
                                    snapshotHtml6 += '<td>'
                                    snapshotHtml6 += '<div class="nice_select nice_selects">'
                                    snapshotHtml6 += '<input value = ' + v[i].tdData5 + ' readonly>'
                                    snapshotHtml6 += '</div>'
                                    snapshotHtml6 += '</td>'
                                    snapshotHtml6 += '</tr>'
                                }
                            }
                            snapshotContent1.append(snapshotHtml1);
                            snapshotContent2.append(snapshotHtml2);
                            snapshotContent3.append(snapshotHtml3);
                            snapshotContent4.append(snapshotHtml4);
                            snapshotContent5.append(snapshotHtml5);
                            snapshotContent6.append(snapshotHtml6);
                            snapshotArray.push(v);
//                      }
                    })

                }else{
                    $("#snapshotPython").html("<pre style='white-space:pre;'><b>"+snapshotValue.snapshot+"</b></pre>");
                }

            });

        },
        error : function() {
            alert("查看快照进入未成功");
        }
    })
    
    //清除已生成的数据
	$(".close_btn").on('click',function(){
		$(".creat_select ul").remove();
		$(".creat_table tbody tr").remove();
	})
}

$(function () {
    historyTest(historyId);
})
//回测历史
function historyTest(historyId){
    var historyId = $("#historyId").val();
    $.ajax({
        url : hosturl + "/select/backtests", // 后台处理接口
        timeout : 15000,
        type : "get", // 数据接收方式
        async : false,
        data : {
            "id" : historyId,
        },
        dataType : "json", // 接受数据格式
        success : function(data) {
            $.each(data.historytest, function(hisKey,hisValue) {
                $("").val()
                var hisResult = $.parseJSON(hisValue.result);
                if(hisValue.name) {
                    var name = hisValue.name;
                    $("#strategyNameHistory").text(name);
                }
                var status = hisResult.status;
                if(hisResult.allData && hisResult.allData.summary) {
                    var backTime = hisResult.allData.summary.data.run_start;
                    var startTime = hisResult.allData.summary.data.start_date;
                    var endTime = hisResult.allData.summary.data.end_date;
                    var annualized = hisResult.allData.summary.data.benchmark_annualized_returns;
                    var maxDrawdown = hisResult.allData.summary.data.max_drawdown;
                }
                var hisHtmlId = $(".history_contact tbody");
                var hisHtml = "";
                hisHtml += '<tr>'
                    +'<td>'+ backTime +'</td>'
                    +'<td>'+ startTime +'</td>'
                    +'<td>'+ endTime +'</td>'
                    +'<td class="a">'+ annualized +'</td>'
                    +'<td>'+ maxDrawdown +'</td>'
                    +'<td>'+ status +'</td>'
                    +'<td>'
                    +'<a href="javascript:void(0);" onclick="snapshot('+ hisValue.bid  +')" class="pop">'+'查看快照'+'</a>'
                    +'<a href="testResults?bid='+hisValue.bid+'">'+'回测结果'+'</a>'
                '</td>'
                +'</tr>';
                hisHtmlId.append(hisHtml);
                bianji = hisValue.type;
            });
        },
        error : function() {
            alert("回测历史进入未成功");
        }
    })
}

function compile(){
    var historyId = $("#historyId").val();
    var bianji = $("#creatS").val();
    if(bianji==1) {
        window.location.href = "createStrategy.html?id="+historyId;
    }else{
        window.location.href = "editStrategy.html?id="+historyId;
    }
}

function history(){
    var historyId = $("#historyId").val();
    var type = $("#creatS").val();
    window.location.href="history.html?sid=" + historyId + "&type="+ type;
}


/*$(function compile() {
    $(".compile").on("click",function(){
        if(bianji==1){
            function compile() {
                window.location.href("createStrategy.html");
            }
        }else{
            function compile() {
                window.location.href("editStrategy.html");
            }
        }

    })

})*/

