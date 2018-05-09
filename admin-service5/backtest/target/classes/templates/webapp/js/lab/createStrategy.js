var addMap = [];
var dataArray = {};

//策略生成器默认显示值
function defaults(){
    var defaultCreat = $(".creatStrategy .creatStrategy_cont li.creat_select_li");
    for(var i=0; i<defaultCreat.length; i++){
        var defaultNumber = defaultCreat[i];
        var creatHtml = $(defaultNumber).find("ul li:first").html();
        $(defaultNumber).find(".nice_select input").attr("value", creatHtml);
    }
}
$(function(){
    init();
    getlist2(1);getlist2(2);getlist2(3);getlist2(4);
    getlist1(1);getlist1(2);getlist1(3);getlist1(4);
    getlist1(5);getlist1(6);getlist1(7);getlist1(8);
    getlist1(9);

    defaults();//策略生成器默认显示值

    //点击确认按钮把已勾选的数据增加到指定页面
    $('.creatStrategy_cont .tj').on('click',function(){
        if($('.creatStrategy_cont').attr('flage',1)){
            $(this).parents('.creatStrategy_cont').attr('flage',2);
        }else{
            $(this).parents('.creatStrategy_cont').attr('flage',1);
        }
    })
    $('#confire').unbind('click').bind('click',function(){
        var idList = [];
        $('.append_right .app_tab').each(function(){
            var _this = $(this);
            if(!_this.is(":hidden")){
                $(".app_tab .append_rcont").each(function(){
                    var Dao = {};
                    var _id = $(this).find('label').attr('_id');
                    var _name = $(this).find('label').attr('_name');
                    var _operator = $(this).find('label').attr('_operator');
                    var _scope = $(this).find('label').attr('_scope');
                    var _paramemter = $(this).find('label').attr('_paramemter');
                    var _myindex = $(this).find('label').attr('_myindex');
                    var _mytype = $(this).find('label').attr('_mytype');
                    var _checked = $(this).find("input[type='checkbox']").is(":checked");
                    Dao['id'] = _id;
                    Dao['name'] = _name;
                    Dao['operator'] = _operator;
                    Dao['scope'] = _scope;
                    Dao['paramemter'] = _paramemter;
                    Dao['myindex'] = _myindex;
                    Dao['mytype'] = _mytype;
                    Dao['checked'] = _checked;
                    idList.push(Dao);
                })
            }
        })

        $('.creatStrategy_cont').each(function(){
            var _html = '';var paramemter;
            if($(this).attr('flage')=='2'){
                $.each(idList,function(k,v){
                    //运算符下拉
                    var operatorData = v.operator;//获取全部字符串格式的operator;
                    var operatorSplit = operatorData.split(",");//用','分割字符串
                    var operatorStr = JSON.stringify(operatorSplit);//将分割的字符串转换为符合JSON格式的字符串
                    var operatorJson = $.parseJSON(operatorStr);//将JSON格式的字符串换为JSON
                    var operatorArray = [];var operatorHtml = '';
                    var operatorHtml_li;
                    for(var i=0;i<operatorJson.length;i++){
                        var operatorCont = operatorJson[i];
                        operatorArray.push(operatorCont);
                        operatorHtml +='<li>' + operatorArray[i]  + '</li>';
                        operatorHtml_li = operatorArray[0]
                    }

                    //范围下拉
                    var scopeData = v.scope;//获取全部字符串格式的operator;
                    var scopeSplit = scopeData.split(",");//用','分割字符串
                    var scopeStr = JSON.stringify(scopeSplit);//将分割的字符串转换为符合JSON格式的字符串
                    var scopeJson = $.parseJSON(scopeStr);//将JSON格式的字符串换为JSON
                    var scopeArray = [];var scopeHtml = '';
                    var scopeHtml_li;
                    for(var i=0;i<scopeJson.length;i++){
                        var scopeCont = scopeJson[i];
                        scopeArray.push(scopeCont);
                        scopeHtml +='<li>' + scopeArray[i]  + '</li>';
                        scopeHtml_li = scopeArray[0]
                    }

                    //参数下拉
                    paramemter = idList;

                    //指数下拉
                    var myindexData = v.myindex;//获取全部字符串格式的operator;
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
                    if(v.checked == true){
                        if(addMap.indexOf(v.id) == -1){
                            addMap.push(v.id)
                        }
                        if(v.mytype == "4"){
                            _html += '<tr id='+v.id+' flage=1>'
                                +'<td style="display:none;">'+v.id+'</td>'
                                +'<td>'+ v.name +'</td>'
                                +'<td>'
                                +'<div class="nice_select" name="nice_select">  '
                                +'<input type="text" value="'+ myindexHtml_li +'" readonly>'
                                +'<ul>' + myindexHtml + '</ul>'
                                +'</div>'
                                +'</td>'
                                +'<td>'
                                +'<div class="nice_select" name="nice_select">  '
                                +'<input type="text" value="'+ operatorHtml_li +'" readonly>'
                                +'<ul>' + operatorHtml + '</ul>'
                                +'</div>'
                                +'</td>'
                                +'<td>'
                                +'<div class="nice_select" name="nice_select">'
                                +'<input type="text" value="'+ scopeHtml_li +'" readonly>'
                                +'<ul>'+ scopeHtml +'</ul> '
                                +'</div>'
                                +'</td>'
                                +'<td>'
                                +'<div class="nice_select selects" name="nice_select">'
                                +'<input type="text" value="10">'
                                +'</div>'
                                +'</td>'
                                +'<td><a id='+v.id+' href="javascript:void(0);" class="redact"></a></td>'
                                +'<td><a id='+v.id+' href="javascript:void(0);" class="deleteData"></a></td>'
                                +'</tr>';

                        }else{
                            _html += '<tr id='+v.id+' flage=1>'
                                +'<td style="display:none;">'+v.id+'</td>'
                                +'<td>'+ v.name +'</td>'
                                +'<td>'
                                +'<div class="nice_select" name="nice_select">  '
                                +'<input type="text" value="'+ operatorHtml_li +'" readonly>'
                                +'<ul>' + operatorHtml + '</ul>'
                                +'</div>'
                                +'</td>'
                                +'<td>'
                                +'<div class="nice_select" name="nice_select">'
                                +'<input type="text" value="'+ scopeHtml_li +'" readonly>'
                                +'<ul>'+ scopeHtml +'</ul> '
                                +'</div>'
                                +'</td>'
                                +'<td>'
                                +'<div class="nice_select selects" name="nice_select">'
                                +'<input type="text" value="10">'
                                +'</div>'
                                +'</td>'
                                +'<td><a id='+v.id+' href="javascript:void(0);" class="redact"></a></td>'
                                +'<td><a id='+v.id+' href="javascript:void(0);" class="deleteData"></a></td>'
                                +'</tr>';
                        }
                    }else{
                        //删除addMap中已经存在的值
                        if(addMap.indexOf(v.id) != -1){
                            var addMapDelete = v.id;
                            addMap.splice($.inArray(addMapDelete,addMap),1);
                        }
                    }
                });
                $(this).find('tbody').html(_html);
                bind();
                redact(paramemter);
                deleteData();
            }
        })
    })

    //清除已生成的数据
    $(".close_btn,#confire").on('click',function(){
        $(".app_tab").remove();
    })
	strategyBuilder($('#sid').val(), false);
})

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

