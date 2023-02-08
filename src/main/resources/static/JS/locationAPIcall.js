"use strict"

let token = "pk.eyJ1IjoibWF0dGhld3dpcmFtIiwiYSI6ImNsOWx2YmJwODFtMnEzdXAyMDFvdHRxcHcifQ.20mYzJo1wfnNRyCTEJMtyw"




function geocode(search, token) {
    var baseUrl = 'https://api.mapbox.com';
    var endPoint = '/geocoding/v5/mapbox.places/';
    return fetch(baseUrl + endPoint + encodeURIComponent(search) + '.json' + "?" + 'access_token=' + token)
        .then(function(res) {
            return res.json();
            // to get all the data from the request, comment out the following three lines...
        }).then(function(data) {
            console.log(data.features)

            return data.features;
        });
}

mapboxgl.accessToken = token;

var map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/streets-v12',
    zoom: 5,
    center: [-80.175652, 33.018505]

});

map.addControl(
    new MapboxGeocoder({
        accessToken: mapboxgl.accessToken,
        mapboxgl: mapboxgl
    })
);
map.addControl(
    new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
// When active the map will receive updates to the device's location as it changes.
        trackUserLocation: true,
// Draw an arrow next to the location dot to indicate which direction the device is heading.
        showUserHeading: true
    })
);


let input = document.getElementsByClassName("mapboxgl-ctrl-geocoder--input")




    console.log(input[0].value)
let submit = document.getElementById("submission")
submit.addEventListener("click", function (e) {

    e.preventDefault()
    let data = geocode(input[0].value, token);
    data.then(res => {
        document.getElementById("location").value = res[0].center[1] + "," +res[0].center[0]
        console.log(document.getElementById("location").value);
        document.getElementsByTagName("form")[1].submit()
    })
})










