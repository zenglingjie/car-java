$(function(){

})

var testSelect = new function () {
    var data;
    var ajaxHelper = new AjaxHelper();

    var vu = new Vue({
        el: '#testResults',
        data: {
            rate:{},
            strategy: {},
            strategyName: "",
            resultSum: {},
            performance: [],
            relativeReturns:false,
            xTimeData: [],
            roiData: [],
            benchRoiData: [],
            roiLogData: [],
            benchRoiLogData: [],
            relativeData: [],
            dailyRoi:[],
            dailyCountBuy: [],
            dailyCountSell: [],
            dayTrades: [],
            dayPos: [],
            log:"",
            errorlog:"",
            href: "",
            returnRate:[],
            benchMarkRate:[],
            alpha:[],
            beta:[],
            sharpe:[],
            sortino:[],
            ir:[],
            vola:[],
            down:[],
            apiUrl: hosturl + "/strategy/testResults",
            straname:"",
            status:""

        },
        methods: {
            getTestResults: function(condi) {
                var vm = this;
                var performanceDataTransform = function () {
                    for(var i=0; i<vm.performance.length;i++){
                        var paddingFormatStr = {
                            "benchmark_total_returns":"NaN",
                            "volatility":"NaN",
                            "sortino":"NaN",
                            "max_drawdown":"NaN",
                            "total_returns":"NaN",
                            "beta":"NaN",
                            "alpha":"NaN",
                            "sharpe":"NaN",
                            "information_ratio":"NaN"
                        };
                        var size = Object.getOwnPropertyNames(vm.performance[i].item).length;
                        if( size == 2){
                            vm.performance[i].item["12M"] = paddingFormatStr;
                            vm.performance[i].item["6M"] = paddingFormatStr;
                            vm.performance[i].item["3M"] = paddingFormatStr;
                        }else if(size == 3){
                            vm.performance[i].item["12M"] = paddingFormatStr;
                            vm.performance[i].item["6M"] = paddingFormatStr;
                        }else if(size == 4) {
                            vm.performance[i].item["12M"] = paddingFormatStr;
                        }

                        var alphaRow={}, betaRow={},sharpeRow={},sortinoRow={},
                            maxDrawdownRow={},volatilityRow={},totalReturnsRow={},
                            benchmarkTotalReturnsRow={},irRow={};

                        alphaRow.date = vm.performance[i].month;
                        betaRow.date = vm.performance[i].month;
                        sharpeRow.date = vm.performance[i].month;
                        sortinoRow.date = vm.performance[i].month;
                        maxDrawdownRow.date = vm.performance[i].month;
                        volatilityRow.date = vm.performance[i].month;
                        totalReturnsRow.date = vm.performance[i].month;
                        benchmarkTotalReturnsRow.date = vm.performance[i].month;
                        irRow.date = vm.performance[i].month;



                        for(var month in vm.performance[i].item ){
                            var monthStr = month;
                            switch (month){
                                case '1M':
                                    monthStr = "mon1";
                                    break;
                                case '3M':
                                    monthStr = "mon3";
                                    break;
                                case '6M':
                                    monthStr = "mon6";
                                    break;
                                case '12M':
                                    monthStr = "mon12";
                                    break;
                                default :
                                    break;
                            }

                            for(var perItem in vm.performance[i].item[month]){

                                switch (perItem){
                                    case 'alpha':
                                        alphaRow[monthStr] = vm.performance[i].item[month]['alpha'];
                                        break;
                                    case 'beta':
                                        betaRow[monthStr] = vm.performance[i].item[month]['beta'];
                                        break;
                                    case 'sharpe':
                                        sharpeRow[monthStr] = vm.performance[i].item[month]['sharpe'];
                                        break;
                                    case 'sortino':
                                        sortinoRow[monthStr] = vm.performance[i].item[month]['sortino'];
                                        break;
                                    case 'max_drawdown':
                                        maxDrawdownRow[monthStr] = vm.performance[i].item[month]['max_drawdown'];
                                        break;
                                    case 'volatility':
                                        volatilityRow[monthStr] = vm.performance[i].item[month]['volatility'];
                                        break;
                                    case 'total_returns':
                                        totalReturnsRow[monthStr] = vm.performance[i].item[month]['total_returns'];
                                        break;
                                    case 'benchmark_total_returns':
                                        benchmarkTotalReturnsRow[monthStr] = vm.performance[i].item[month]['benchmark_total_returns'];
                                        break;
                                    case 'information_ratio':
                                        irRow[monthStr] = vm.performance[i].item[month]['information_ratio'];
                                        break;
                                    default :
                                        break;

                                }
                            }

                        }

                        vm.returnRate.push(totalReturnsRow);
                        vm.benchMarkRate.push(benchmarkTotalReturnsRow);
                        vm.alpha.push(alphaRow);
                        vm.beta.push(betaRow);
                        vm.sharpe.push(sharpeRow);
                        vm.sortino.push(sortinoRow);
                        vm.ir.push(irRow);
                        vm.vola.push(volatilityRow);
                        vm.down.push(maxDrawdownRow);
                    }
                }
                var successCall = function(d){
                    vm.strategy = d;
                    vm.log = d.log;
                    vm.errorlog = d.errorlog;
                    if(d.type && d.id) {
                        if (d.type === 1) {
                            vm.href = "createStrategy?id=" + d.id;
                        } else{
                            vm.href = "editStrategy?id=" + d.id;
                        }
                    }
                    var text = d.result.toString().replace(/NaN/g,0).replace(/Infinity/g,0);
                    // var result = $.parseJSON(text)
                    var result = JSON.parse(text);
                    vm.logs = result.logs;
                    vm.strategyName = d.name;
                    vm.status = result.status;

                    if(result.allData) {
                        result.allData.name = d.name;
                        result.allData.modified = d.modified;
                        result.allData.created = d.created;

                        if (result.allData.summary) {
                            vm.resultSum = result.allData.summary.data;
                        }


                        if (result.allData.tradeDetail) {
                            vm.dayTrades = result.allData.tradeDetail.data;

                        }
                        if (result.allData.acountDetail) {
                            vm.dayPos = result.allData.acountDetail.data;
                        }

                        if (result.allData.performanceSplit) {
                            vm.performance = result.allData.performanceSplit.data;
                            performanceDataTransform();
                        }
                        if (result.allData.netCurve) {
                            data = result.allData.netCurve.data;
                            loadChartData();
                        }
                    }

                };
                ajaxHelper.get(vm.apiUrl, condi, successCall);
            }
        },
        mounted:function () {

        },
        created:function () {
            var id = $("#sid").val();
            this.getTestResults({"id":id});
        }
    });

    function display_accumulative(){
        vu.roiData = data['roi'];
        vu.benchRoiData = data['benchroi'];
    }
    function display_log(){
        vu.roiData = data['roi_log'];
        vu.benchRoiData = data['benchroi_log'];
    }
    function display_relative(){
        if($("#accumulative_returns").is(":checked")){
            vu.relativeData = data['exceed_roi'];
        }else{
            vu.relativeData = data['exceed_roi_log'];
        }
    }
    function loadChartData() {
        vu.xTimeData = data['date'];
        vu.dailyRoi = data['daily_roi'];
        var dailyCountArray = data['daily_count'];
        for(var i in dailyCountArray ){
            if(dailyCountArray[i].length == 2){
                vu.dailyCountBuy.push(dailyCountArray[i][0]);
                vu.dailyCountSell.push(-(dailyCountArray[i][1]));
            }

        }

        vu.relativeReturns = $("#relative_returns").is(":checked");

        if($("#accumulative_returns").is(":checked")) {
            display_accumulative();
        }else {
            display_log();
        }
        if($("#relative_returns").is(":checked")){
            display_relative();
        }else{
            delete vu.relativeData;
        }
        renderChart();

    }


    function renderChart() {

        var rChart = echarts.init(document.getElementById('data-chart'));
        rChart.setOption(getOption(vu));

        var dailyRioChart = echarts.init(document.getElementById('daily-rio-chart'));
        dailyRioChart.setOption(getDailyRoiDataOption(vu));

        var volumeChart = echarts.init(document.getElementById('volume-chart'));
        volumeChart.setOption(getVolumeOption(vu));


    }

    return{
        loadChartData:loadChartData,

    }

};