function init() {
    //日历插件
    laydate({
        elem: '#test1',
        format: 'YYYY-MM-DD',
        festival: true,
    });
    laydate({
        elem: '#test2',
        format: 'YYYY-MM-DD',
        festival: true,
    });

    //图表展示方法
    //stock.render();

}

//交易设置，股票池
var dataMap = [];
function getlist1(param) {
    $.ajax({
        url : hosturl + "/select/tactics", // 后台处理接口
        timeout : 15000,
        type : "get", // 数据接收方式
        async : false,
        data : {
            "type" : param
        },
        dataType : "json", // 接受数据格式
        success : function(data) {
            var stringId= $("#ul"+ data.type);
            var dataHtml = "";
            $.each(data.list, function(i,val){
                // if(dataMap.indexOf(val.name) == -1) {
                dataHtml += "<li>" + val.name + "</li>"
                dataMap.push(val.name);
            });		  	// }

            stringId.append(dataHtml);
            bind();
        },
        error : function() {
            alert("抱歉！服务器发生错误123");
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

//将页面选中的值用ajax的方式返回给后台
function getBackTestJson(){
    var backTestJson = {};
    var transactionSet = $("#creatStrategy_cont1 h2 span").html();
    var stockPool = $("#creatStrategy_cont2 h2 span").html();
    var ulList = $(".creat_select").children("ul");
    var liData8 = $("#ddd").find("input[type='checkbox']").is(":checked")
    for (var j=0;j<ulList.length;j++){
        var liArr = ulList.eq(j).find("li").find(".nice_select");
        if(j<1){
            var liData=[
                {liData1 : liArr.eq(0).find("input").val(),
                    liData2 : liArr.eq(1).find("input").val(),
                    liData3 : liArr.eq(2).find("input").val(),
                    liData4 : liArr.eq(3).find("input").val(),
                    liData5 : liArr.eq(4).find("input").val(),
                    liData6 : liArr.eq(5).find("input").val(),
                    liData7 : liArr.eq(6).find("input").val()}
            ];
            backTestJson[transactionSet] = liData;
        }else{
            var liData=[
                {liData1 : liArr.eq(0).find("input").val(),
                    liData2 : liArr.eq(1).find("input").val(),
                    liData3 : liArr.eq(2).find("input").val(),
                    liData4 : liArr.eq(3).find("input").val(),
                    liData5 : liArr.eq(4).find("input").val(),
                    liData6 : liData8}
            ]
            backTestJson[stockPool] = liData;
        }
    }

    var tdDataArray3 = [];	var tdDataArray4 = [];	var tdDataArray5 = [];	var tdDataArray6 = [];
    var trList3 = $("#creatStrategy_cont3 tbody").children("tr");
    var trList4 = $("#creatStrategy_cont4 tbody").children("tr");
    var trList5 = $("#creatStrategy_cont5 tbody").children("tr");
    var trList6 = $("#creatStrategy_cont6 tbody").children("tr");
    var chooseCondition = $("#creatStrategy_cont3 h2 span").html();
    var sortOrder = $("#creatStrategy_cont4 h2 span").html();
    var stocksStop = $("#creatStrategy_cont5 h2 span").html();
    var marketstop = $("#creatStrategy_cont6 h2 span").html();
    for (var i=0;i<trList3.length;i++) {
        var tdArr = trList3.eq(i).find("td");
        var trId3 = tdArr.eq(0).html();
        var dataId3 = dataArray[trId3];
        var userId3 = dataId3.userInput;
        var tdData={tdData0 : tdArr.eq(0).html(),
            tdData1 : tdArr.eq(1).html(),
            tdData2 : tdArr.eq(2).find("input").val(),
            tdData3 : tdArr.eq(3).find("input").val(),
            tdData4 : tdArr.eq(4).find("input").val(),
            tdData5 : userId3}
        tdDataArray3.push(tdData);
    }
    for (var i=0;i<trList4.length;i++) {
        var tdArr = trList4.eq(i).find("td");
        var trId4 = tdArr.eq(0).html();
        var dataId4 = dataArray[trId4];
        var userId4 = dataId4.userInput;
        var tdData={tdData0 : tdArr.eq(0).html(),
            tdData1 : tdArr.eq(1).html(),
            tdData2 : tdArr.eq(2).find("input").val(),
            tdData3 : tdArr.eq(3).find("input").val(),
            tdData4 : tdArr.eq(4).find("input").val(),
            tdData5 : userId4}
        tdDataArray4.push(tdData);
    }
    for (var i=0;i<trList5.length;i++) {
        var tdArr = trList5.eq(i).find("td");
        var trId5 = tdArr.eq(0).html();
        var dataId5 = dataArray[trId5];
        var userId5 = dataId5.userInput;
        var tdData={tdData0 : tdArr.eq(0).html(),
            tdData1 : tdArr.eq(1).html(),
            tdData2 : tdArr.eq(2).find("input").val(),
            tdData3 : tdArr.eq(3).find("input").val(),
            tdData4 : tdArr.eq(4).find("input").val(),
            tdData5 : userId5}
        tdDataArray5.push(tdData);
    }
    for (var i=0;i<trList6.length;i++) {
        var tdArr = trList6.eq(i).find("td");
        var trId6 = tdArr.eq(0).html();
        var dataId6 = dataArray[trId6];
        var userId6 = dataId6.userInput;
        var tdData={tdData0 : tdArr.eq(0).html(),
            tdData1 : tdArr.eq(1).html(),
            tdData2 : tdArr.eq(2).find("input").val(),
            tdData3 : tdArr.eq(3).find("input").val(),
            tdData4 : tdArr.eq(4).find("input").val(),
            tdData5 : tdArr.eq(5).find("input").val(),
            tdData6 : userId6}
        tdDataArray6.push(tdData);
    }
    backTestJson[chooseCondition] = tdDataArray3;
    backTestJson[sortOrder] = tdDataArray4;
    backTestJson[stocksStop] = tdDataArray5;
    backTestJson[marketstop] = tdDataArray6;
    return JSON.stringify(backTestJson);

}

//点击编辑已增加的一条数据
function redact(paramemter){
    $(".redact").on('click', function (){
        $("body").append("<div id='mask'></div>");
        $("#mask").addClass("mask").fadeIn("slow");
        $("#pop_redact").fadeIn("slow");
    });
    $('.redact').on('click',function(){
        var redactId = $(this).attr("id");
        $.each(paramemter, function(paramemterk,paramemterV) {
            var paraHtml='';var paraString = $(".redacts");
            if(paraHtml == ''){
                var paramemterData = paramemterV.paramemter;//获取全部字符串格式的paramemter;
                var paramemterJson = $.parseJSON(paramemterData);//将JSON格式的字符串换为JSON
                var redactDao = dataArray[redactId];
                var redactInput = redactDao.userInput;
                if(redactId == paramemterV.id){
                    $.each(paramemterJson,function(parakey,paravalue){
                        paraHtml+="<div class='redactcont' id='"+ paramemterV.id +"'>";
                        paraHtml+="<p>"+ paravalue.name +"</p>";
                        //判断dataArray里面是否有redactInput的值
                        if(!redactInput){
                            paraHtml+="<input type='text' value = '"+ paravalue.defaultValue +"' id='"+ parakey +"'/>";
                        }else{
                            $.each(redactInput, function(redactInputK,redactInputV) {
                                if(parakey==redactInputK){
                                    paraHtml+="<input type='text' value = '"+ redactInputV +"' id='"+ parakey +"'/>";
                                }
                            });
                        }
                        paraHtml+="</div>";
                    });
                    paraString.html(paraHtml);

                    //点击确认向dataArray增加用户修改过的参数
                    $(".confires").on("click",function(){
                        var redactIds = $(this).parent().find(".redacts .redactcont").attr("id");
                        var _userInput = {};var dao = dataArray[redactIds];
                        $.each(paramemter, function(paramemterk,paramemterV) {
                            var paramemterData = paramemterV.paramemter;//获取全部字符串格式的paramemter;
                            var paramemterJson = $.parseJSON(paramemterData);//将JSON格式的字符串换为JSON
                            $.each(paramemterJson,function(parakey,paravalue){
                                _userInput[parakey]  = $("#"+ parakey).val();
                            });
                        });
                        dao['userInput'] = _userInput;
                    })
                }
            }

        });

    })

}
