var eventSource = new EventSource("/getEmitter");

eventSource.addEventListener("newIncidence", function(event) {
	
	location.reload();
	
});