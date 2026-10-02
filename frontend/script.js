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

//--Elements to scan the barcode--

function onScanSuccess(decodedText){
    console.log(`Decoded from webcam = ${decodedText}`);

    scanner.pause();
    axios.post(`http://localhost:8080/cassa-automatica/scan/${decodedText}`).then(
        (response) => {
            console.log("Product added:", response.data);
            console.log(response.data)
            setTimeout(() => {
                scanner.resume();
            }, 2000);
        },
        (error) => {
            console.log("Scan error:", error);
            setTimeout(() => {
                scanner.resume();
            }, 2000);
        }
    );

}

let scanner = new Html5QrcodeScanner("reader", { width: 350, height: 150 }, false);

scanner.render(onScanSuccess);
