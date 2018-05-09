var stock = (function () {
	// 数据存放目录
	var baseUrl = './data/';
    // 获取数据并渲染界面
	function render() {
		var kUrl = baseUrl + 'k.json';
		var handicapUrl = baseUrl + 'handicap.json';
		var fundFlowUrl = baseUrl + 'fund_flow.json';
		var analysisUrl = baseUrl + 'compony_finance_analysis.json';
		_ajax(kUrl, function(data) {
            _renderK(data)
        })
//      _ajax(handicapUrl, function(data) {
//          _renderHandicap(data)
//      })
//      _ajax(fundFlowUrl, function(data) {
//          _renderFundFlow(data)
//      })
//      _ajax(analysisUrl, function(data) {
//          _renderAnalysis(data)
//      })
	}
    // 渲染k线图
	function _renderK(data) {
		var kChart = echarts.init(document.getElementById('data-k'));
		var xData = [],
			kData = [],
			volumeData = [];
		data.forEach(function (value) {
			xData.push(value.date)
			kData.push(value.value)
			volumeData.push(value.volume)
		})
		function calculateMA(dayCount) {
	        var result = [];
	        for (var i = 0, len = kData.length; i < len; i++) {
	            if (i < dayCount) {
	                result.push('-');
	                continue;
	            }
	            var sum = 0;
	            for (var j = 0; j < dayCount; j++) {
	                sum += kData[i - j][1];
	            }
	            result.push(sum / dayCount);
	        }
	        return result;
	    }
		var option = {
		    title: {
		        text: '',
		        left: 'center'
		    },
		    toolbox: {
		        show: false,
		        right: "2%",
		        feature: {
		            dataZoom: {
		                yAxisIndex: 'none'
		            }
		        }
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
//		    legend: {
//		        data: [ 'MA5', 'MA10', 'MA20', 'MA30'],
//		        bottom: 'bottom',
//		        left: 'center',
//		        y: '80%',
//		    },
		    grid: [{
		        x: '8%',
		        y: '15%',
		        width: '88%',
		        height: '60%'
		    }, {
		        x2: '8%',
		        y: '100%',
		        width: '84%',
		        height: '50%'
		    }],
		    xAxis: [{
		        type: 'category',
		        data: xData,
		        gridIndex: 0,
		        scale: true,
		        boundaryGap: false,
		        axisLine: {
		            onZero: true
		        },
		        splitLine: {
		            show: true,
		        },
		        splitNumber: 20,
		        min: 'dataMin',
		        max: 'dataMax'
		    }, {
		        data: xData,
		        gridIndex: 1,
		        axisLine: {
		            lineStyle: {
		                color: '#666'
		            }
		        },
		      
		    }],
		    yAxis: [{
		        scale: true,
		        gridIndex: 0,
		        
//		        splitArea: {
//		            show: true
//		        }
		    }, {
		        gridIndex: 1,
		        axisLine: {
		        	lineStyle: {
		                color: '#666'
		            }
		        },
		    }],
		    dataZoom: [{
		        type: 'inside',
		        start: 50,
		        xAxisIndex: [0, 1],
		        end: 100
		    }, {
		        xAxisIndex: [0, 1],
		        show: true,
		        type: 'slider',
		        y: '90%',
		        start: 150,
		        end: 100
		    }],
		    series: [{
		        name: 'MA5',
		        type: 'line',
		        data: calculateMA(5),
		        smooth: true,
		        xAxisIndex: 0,
		        yAxisIndex: 0,
		        itemStyle: {
	                normal: {
	                    color: '#13fc29'
	                }
	           },
		        lineStyle: {
		            normal: {
		                opacity: 0.5
		            }
		        }
		    }, {
		        name: 'MA10',
		        type: 'line',
		        data: calculateMA(10),
		        smooth: true,
		        xAxisIndex: 0,
		        yAxisIndex: 0,
		        itemStyle: {
	                normal: {
	                    color: '#137bfc'
	                }
	           },	           
		        lineStyle: {
		            normal: {
		                opacity: 0.5
		            }
		        }
		    }, {
		        name: 'MA20',
		        type: 'line',
		        xAxisIndex: 0,
		        yAxisIndex: 0,
		        data: calculateMA(20),
		        smooth: true,
		        itemStyle: {
	                normal: {
	                    color: '#df07f9'
	                }
	           },
		        lineStyle: {
		            normal: {
		                opacity: 0.5
		            }
		        },
		        
		    }, {
		        name: 'MA30',
		        xAxisIndex: 0,
		        yAxisIndex: 0,
		        type: 'line',
		        data: calculateMA(30),
		        smooth: true,
		        itemStyle: {
	                normal: {
	                    color: '#000',
	                }
	           },
		        lineStyle: {
		            normal: {
		                opacity: 0.5,
		            }
		        }
		    }]
		};


		kChart.setOption(option);
	}
    // 渲染盘口
    // 渲染资金流向
	// 封装ajax方法
	function _ajax(url, cb) {
	    // $.getJSON(url + '&callback=?')
	    $.getJSON(url)
        .done(function(d) {
            // console.log(d)
            cb && cb(d)
        })
        .fail(function(e) {
        	console.log(e)
            alert(url + '：获取不到数据');
        })
	}
    // 暴露render方法
	return {
		render : render
	}
})()
