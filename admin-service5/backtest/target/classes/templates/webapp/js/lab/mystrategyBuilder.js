$(function(){
    getlist2(1);getlist2(2);getlist2(3);getlist2(4);
})

//点击弹出添加窗口
function getlist2(param){
    $(this).parent().parent().parent().attr('flage',2);
    $.ajax({
        url : hosturl + "/select/stock", // 后台处理接口
        timeout : 15000,
        type : "get", // 数据接收方式
        async : false,
        data : {
            "mytype" : param
        },
        dataType : "json", // 接受数据格式
        //jsonpCallback: 'callResult',	jsonp
        success : function(data) {
            //动态创建弹框左边块
            var dataId= $("#pop_1"); var dataHtml = ""; var j=0;
            $.each(data.list, function(i,val){
                j++;
                $(".append_right").append('<div class="app_tab" id="append'+ j +'"></div>');
                dataHtml += "<li><a>" + i + "</a></li>";
                var html = add(val);
                $("#append"+j).html(html);
                return;
            });
            dataId.html("");
            dataId.append(dataHtml);

            //判断checkbox是否被勾选
            if ($(".app_tab").find("input[type=checkbox]:checked").length >= 10) {
                $('input[type=checkbox]').attr('disabled', true);
                $("input[type=checkbox]:checked").attr('disabled', false);
            } else {
                $('input[type=checkbox]').attr('disabled', false);
            }

            //动态创建弹框右边块
            function add(val){
                divisorHtml = "";
                for(var j=0;j<val.length;j++){
                    var _id = val[j].factor_id + "";
                    var _ids = val[j].factor_id;
                    var _operators = val[j].operator;
                    var _scopes = val[j].scope;
                    var _paramemters = val[j].paramemter;
                    var _myindexs = val[j].myindex;
                    var dao = {};
                    dao['id'] = _ids;
                    dao['operator'] = _operators;
                    dao['scope'] = _scopes;
                    dao['paramemter'] = _paramemters;
                    dao['myindex'] = _myindexs;
                    if(addMap.indexOf(_id) != -1){
                        var userInputId = dataArray[_id];
                        var _userInput = userInputId.userInput;
                        dao['userInput'] = _userInput;
                    }
                    dataArray[_ids] = dao;
                    stringId2 = "ck"+ val[j].factor_id;
                    divisorHtml +='<div class="append_rcont">'
                    if(addMap.indexOf(_id) == -1){
                        divisorHtml +='<input type="checkbox" id='+ stringId2 +'>';
                    }else{
                        divisorHtml +='<input type="checkbox" id='+ stringId2 +' checked="checked">';
                    }
                    divisorHtml +='<label for='+ stringId2 +' _mytype = '+val[j].mytype+' _id = '+val[j].factor_id+' _name = '+val[j].name+' _operator = '+val[j].operator+' _scope = '+val[j].scope+' _paramemter = '+val[j].paramemter+' _myindex = '+val[j].myindex+' >'+ val[j].name+'</label> ';
                    divisorHtml +='<i>';
                    divisorHtml +='<div class="app_pop">';
                    divisorHtml +='<p>'+ val[j].mydescribe +'</p>';
                    divisorHtml +='</div>';
                    divisorHtml +='</i>';
                    divisorHtml +='</div>';
                }
                return divisorHtml;
            }
            bind();
        },
        error : function() {
            alert("抱歉！服务器发生错误");
        }
    })
    //弹窗设置默认值
    $(document).ready(function(){
        $(".append_left li:first-child").addClass("hover")
        $(".app_tab").attr({"style":"display:none"});
        $(".append_right .app_tab:first-child").attr({"style":"display:block"});
    });
    //判断checkbox是否被勾选
    $(".app_tab").find('input[type=checkbox]').click(function() {
        $('input[type=checkbox]').attr('disabled', true);
        if ($("input[type=checkbox]:checked").length >= 10) {
            $("input[type=checkbox]:checked").attr('disabled', false);
            alert("最多勾选10条")
        } else {
            $('input[type=checkbox]').attr('disabled', false);
        }
    });

}

