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