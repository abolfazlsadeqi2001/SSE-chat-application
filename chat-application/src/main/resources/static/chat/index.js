var sse = new EventSource("/chat/sse");

sse.onmessage = function (e) {
	var datas = e.data.split("\n");
	
	var legendElement = document.createElement("legend");
	legendElement.innerHTML = datas[0];
	
	var fieldSetElement = document.createElement("fieldSet");
	fieldSetElement.appendChild(legendElement);
	fieldSetElement.innerHTML += datas[1];
	
	var messageContainerDiv = document.querySelector("div.message-container");
	messageContainerDiv.appendChild(fieldSetElement);
};

function send (){
	var textAreaElement = document.querySelector("textarea");
	
	$.post("/new/message",{message:textAreaElement.value},function(e){
		textAreaElement.value = "";
	});
}

/* you can use if you have more events than 1
sse.addEventListener("event-name",callBack)
*/