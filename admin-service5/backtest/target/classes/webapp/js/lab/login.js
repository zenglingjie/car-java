$(function(){
      $("form").submit(function() {
          var v=md5($("#password").val());
          $("#password1").val(v);
          $("#password").attr("disabled", "true");
          return true;
      });

})
