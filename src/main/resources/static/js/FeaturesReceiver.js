var FeaturesReceiver = function () {};

FeaturesReceiver.prototype = function (){

    var geojsonMarkerOptions = {
        radius: 6,
        fillColor: "#ff7800",
        color: "#000",
        weight: 1,
        opacity: 1,
        fillOpacity: 0.8
    };

    var backEndCallToGetPoints = function (){


        $.ajax({
            type: 'GET',
            url: '/getpoints',
            dataType: 'json',
            success: function (data) {
                var receivedCoordinates = [];
                var i;
                for (i = 0; i < data.length; i++) {
                    receivedCoordinates.push([data[i].x, data[i].y ]);
                }
                multiPointGeometry.coordinates = receivedCoordinates;
                defaultPointFeature.geometry = multiPointGeometry;

                L.geoJSON(defaultPointFeature, {
                        pointToLayer: function (feature, latlng) {
                        return L.circleMarker(latlng, geojsonMarkerOptions);
                    }
                }).addTo(mymap);

            }
        });
    };

    var backEndCallToGetLines = "this function is not implemented yet";

    var backEndCallToGetPolygons =  "this function is not implemented yet";;

    return {backEndCallToGetPoints:backEndCallToGetPoints,
            backEndCallToGetLines:backEndCallToGetLines,
            backEndCallToGetPolygons:backEndCallToGetPolygons}
}();