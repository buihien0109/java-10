var options = {
    method: 'GET',
    url: 'https://theaudiodb.p.rapidapi.com/searchalbum.php',
    params: { s: 'daft_punk' },
    headers: {
        'x-rapidapi-host': 'theaudiodb.p.rapidapi.com',
        'x-rapidapi-key': 'e01c2fe289mshccdde0e629047f3p108077jsn98e1483aaf3c'
    }
};

axios.request(options).then(function (response) {
    console.log(response.data);
}).catch(function (error) {
    console.error(error);
});