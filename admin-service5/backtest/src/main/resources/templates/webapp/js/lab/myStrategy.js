
var listSelect = new function () {
    /*使用ajax发送请求*/
    var ajaxHelper = new AjaxHelper();
    var vu = new Vue({
            el: '#ind_genresult',
            data: {
                strategyList: [],
                resultJson: [],
                resultPython: [],
                /*接受type=1的数据*/
                results1: [],
                /*接受type=2数据*/
                results2: [],
                becktestResult:[],
                /*发送ajax的URL*/
                apiUrl: hosturl + "/strategy/myStrategy"

            },
            methods: {
                deletes: function(result) {
                    var deleteId = result.sid;
                    $.ajax({
                       url : hosturl + "/strategy/strategy_delete", // 后台处理接口
                       timeout : 15000,
                       type : "get", // 数据接收方式
                       async : false,
                       data : {
                           "sid" : deleteId
                       },
                       dataType : "json", // 接受数据格式
                        success : function() {
                            $(".de_icon").on("click",function(){
                                $(this).parents(".delete").parents(".ind_list li").remove();
                            })
                       },
                       error : function() {
                        alert("抱歉！服务器发生错误");
                       }

                })
                },
                /*调用参数的方法*/
                getGenStrategy: function(condi) {
                    var vm = this;
                    var type = condi.type;
                    /*回调函数----返回的数据*/
                    var successCall = function(d){
                        var arr = d.list;
                        function formatDate(now)
                        {
                            var year=now.getYear().toLocaleString().substr(1,2);
                            year = "20" + year;
                            var month=("0" + (now.getMonth()+1)).slice(-2);
                            var date=("0" + now.getDate()).slice(-2);
                            var hour=("0" +now.getHours()).slice(-2);
                            var minute=("0" +now.getMinutes()).slice(-2);
                            var second=("0" +now.getSeconds()).slice(-2);
                            return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
                        }
                        for(var item in arr){
                            arr[item].created = formatDate(new Date(arr[item].created));

                            arr[item].updatetime = formatDate(new Date(arr[item].updatetime));


                            // var updatetimeDate = new Date(arr[item].updatetime).toLocaleString().substr(0,17);
                            // var updatetimeDate = new Date(arr[item].updatetime).toLocaleString();
                            // arr[item].updatetime = updatetimeDate;
                            arr[item].historyhref = "history.html?sid=" + arr[item].sid + "&type="+ type;

                            var itemSum = $.parseJSON(arr[item].result);
                            if(itemSum) {
                                var alldata = itemSum.allData;
                                if(alldata) {
                                    var summary = alldata.summary;
                                    if (summary) {
                                        var data = summary.data;
                                        if(data){
                                            arr[item].totoalReturns = data.total_returns;
                                            arr[item].max_drawdown = data.max_drawdown;
                                            arr[item].beta = data.beta;
                                            arr[item].alpha = data.alpha;
                                            arr[item].annualized_returns = data.annualized_returns;
                                            arr[item].sharpe = data.sharpe;
                                        }
                                    }
                                }
                            }
                        }

                        if(d.type == 1){
                           vm.resultJson = d.list;
                        }else if(d.type == 2){
                           vm.resultPython = d.list;
                        }

                    };
                    ajaxHelper.get(vm.apiUrl, condi, successCall);
                 }
            },
            mounted:function () {

            },
            /*参数*/
            created:function () {
                this.getGenStrategy({type:1});
                this.getGenStrategy({type:2});

            }
        });

};
