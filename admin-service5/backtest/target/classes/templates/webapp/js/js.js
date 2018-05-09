// var hosturl = "http://172.18.44.101:8008"
// var weburl = "172.18.44.101:8008"
var hosturl = "http://backtest"
var weburl = "backtest"
// var hosturl = "http://backtestdev"
// var weburl = "backtestdev"
$(function () {
	$('#logouturl').attr("href", hosturl + '/logout.html');
    $('#loginurl').attr("href", hosturl + '/login.html');
	//下拉菜单
    $(".menu li").hover(function () {
        $(this).find(".downMenu").stop(true,true).fadeIn();
    },function(){
        $(this).find(".downMenu").stop(true,true).fadeOut();
    });
	
	//输入框消失显示
	$(".login_incont .input_txt").each(function(){
     var thisVal=$(this).val();
     //判断文本框的值是否为空，有值的情况就隐藏提示语，没有值就显示
     if(thisVal!=""){
       $(this).siblings("label").hide();
      }else{
       $(this).siblings("label").show();
      }
     //聚焦型输入框验证 
     $(this).on("focus",function(){
       $(this).siblings("label").hide();
      }).blur(function(){
        var val=$(this).val();
        if(val!=""){
         $(this).siblings("label").hide();
        }else{
         $(this).siblings("label").show();
        } 
      });
    })
	
	//登录界面切换
	$(".login_top a").on("click",function(){
        var index = $(this).index();
        $(".login_top a").removeClass("focus").eq(index).addClass("focus");
		$(".login_incont").hide().eq(index).show();
    });
	
	//我的策略界面切换
	$(".myStrategy_left a").on("click",function(){
        var index = $(this).index();
        $(".myStrategy_left a").removeClass("focus").eq(index).addClass("focus");
		$(".ind_wrap").hide().eq(index).show();
    });

	//回测结果左菜单切换
	$(".testResults_left .test_list li").on("click",function(){
        var index = $(this).index();
        $(".testResults_left .test_list li").removeClass("add_li").eq(index).addClass("add_li");
		$(".testResults_right").hide().eq(index).show();
    });
	
	//创建策略下拉切换
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

	$(".de_icon").on("click",function(){
		$(this).parents(".clearfix").remove();
	})

    $(".append_left li").click(function(){
        var index = $(this).index();
        $(".append_left li").removeClass("hover").eq(index).addClass("hover");
        $(".app_tab").hide().eq(index).show();
    });




//	$(".testResults_left .test_list .first_li").on("click",function(e){
//		if(e||e.stopPropagation){
//			e.stopPropagation();
//		}
//		else{
//			window.event.cancelBubble=true;
//		}
//		$(this).addClass("add_li").siblings().removeClass("add_li");
//		var idx =$(this).index();
//		//$(this).parents(".test_list").find(".test_ul").stop(true,false).slideUp().eq(idx).stop(true,false).slideDown();
//		
//      $(".testResults_right").removeClass("list_show").eq(idx).addClass("list_show");
//		
//		$(this).find(".test_ul_li").on("click",function(e){
//			if(e||e.stopPropagation){
//				e.stopPropagation();
//			}
//			else{
//				window.event.cancelBubble=true;
//			}
//			$(this).addClass("add_li").siblings().removeClass("add_li");
//			var inx =$(this).index();                
//			$(".clsy").removeClass("clsys").eq(inx).addClass("clsys");
//		})
//	});
	
	//日志界面切换
	$(".editStrategylog a").on("click",function(){
        var index = $(this).index();
        $(".editStrategylog a").removeClass("focus").eq(index).addClass("focus");
		$(".editStrategylog_cont").hide().eq(index).show();
   	});  
    
    //高度度随屏幕变化
	$(document).ready(function(){			
		var winHeight = $(window).height();// 取窗口高度	
		var winWidth = $(window).width();// 取窗口宽度	
	    var x = winHeight - 180;	    
	    var y = winHeight - 130;
	    var y1 = winHeight - 575;
	    var y2 = winHeight - 220;
	    var y3 = winHeight -50-86;
	    $(".creatStrategy").css("height",x);
	    $(".editStrategy_left").css("height",y);
	    $(".ace_content").css("height",y);
	    $(".logCont").css("height",y1);
	    $(".logDetails").css("height",y2);
	    $(".login_content").css("height",y3);
	});
	
	$(window).resize(function() {
	    var winHeight = $(window).height();// 取窗口高度	
		var winWidth = $(window).width();// 取窗口宽度	
	    var x = winHeight - 180;
	    var y = winHeight - 130;
	    var y1 = winHeight - 575;
	    $(".creatStrategy").css("height",x);
	    $(".editStrategy_left").css("height",y);
	    $(".ace_content").css("height",y);
	    $(".logCont").css("height",y1)
	});
	
	
	

});




