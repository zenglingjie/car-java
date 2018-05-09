$(function ($) {
		//弹出层
		$(".pop").on('click', function () {
			$("body").append("<div id='mask'></div>");
			$("#mask").addClass("mask").fadeIn("slow");
			$("#pop_index").fadeIn("slow");
		});
		$(".redact").on('click', function () {
			$("body").append("<div id='mask'></div>");
			$("#mask").addClass("mask").fadeIn("slow");
			$("#pop_redact").fadeIn("slow");
		});		
		//关闭
		$(".close_btn, button").hover(function () { 
			$(this).css({ color: 'fff' })
		}, function () { 
			$(this).css({ color: '#fff' }) 
		}).on('click', function () {
			$("#pop_index,#pop_redact").css({ display: 'none' });
			$("#mask").css({ display: 'none' });
		});
	});