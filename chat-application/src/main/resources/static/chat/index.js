var sse = new EventSource("/chat/sse");

sse.onmessage = function (e) {
	var datas = e.data.split("\n");
	console.log(datas);
};

/* you can use if you have more events than 1
sse.addEventListener("event-name",callBack)
*/