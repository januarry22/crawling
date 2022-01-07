/**
 * AJAX 공통소스
 */
function ajaxCallPost(url, param, callbackSuccess, callbackFail){
	console.log("=========================================================")
	console.log("endPoint : " + url);
	console.log(param);
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		dataType : "JSON",    //옵션이므로 JSON으로 받을게 아니면 안써도 됨
		data : JSON.stringify(param),
		success : function(res) {
			console.log(res);
    		console.log("=========================================================")
			callbackSuccess(res);
		},
		error : function(xhr, status, error) {
			console.log(error);
    		console.log("=========================================================")
			callbackFail(error);
		}
	});
}
function ajaxCallPostToken(url, authToken, param, callbackSuccess, callbackFail){
	console.log("=========================================================")
	console.log("endPoint : " + url);
	console.log("authToken : " + authToken);
	console.log(param);
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json",
		dataType : "JSON",    //옵션이므로 JSON으로 받을게 아니면 안써도 됨
		data : JSON.stringify(param),
		beforeSend : function(header){ 
			header.setRequestHeader("authToken", authToken);
		},
		success : function(res) {
			console.log(res);
    		console.log("=========================================================")
    		if(res.errorCode && res.erroMsg){
    			location.href="https://www.cliv.co.kr";
    		}else{
    			callbackSuccess(res);
    		}
		},
		error : function(xhr, status, error) {
			console.log(error);
    		console.log("=========================================================")
			callbackFail(error);
		}
	});
}
function ajaxCallGet(url, callbackSuccess, callbackFail){
	console.log("=========================================================")
	console.log("endPoint : " + url);
	$.ajax({
		type : "GET",
		url : url,
		contentType : "application/json",
		success : function(res) {
			console.log(res);
    		console.log("=========================================================")
			callbackSuccess(res);
		},
		error : function(xhr, status, error) {
			console.log(error);
    		console.log("=========================================================")
			callbackFail(error);
		}
		,beforeSend:function(){
			
			if(url.indexOf("/api/v1/recommend/") > -1){
				 $('.loading').show();
					$(".pop_bg").css("height", document.body.scrollHeight+"px");
					$(".pop_bg").css("opacity", "0.6");
					$(".pop_bg").show();
			}
			
	    }
	    ,complete:function(){
	    	if(url.indexOf("/api/v1/recommend/") > -1){
	    		
	    		setTimeout(function(){
	    			$('.loading').hide();
					$(".pop_bg").css("height", document.body.scrollHeight+"px");
					$(".pop_bg").css("opacity", "0.9");
					$(".pop_bg").hide();
	    		}, 3000)
	    	}
	    }
	});
}