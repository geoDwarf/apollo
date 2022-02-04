var map = L.map('regional-map', {
		crs: L.CRS.Simple
	});

	var bounds = [
	                [0, 0],
	                [1000, 1000]
	              ];
	var image = L.imageOverlay('/primaprova.jpg', bounds).addTo(map);

	map.fitBounds(bounds);