//我的策略-策略生成器
var addMap = [];
function strategyBuilder(param, isSnap){
	if (param == -1) {
	    return;
	}
	var url = hosturl + "/getStrategy";
	if (isSnap) {
	    url = hosturl + "/select/snapshot";
	}
	$.ajax({
	   url : url, // 后台处理接口
	   timeout : 15000,
	   type : "get", // 数据接收方式
	   async : false,
	   data : {
	    "id" : param
	   },
	   dataType : "json", // 接受数据格式
	   success : function(data) {
		$.each(data.list, function(snapshotKey,snapshotValue) {
			var snapshotJson = null;
			if (isSnap) {
			    snapshotJson = $.parseJSON(snapshotValue.snapshot);
			}
			else {
			    snapshotJson = $.parseJSON(snapshotValue.json);
			}
			$.each(snapshotJson,function(k,v){
				var transactionSet = $("#creatStrategy_cont1 h2 span").html();
	    		var stockPool = $("#creatStrategy_cont2 h2 span").html();
				var chooseCondition = $("#creatStrategy_cont3 h2 span").html();
			    var sortOrder = $("#creatStrategy_cont4 h2 span").html();
			    var stocksStop = $("#creatStrategy_cont5 h2 span").html();
			    var marketstop = $("#creatStrategy_cont6 h2 span").html();
				var _html = '';
				var paramemterJson = [];
				for(var j=0;j<v.length;j++){
					if(k==transactionSet){
						$("#li1").val(v[j].liData1);
						$("#li2").val(v[j].liData2);
						$("#li3").val(v[j].liData3);
						$("#li4").val(v[j].liData4);
						$("#li5").val(v[j].liData5);
						$("#li6").val(v[j].liData6);
						$("#li7").val(v[j].liData7);
					}else if(k==stockPool){
						$("#li8").val(v[j].liData1);
						$("#li9").val(v[j].liData2);
						$("#li10").val(v[j].liData3);
						$("#li11").val(v[j].liData4);
						$("#li12").val(v[j].liData5);
						if(v[j].liData6 == false){
							$("#li13").attr("")
						}else{
							$("#li13").attr("checked","checked")
						}
					}else if(k==chooseCondition){
						var id = v[j].tdData0;
						addMap.push(id);
                        var dao = dataArray[id];
                        var _userInput =  v[j].tdData5;
                        dao['userInput'] = _userInput;
                        dataArray[id] = dao;
						//运算符下拉
						var operatorData = dao.operator;//获取全部字符串格式的operator;
						var operatorSplit = operatorData.split(",");//用','分割字符串
						var operatorStr = JSON.stringify(operatorSplit);//将分割的字符串转换为符合JSON格式的字符串
						var operatorJson = $.parseJSON(operatorStr);//将JSON格式的字符串换为JSON
						var operatorArray = [];var operatorHtml = '';
						for(var i=0;i<operatorJson.length;i++){
							var operatorCont = operatorJson[i];
							operatorArray.push(operatorCont);
							operatorHtml +='<li>' + operatorArray[i]  + '</li>';
						}
						//范围下拉
						var scopeData = dao.scope;//获取全部字符串格式的operator;
						var scopeSplit = scopeData.split(",");//用','分割字符串
						var scopeStr = JSON.stringify(scopeSplit);//将分割的字符串转换为符合JSON格式的字符串
						var scopeJson = $.parseJSON(scopeStr);//将JSON格式的字符串换为JSON
						var scopeArray = [];var scopeHtml = '';
						for(var i=0;i<scopeJson.length;i++){
							var scopeCont = scopeJson[i];
							scopeArray.push(scopeCont);
							scopeHtml +='<li>' + scopeArray[i]  + '</li>';
						}
						_html += '<tr>'
                            +'<td style="display:none;">'+ v[j].tdData0 +'</td>'
							+'<td>'+ v[j].tdData1 +'</td>'
							+'<td>'
							+'<div class="nice_select" name="nice_select">  '
							+'<input type="text" value="'+v[j].tdData2+'" readonly>'
							+'<ul>' + operatorHtml + '</ul>'
							+'</div>'
							+'</td>'
							+'<td>'
							+'<div class="nice_select" name="nice_select">'
							+'<input type="text" value="'+v[j].tdData3+'" readonly>'
							+'<ul>'+ scopeHtml +'</ul> '
							+'</div>'
							+'</td>'
							+'<td>'
							+'<div class="nice_select selects" name="nice_select">'
							+'<input type="text" value="'+v[j].tdData4+'">'
							+'</div>'
							+'</td>'
							+'<td><a href="javascript:void(0);" class="redact" id="'+ v[j].tdData0 +'"></a></td>'
							+'<td><a href="javascript:void(0);" class="deleteData" id="'+ v[j].tdData0 +'"></a></td>'
							+'</tr>';
                        $("#creatStrategy_cont3 .creat_table").find('tbody').html(_html);
					}else if(k==sortOrder){
                        var id = v[j].tdData0;
                        addMap.push(id);
                        var dao = dataArray[id];
                        var _userInput =  v[j].tdData5;
                        dao['userInput'] = _userInput;
                        dataArray[id] = dao;
                        //运算符下拉
                        var operatorData = dao.operator;//获取全部字符串格式的operator;
                        var operatorSplit = operatorData.split(",");//用','分割字符串
                        var operatorStr = JSON.stringify(operatorSplit);//将分割的字符串转换为符合JSON格式的字符串
                        var operatorJson = $.parseJSON(operatorStr);//将JSON格式的字符串换为JSON
                        var operatorArray = [];var operatorHtml = '';
                        for(var i=0;i<operatorJson.length;i++){
                            var operatorCont = operatorJson[i];
                            operatorArray.push(operatorCont);
                            operatorHtml +='<li>' + operatorArray[i]  + '</li>';
                        }
                        //范围下拉
                        var scopeData = dao.scope;//获取全部字符串格式的operator;
                        var scopeSplit = scopeData.split(",");//用','分割字符串
                        var scopeStr = JSON.stringify(scopeSplit);//将分割的字符串转换为符合JSON格式的字符串
                        var scopeJson = $.parseJSON(scopeStr);//将JSON格式的字符串换为JSON
                        var scopeArray = [];var scopeHtml = '';
                        for(var i=0;i<scopeJson.length;i++){
                            var scopeCont = scopeJson[i];
                            scopeArray.push(scopeCont);
                            scopeHtml +='<li>' + scopeArray[i]  + '</li>';
                        }
                        _html += '<tr>'
                            +'<td style="display:none;">'+ v[j].tdData0 +'</td>'
                            +'<td>'+ v[j].tdData1 +'</td>'
                            +'<td>'
                            +'<div class="nice_select" name="nice_select">  '
                            +'<input type="text" value="'+v[j].tdData2+'" readonly>'
                            +'<ul>' + operatorHtml + '</ul>'
                            +'</div>'
                            +'</td>'
                            +'<td>'
                            +'<div class="nice_select" name="nice_select">'
                            +'<input type="text" value="'+v[j].tdData3+'" readonly>'
                            +'<ul>'+ scopeHtml +'</ul> '
                            +'</div>'
                            +'</td>'
                            +'<td>'
                            +'<div class="nice_select selects" name="nice_select">'
                            +'<input type="text" value="'+v[j].tdData4+'">'
                            +'</div>'
                            +'</td>'
                            +'<td><a href="javascript:void(0);" class="redact" id="'+ v[j].tdData0 +'"></a></td>'
                            +'<td><a href="javascript:void(0);" class="deleteData" id="'+ v[j].tdData0 +'"></a></td>'
                            +'</tr>';
                        $("#creatStrategy_cont4 .creat_table").find('tbody').html(_html);
                    }else if(k==stocksStop){
                        var id = v[j].tdData0;
                        addMap.push(id);
                        var dao = dataArray[id];
                        var _userInput =  v[j].tdData5;
                        dao['userInput'] = _userInput;
                        dataArray[id] = dao;
                        //运算符下拉
                        var operatorData = dao.operator;//获取全部字符串格式的operator;
                        var operatorSplit = operatorData.split(",");//用','分割字符串
                        var operatorStr = JSON.stringify(operatorSplit);//将分割的字符串转换为符合JSON格式的字符串
                        var operatorJson = $.parseJSON(operatorStr);//将JSON格式的字符串换为JSON
                        var operatorArray = [];var operatorHtml = '';
                        for(var i=0;i<operatorJson.length;i++){
                            var operatorCont = operatorJson[i];
                            operatorArray.push(operatorCont);
                            operatorHtml +='<li>' + operatorArray[i]  + '</li>';
                        }
                        //范围下拉
                        var scopeData = dao.scope;//获取全部字符串格式的operator;
                        var scopeSplit = scopeData.split(",");//用','分割字符串
                        var scopeStr = JSON.stringify(scopeSplit);//将分割的字符串转换为符合JSON格式的字符串
                        var scopeJson = $.parseJSON(scopeStr);//将JSON格式的字符串换为JSON
                        var scopeArray = [];var scopeHtml = '';
                        for(var i=0;i<scopeJson.length;i++){
                            var scopeCont = scopeJson[i];
                            scopeArray.push(scopeCont);
                            scopeHtml +='<li>' + scopeArray[i]  + '</li>';
                        }
                        _html += '<tr>'
                            +'<td style="display:none;">'+ v[j].tdData0 +'</td>'
                            +'<td>'+ v[j].tdData1 +'</td>'
                            +'<td>'
                            +'<div class="nice_select" name="nice_select">  '
                            +'<input type="text" value="'+v[j].tdData2+'" readonly>'
                            +'<ul>' + operatorHtml + '</ul>'
                            +'</div>'
                            +'</td>'
                            +'<td>'
                            +'<div class="nice_select" name="nice_select">'
                            +'<input type="text" value="'+v[j].tdData3+'" readonly>'
                            +'<ul>'+ scopeHtml +'</ul> '
                            +'</div>'
                            +'</td>'
                            +'<td>'
                            +'<div class="nice_select selects" name="nice_select">'
                            +'<input type="text" value="'+v[j].tdData4+'">'
                            +'</div>'
                            +'</td>'
                            +'<td><a href="javascript:void(0);" class="redact" id="'+ v[j].tdData0 +'"></a></td>'
                            +'<td><a href="javascript:void(0);" class="deleteData" id="'+ v[j].tdData0 +'"></a></td>'
                            +'</tr>';
                        $("#creatStrategy_cont5 .creat_table").find('tbody').html(_html);
                    }else if(k==marketstop){
                        var id = v[j].tdData0;
                        addMap.push(id);
                        var dao = dataArray[id];
                        var _userInput =  v[j].tdData6;
                        dao['userInput'] = _userInput;
                        dataArray[id] = dao;

                        //指数下拉
                        var myindexData = dao.myindex;//获取全部字符串格式的operator;
                        var myindexSplit = myindexData.split(",");//用','分割字符串
                        var myindexStr = JSON.stringify(myindexSplit);//将分割的字符串转换为符合JSON格式的字符串
                        var myindexJson = $.parseJSON(myindexStr);//将JSON格式的字符串换为JSON
                        var myindexArray = [];var myindexHtml = '';
                        var myindexHtml_li;
                        for(var i=0;i<myindexJson.length;i++){
                            var myindexCont = myindexJson[i];
                            myindexArray.push(myindexCont);
                            myindexHtml +='<li>' + myindexArray[i]  + '</li>';
                            myindexHtml_li = myindexArray[0]
                        }

                        //运算符下拉
                        var operatorData = dao.operator;//获取全部字符串格式的operator;
                        var operatorSplit = operatorData.split(",");//用','分割字符串
                        var operatorStr = JSON.stringify(operatorSplit);//将分割的字符串转换为符合JSON格式的字符串
                        var operatorJson = $.parseJSON(operatorStr);//将JSON格式的字符串换为JSON
                        var operatorArray = [];var operatorHtml = '';
                        for(var i=0;i<operatorJson.length;i++){
                            var operatorCont = operatorJson[i];
                            operatorArray.push(operatorCont);
                            operatorHtml +='<li>' + operatorArray[i]  + '</li>';
                        }
                        //范围下拉
                        var scopeData = dao.scope;//获取全部字符串格式的operator;
                        var scopeSplit = scopeData.split(",");//用','分割字符串
                        var scopeStr = JSON.stringify(scopeSplit);//将分割的字符串转换为符合JSON格式的字符串
                        var scopeJson = $.parseJSON(scopeStr);//将JSON格式的字符串换为JSON
                        var scopeArray = [];var scopeHtml = '';
                        for(var i=0;i<scopeJson.length;i++){
                            var scopeCont = scopeJson[i];
                            scopeArray.push(scopeCont);
                            scopeHtml +='<li>' + scopeArray[i]  + '</li>';
                        }
                        _html += '<tr>'
                            +'<td style="display:none;">'+ v[j].tdData0 +'</td>'
                            +'<td>'+ v[j].tdData1 +'</td>'
                            +'<td>'
                            +'<div class="nice_select" name="nice_select">  '
                            +'<input type="text" value="'+v[j].tdData2+'" readonly>'
                            +'<ul>' + myindexHtml + '</ul>'
                            +'</div>'
                            +'</td>'
                            +'<td>'
                            +'<div class="nice_select" name="nice_select">  '
                            +'<input type="text" value="'+v[j].tdData3+'" readonly>'
                            +'<ul>' + operatorHtml + '</ul>'
                            +'</div>'
                            +'</td>'
                            +'<td>'
                            +'<div class="nice_select" name="nice_select">'
                            +'<input type="text" value="'+ v[j].tdData4+'" readonly>'
                            +'<ul>'+ scopeHtml +'</ul> '
                            +'</div>'
                            +'</td>'
                            +'<td>'
                            +'<div class="nice_select selects" name="nice_select">'
                            +'<input type="text" value="'+ v[j].tdData5 +'">'
                            +'</div>'
                            +'</td>'
                            +'<td><a href="javascript:void(0);" class="redact" id="'+ v[j].tdData0 +'"></a></td>'
                            +'<td><a href="javascript:void(0);" class="deleteData" id="'+ v[j].tdData0 +'"></a></td>'
                            +'</tr>';
                        $("#creatStrategy_cont6 .creat_table").find('tbody').html(_html);
                    }
                    deleteData();//绑定删除方法
                    bind();//绑定下拉菜单
                    redacts();//获取用户输入过的值
				}
			 })
		});
	   },
	   error : function() {
	    alert("我的策略-策略生成器进入未成功");
	   }
	})
}

