//TODO finish to parse in JSON the String arriving from the Back end. Consider making the java controller returning an array of points instead of a string

    var geojsonFeature = {
                       "type": "FeatureCollection",
                       "features":[
  ]};

function loadPoints(data){
    var JSONArray = JSON.parse(data);
    populateGeoJson(JSONArray);
    return geojsonFeature;
}

  function populateGeoJson(pointsCollection){
    for (i = 0; i < pointsCollection.length; i++){
        geojsonFeature.features.push({
              "type": "Feature",
              "properties": {
                  "name": "Coors Field",
                  "amenity": "Baseball Stadium",
                  "popupContent": "This is where the Rockies play!"
                   },
              "geometry": {
                  "type": "Point",
                  "coordinates":[pointsCollection[i].x,pointsCollection[i].y]
                  }
             });
            }
   }