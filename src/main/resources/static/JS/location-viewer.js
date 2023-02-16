let html = ""
let key = document.getElementById("tripKey").value
function locationViewer() {
    let hidden = document.getElementById("hidden").value
    const options = {method: 'GET', headers: {accept: 'application/json'}};
    console.log(hidden);
    fetch(`https://corsanywhere.herokuapp.com/https://api.content.tripadvisor.com/api/v1/location/${hidden}/photos?key=${key}&language=en`, options)
        .then(response => response.json())
        .then(response => {
            console.log(response);
            document.getElementById("picture").value = response.data[0].images.large.url
            for (let i = 0; i < response.data.length; i++) {
                setTimeout(function () {
                    html = ""
                    html += `<img class="imgLocationViewer" src="${response.data[i].images.large.url}">`
                    document.getElementById("display").innerHTML += html
                }, 100)

            }
        })
        .catch(err => console.error(err));
}
locationViewer()

function addTitle() {
    let hidden = document.getElementById("hidden").value

    const options = {method: 'GET', headers: {accept: 'application/json'}};

    fetch(`https://corsanywhere.herokuapp.com/https://api.content.tripadvisor.com/api/v1/location/${hidden}/details?key=${key}&language=en&currency=USD`, options)
        .then(response => response.json())
        .then(response => {
            console.log(response);
            let title = document.getElementById("title")
            let html = ""
            html += `<h4>${response.name}</h4>`
            html += `<h6>${response.address_obj.address_string}</h6>`
            html += `<h6>Phone: ${response.phone}</h6>`
            html += `<h6>Rating: ${response.rating}</h6>`
            html += `<h6>Website: <a href="${response.website}">${response.website}</a></h6>`
            title.innerHTML = html
            document.getElementById("name").value = response.name
            document.getElementById("web_url").value = response.web_url
            document.getElementById("address_string").value = response.address_obj.address_string
            document.getElementById("latitude").value = response.latitude
            document.getElementById("longitude").value = response.longitude
            document.getElementById("email").value = response.email
            document.getElementById("phone").value = response.phone
            document.getElementById("rating").value = response.rating



        })
        .catch(err => console.error(err));
}

addTitle()