//所有指标的下拉选择框
function bind(){
    $('[name="nice_select"]').click(function(e){
        $('[name="nice_select"]').find('ul').hide();
        $(this).find('ul').show();
        e.stopPropagation();
    });
    $('[name="nice_select"] li').hover(function(e){
        $(this).toggleClass('on');
        e.stopPropagation();
    });
    $('[name="nice_select"] li').click(function(e){
        var val = $(this).text();
        $(this).parents('[name="nice_select"]').find('input').val(val);
        $('[name="nice_select"] ul').hide();
        e.stopPropagation();
    });

    $(document).click(function(){
        $('[name="nice_select"] ul').hide();
    });

    $(".append_left li").click(function(){
        var index = $(this).index();
        $(".append_left li").removeClass("hover").eq(index).addClass("hover");
        $(".app_tab").hide().eq(index).show();
    });

    $(".append_rcont i").hover(function(){
        $(this).find(".app_pop").toggle();
    });

}

//点击删除已增加的一条数据
function removeAddMap(id) {
    for(var i=0; i<addMap.length; i++) {
        if(addMap[i] == id) {
            addMap.splice(i, 1);
            break;
        }
    }
}
function deleteData(){
    $(".deleteData").on('click',function(){
        var deleteId = $(this).attr("id");
        if(addMap.indexOf(deleteId) != -1) {
            removeAddMap(deleteId);
        }
        $(this).parents("tr").remove();
    })
}

//点击编辑获取用户输入的值
function redacts(){
    $('.redact').on('click',function(){
        var redactId = $(this).attr("id");
    	$.each(dataArray,function(datak,datav) {
    		if(redactId == datav.id){
                var paraHtml='';var paraString = $(".redacts");
                var param = datav.paramemter;//获取全部字符串格式的param;
                var paramJson = $.parseJSON(param);//将JSON格式的字符串换为JSON
                if(paraHtml == '') {
					$.each(paramJson,function(paramemterK,paramemterV){
                        paraHtml+="<div class='redactcont'>";
						paraHtml+="<p>"+ paramemterV.name +"</p>";
						$.each(datav.userInput,function(k,v){
							if(k == paramemterK){
                                paraHtml+="<input type='text' value = '"+ v +"'/>";
							}else{
                                paraHtml+="<input type='text' value = ''/>";
                            }
						})
                        paraHtml+="</div>";
					});
				}
				paraString.html(paraHtml);
			}
        })
    })

}

