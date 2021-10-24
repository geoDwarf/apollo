
var mymap = L.map('mapid').setView([42.0505, 12.09], 10);
function getDefaultFeatures(){
 $.ajax({
         'url' : 'http://localhost:8080/getpoints',
        'type' : 'GET',
        'success' : function(data) {
            L.geoJSON(loadPoints(data), {
                                              style: myStyle
                                          }).addTo(mymap);
        },
        'error' : function(request,error)
        {
            alert("Request: "+JSON.stringify(request));
        }
    });
}


var myStyle = {
    "color": "#ff7800",
    "weight": 5,
    "opacity": 0.65
};
	L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		id: 'mapbox/streets-v11',
		tileSize: 512,
		zoomOffset: -1
	}).addTo(mymap);

