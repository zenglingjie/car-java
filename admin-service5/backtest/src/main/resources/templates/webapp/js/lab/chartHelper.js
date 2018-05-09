function getOption(vu) {
    var option = {
                animation: false,
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
                    trigger: 'axis',
                    show:false
                },
                legend: {
                    data: [ '回测', '基准'],
                    top: 'top',
                    left: 'center',
                    y: '0%',
                },
                grid: [{
                    x: '0',
                    y: '10%',
                    width: '100%',
                    height: '60%'
                }, {
                    x2: '0',
                    y: '100%',
                    width: '100%',
                    height: '40%'
                }],
                xAxis: [{
                    type: 'category',
                    data: vu.xTimeData,
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
                    data: vu.xTimeData,
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
                    start: 0,
                    xAxisIndex: [0, 1],
                    end: 100
                }, {
                    xAxisIndex: [0, 1],
                    show: true,
                    type: 'slider',
                    y: '80%',
                    start: 0,
                    end: 100
                }],
                series: [
                    {
                    name: '回测',
                    type: 'line',
                    data: vu.roiData,
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
                    },
                    {
                    name: '基准',
                    type: 'line',
                    data: vu.benchRoiData,
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
                   }
                ]
    };

    var optionAddRelativeReturns = {
        animation: false,
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
            trigger: 'axis',
            show:false

        },
        legend: {
            data: [ '回测', '基准','相对收益'],
            bottom: 'bottom',
            left: 'center',
            y: '75%',
        },
        grid: [{
            x: '0',
            y: '10%',
            width: '100%',
            height: '60%'
        }, {
            x2: '0',
            y: '100%',
            width: '100%',
            height: '40%'
        }],
        xAxis: [{
            type: 'category',
            data: vu.xTimeData,
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
            data: vu.xTimeData,
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
            start: 0,
            xAxisIndex: [0, 1],
            end: 100
        }, {
            xAxisIndex: [0, 1],
            show: true,
            type: 'slider',
            y: '80%',
            start: 0,
            end: 100
        }],
        series: [
            {
                name: '回测',
                type: 'line',
                data: vu.roiData,
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
            },
            {
                name: '基准',
                type: 'line',
                data: vu.benchRoiData,
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
            },
            {
                name: '相对收益',
                type: 'line',
                data: vu.relativeData,
                smooth: true,
                xAxisIndex: 0,
                yAxisIndex: 0,
                itemStyle: {
                    normal: {
                        color: '#FF00FF'
                    }
                },
                lineStyle: {
                    normal: {
                        opacity: 0.5
                    }
                }
            }
        ]
    };

    if(vu.relativeReturns){
        return optionAddRelativeReturns;
    }else {
        return option;
    }

}

function getDailyRoiDataOption(vu) {
    var loadDailyRoiData = {
        animation: false,
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
            trigger: 'axis',
            show: false,
        },
        legend: {
            data: [ '日收益'],
            bottom: 'bottom',
            left: 'center',
            y: '75%',
        },
        grid: [{
            x: '0',
            y: '10%',
            width: '100%',
            height: '60%'
        }, {
            x2: '0',
            y: '100%',
            width: '100%',
            height: '40%'
        }],
        xAxis: [{
            type: 'category',
            data: vu.xTimeData,
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
            data: vu.xTimeData,
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
            start: 0,
            xAxisIndex: [0, 1],
            end: 100
        }, {
            xAxisIndex: [0, 1],
            show: true,
            type: 'slider',
            y: '80%',
            start: 0,
            end: 100
        }],
        series: [
            {
                name: '日收益',
                type: 'line',
                data:  vu.dailyRoi,
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
            }
        ]
    };

    return loadDailyRoiData;
}

function getVolumeOption(vu) {
    var loadDailyRoiData = {
        animation: false,
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
            trigger: 'axis',
            show: false,
        },
        legend: {
            data: [ '买入数量','卖出数量'],
            bottom: 'bottom',
            left: 'center',
            y: '75%',
        },
        grid: [{
            x: '0',
            y: '10%',
            width: '100%',
            height: '60%'
        }, {
            x2: '0',
            y: '100%',
            width: '100%',
            height: '40%'
        }],
        xAxis: [{
            type: 'category',
            data: vu.xTimeData,
            gridIndex: 0,
            axisTick : {show: false},
            scale: true,
            boundaryGap: true,
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
            data: vu.xTimeData,
            gridIndex: 1,
            axisLine: {
                lineStyle: {
                    color: '#666'
                }
            },

        }],
        yAxis: [{
            type : 'value',
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
            start: 0,
            xAxisIndex: [0, 1],
            end: 100
        }, {
            xAxisIndex: [0, 1],
            show: true,
            type: 'slider',
            y: '80%',
            start: 0,
            end: 100
        }],
        series: [
            {
                name: '买入数量',
                type: 'bar',
                data: vu.dailyCountBuy,
                smooth: true,
                barWidth : 20,
                stack: '总量',
                xAxisIndex: 0,
                yAxisIndex: 0,
                itemStyle: {
                    normal: {
                        color: '#FF0000',
                        label : {show: false}
                    }
                },
                lineStyle: {
                    normal: {
                        opacity: 0.8
                    }
                }
            },
            {
                name: '卖出数量',
                type: 'bar',

                data: vu.dailyCountSell,
                smooth: true,
                stack: '总量',
                xAxisIndex: 0,
                yAxisIndex: 0,
                barWidth : 20,
                itemStyle: {
                    normal: {
                        color: '#66CDAA',
                        label : {show: false, position: 'left'}
                    }
                },
                lineStyle: {
                    normal: {
                        opacity: 0.8
                    }
                }
            }
        ]
    };

    /*var loadDailyRoiData = {

     tooltip : {
     trigger: 'axis',

     },
     toolbox: {
     show : true,
     right: "2%",

     },

     yAxis : [
     {
     type : 'value'
     }
     ],
     xAxis : [
     {
     type : 'category',
     axisTick : {show: false},
     data : vu.xTimeData
     }
     ],
     series : [

     {
     name:'买入数量',
     type:'bar',
     stack: '总量',
     barWidth : 15,
     itemStyle: {normal: {
     label : {show: false},
     color: '#FF0000',
     }},
     data: vu.dailyCountBuy
     },
     {
     name:'卖出数量',
     type:'bar',
     stack: '总量',
     itemStyle: {
     normal: {
     label : {show: false, position: 'left'},
     color: '#66CDAA',
     label : {show: true}
     }},
     data: vu.dailyCountSell
     }
     ]


     };*/
    return loadDailyRoiData;
}