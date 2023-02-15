"use strict"

let token = "pk.eyJ1IjoibWF0dGhld3dpcmFtIiwiYSI6ImNsOWx2YmJwODFtMnEzdXAyMDFvdHRxcHcifQ.20mYzJo1wfnNRyCTEJMtyw"
let key = "[[${key}]]";
alert(key)
let lat;
let lng;

let input;

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
        zoom: 10,
        center: [-20, 80]

    });

    map.addControl(
        new MapboxGeocoder({
            accessToken: mapboxgl.accessToken,
            mapboxgl: mapboxgl
        })
    );











let submit = document.getElementById("submission")
submit.addEventListener("click", function (e) {

    e.preventDefault()
    let input = document.getElementsByClassName("mapboxgl-ctrl-geocoder--input")
    let data = geocode(input[0].value, token);
    data.then(res => {

        document.getElementById("location").value = res[0].center[1] + "," +res[0].center[0]
        document.getElementById("title").value = input[0].value
        console.log(document.getElementById("location").value);
        document.getElementsByTagName("form")[1].submit()
    })
})










