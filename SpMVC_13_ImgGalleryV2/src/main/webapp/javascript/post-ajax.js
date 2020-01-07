var img_context = function(key, option){
	let img_file_seq = this.attr("data-id")
	if(key == 'delete'){
		if(confirm(img_file_seq + "이미지를 삭제할까요?")) {
			
			$.post("${rootPath}/rest/image_delete", 
					{data:{img_id:img_id}},
					function(result){
						if(result == "OK"){
							document.location.replace(document.location.href)
						}else{
							alert("파일을 삭제할 수 없음")
						}		
			})
			document.location.replace("${rootPath}/")
			
			return false;
		}
	} else if(key == "copy"){
		
	} else if(key == "loading"){
		
	}
	
}