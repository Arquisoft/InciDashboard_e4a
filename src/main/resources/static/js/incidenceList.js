var eventSource = new EventSource("/getEmitter");

eventSource.addEventListener("newIncidence", function(event) {
	
	var obj =  JSON.parse(event.data);
	console.log(obj);
	console.log(obj.inciName);
	var tabla = document.getElementById("tablaIncidencias");
	var row = tabla.insertRow(-1);	
	var enlace = row.insertCell(-1);	
	var name = row.insertCell(-1);
	var location = row.insertCell(-1);
	var agent = row.insertCell(-1);
	var state = row.insertCell(-1);
	
	enlace.outerHTML = '<th><a href = "'+ obj.id +'">'+ obj.id + '</a></th>';
	name.outerHTML = '<th>' + obj.inciName + '</th>';
	location.outerHTML = '<th>'+ String(obj.location) + '</th>';
	agent.outerHTML = '<th>'+ String(obj.agent) +'</th>';
	state.outerHTML = '<th>' + obj.state + '</th>';
	
});