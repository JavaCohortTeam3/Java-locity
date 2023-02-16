"use strict"


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
        zoom: 0,
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
        if (input[0].value != undefined){
            document.getElementsByTagName("form")[1].submit()
        }
    })
})

let boolTripStartHasDate;
let boolTripEndHasDate;
let boolTripEndDateAfterTripStartDate;

document.getElementById("begin").addEventListener("change", checkTripStartDateHasLength);

function checkTripStartDateHasLength(){
    if (document.getElementById("begin").value.length != 10){
        document.getElementById("instructions_dates_start").style.visibility = "visible";
        boolTripStartHasDate = false;
        checkValidInput();
    } else {
        document.getElementById("instructions_dates_start").style.visibility = "hidden";
        boolTripStartHasDate = true;
        checkIfStartAndEndDatesHaveData();
    }
}

document.getElementById("end").addEventListener("change", checkTripEndDateHasLength);

function checkTripEndDateHasLength(){
    if (document.getElementById("end").value.length != 10){
        document.getElementById("instructions_dates_end").style.visibility = "visible";
        boolTripEndHasDate = false;
        checkValidInput();
    } else {
        document.getElementById("instructions_dates_end").style.visibility = "hidden";
        boolTripEndHasDate = true;
        checkIfStartAndEndDatesHaveData();
    }
}

function checkIfStartAndEndDatesHaveData(){
    if (boolTripEndHasDate && boolTripStartHasDate){
        checkIfEndDateAfterStartDate();
    }
}

function checkIfEndDateAfterStartDate(){
    if (document.getElementById("begin").value <= document.getElementById("end").value){
        document.getElementById("instructions_dates_end_after_start").style.visibility = "hidden";
        boolTripEndDateAfterTripStartDate = true;
        checkValidInput();
    } else {
        if (document.getElementById("end").value != null){
            document.getElementById("instructions_dates_end_after_start").style.visibility = "visible";
        }
        boolTripEndDateAfterTripStartDate = false;
        checkValidInput();
    }
}

let SubmitButton = document.getElementById('submission');
SubmitButton.setAttribute('disabled', true);
SubmitButton.style.opacity = '.2';

function checkValidInput(){
    if (boolTripStartHasDate && boolTripEndHasDate && boolTripEndDateAfterTripStartDate){
        SubmitButton.removeAttribute('disabled')
        SubmitButton.style.opacity = '1';
    } else {
        SubmitButton.setAttribute('disabled', true)
        SubmitButton.style.opacity = '.2';
    }
}

checkTripStartDateHasLength();
checkTripEndDateHasLength();
checkIfEndDateAfterStartDate();