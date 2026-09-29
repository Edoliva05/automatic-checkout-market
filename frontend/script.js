document.getElementById('btn-new-cart').addEventListener('click', e => {
    axios.post("http://localhost:8080/cassa-automatica/newCart").then(
        (response) => {
            var result = response.data;
            console.log(result);
        },
        (error) => {
            console.log(error);
        }
    );
});

document.getElementById('btn-checkout').addEventListener('click', e => {
    axios.post("http://localhost:8080/cassa-automatica/checkout").then(
        (response) => {
            var result = response.data;
            console.log(result);
        },
        (error) => {
            console.log(error);
        }
    );
});

document.getElementById('btn-send-scan').addEventListener('click', e => {

    const barcode = document.getElementById('input-barcode').value;
    
    axios.post(`http://localhost:8080/cassa-automatica/scan/${barcode}`).then(
        (response) => {
            var result = response.data;
            console.log(result);
        },
        (error) => {
            console.log(error);
        }
    );
});