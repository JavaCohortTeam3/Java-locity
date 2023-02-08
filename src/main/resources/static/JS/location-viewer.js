let html = ""
function locationViewer() {
    let hidden = document.getElementById("hidden").value
    const options = {method: 'GET', headers: {accept: 'application/json'}};
    console.log(hidden);
    fetch(`https://api.content.tripadvisor.com/api/v1/location/${hidden}/photos?key=36086683E4694CAFBB45F4197D8AE5D4&language=en`, options)
        .then(response => response.json())
        .then(response => {
            console.log(response)

            for (let i = 0; i < response.data.length; i++) {
                html = ""
                html += `<img src="${response.data[i].images.large.url}">`
                document.getElementById("display").innerHTML += html
            }
        })
        .catch(err => console.error(err));
}
locationViewer()

function addTitle() {
    let hidden = document.getElementById("hidden").value

    const options = {method: 'GET', headers: {accept: 'application/json'}};

    fetch(`https://api.content.tripadvisor.com/api/v1/location/${hidden}/details?key=36086683E4694CAFBB45F4197D8AE5D4&language=en&currency=USD`, options)
        .then(response => response.json())
        .then(response => {
            console.log(response)
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