/**
 * @description : ajax 요청
 */
function sendRequest(request, uri, callBack){
	var xhr = new XMLHttpRequest();
	xhr.open(request.method, uri, true);
	if(request.contentType){
		xhr.setRequestHeader("Content-Type", request.contentType);
	}
	xhr.onreadystatechange = function () {
		if(xhr.readyState === 4 && xhr.status === 200){
			callBack(JSON.parse(xhr.responseText));
		}
	};

	xhr.send(request.data);
}