let html = "";
let array = []
function getDetails(lat, long, details) {
    const options = {method: 'GET', headers: {accept: 'application/json'}};
    console.log(details);
    fetch(`https://corsanywhere.herokuapp.com/https://api.content.tripadvisor.com/api/v1/location/nearby_search?latLong=${lat}%2C${long}&key=36086683E4694CAFBB45F4197D8AE5D4&category=${details}&radius=25&radiusUnit=mi&language=en`, options)
        .then(response => response.json())
        .then(response => {
            console.log(response)
            array = []
            for (let i = 0; i < 5; i++) {
                setTimeout(function () {
                    array.push(response.data[i].location_id)
                }, 100)

            }

        getPics(array)

        })
        .catch(err => console.error(err));
}

let hidden  = document.getElementById("hidden").value
document.getElementById("details").addEventListener("change", function () {
    let details = document.getElementById("details").value
    if (details === "") {
        document.getElementById("holder").innerHTML = ""
    } else {


        getDetails(hidden.split(",")[0], hidden.split(",")[1], details)
    }

})

function getPics(array) {
    console.log(array);
    document.getElementById("holder").innerHTML = ""

    for (let i = 0; i < 5; i++) {
        setTimeout(function () {
            const options = {method: 'GET', headers: {accept: 'application/json'}};

            fetch(`https://corsanywhere.herokuapp.com/https://api.content.tripadvisor.com/api/v1/location/${array[i]}/details?key=36086683E4694CAFBB45F4197D8AE5D4&language=en&currency=USD`, options)
                .then(response => response.json())
                .then(response => {
                    console.log(response)

                    html = ""

                    html += `<div class ="container">`
                    html += `<button class="btn bg-warning btn-outline-dark m-2" name="idd" value="${response.location_id}">`
                    html += `<h4>${response.name}</h4>`
                    html += `<h6>Rating: ${response.rating}</h6>`
                    html += `<h6>${response.address_obj.address_string}</h6>`
                    html += `</button>`
                    html += `</div>`

                    document.getElementById("holder").innerHTML += html


                })
                .catch(err => console.error(err));

        }, 100 )

    }


}