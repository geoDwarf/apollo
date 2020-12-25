var FeaturesReceiver = function () {};

FeaturesReceiver.prototype = function (){


    var backEndCallToGetPoints = function (){
        console.log('document ready');
        $.ajax({
            type: 'GET',
            url: '/getpoints',
            dataType: 'json',
            success: function (data) {
                var receivedCoordinates = [[data[0].x, data[0].y ],[data[1].x, data[1].y]];
                multiPointGeometry.coordinates = receivedCoordinates;
                defaultPointFeature.geometry = multiPointGeometry;

                L.geoJSON(defaultPointFeature, {
                    style: myStyle
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