//New cart button
document.getElementById('btn-new-cart').addEventListener('click', e => {
    axios.post("http://localhost:8080/cassa-automatica/newCart").then(
        (response) => {
            var result = response.data;
            console.log(result);
            //emptying actual cart and total
            const cartItemsList = document.getElementById("cart-items");
            const cartTotal = document.getElementById("cart-total");

            cartItemsList.innerHTML = '';
            cartTotal.innerHTML = 'Total to Pay: €0.0';

        },
        (error) => {
            console.log(error);
            showToast(error.response.data);
        }
    );
});

//Checkout button
document.getElementById('btn-checkout').addEventListener('click', e => {
    axios.post("http://localhost:8080/cassa-automatica/checkout").then(
        (response) => {
            alert(response.data);
            document.getElementById("cart-items").innerHTML = '';
            document.getElementById("cart-total").innerHTML = 'Total to Pay: €0.0';
        },
        (error) => {
            console.log(error);
            showToast(error.response.data);
        }
    );
});

//Function that takes the JSON data from backend and display it to the user
function updateCartUI(cartData){
    const cartItemsList = document.getElementById("cart-items");
    const cartTotal = document.getElementById("cart-total");

    cartItemsList.innerHTML = '';

    if(!cartData.rows || cartData.rows == 0) return;

    let total = 0;

    cartData.rows.forEach(row => {
        console.log(row.product.productName);
        console.log(row.product.price);
        console.log(row.quantity);

        //fetching all components from the JSON object
        const productName = row.product.productName;
        const productPrice = row.product.price;
        const productQuantity = row.quantity;

        const li = document.createElement('li');

        //adding styling and text to the <li>
        li.style.padding = "5px 0";
        li.style.borderBottom = "1px solid #eee";
        li.textContent = `${productQuantity}x ${productName}:   €${(productPrice * productQuantity).toFixed(2)}`;

        cartItemsList.appendChild(li);

        total += (productPrice * productQuantity);
    });

    cartTotal.textContent = `Total to Pay: €${total.toFixed(2)}`;

}

//Elper function to show/hide the error message
function showToast(message){
    const error_div = document.getElementById("error-div");
    const error_div_text = document.querySelector('#error-p');
    
    error_div_text.innerHTML = message;

    error_div.classList.add('show');
    setTimeout(() => {
        error_div.classList.remove('show');
    }, 3000);

}

//--Elements to scan the barcode--

//audio scanning barcode
const sound = new Audio("./audio/scan_audio.mp3");


function onScanSuccess(decodedText){
    console.log(`Decoded from webcam = ${decodedText}`);

    sound.play();  //playing scanning sound

    scanner.pause();
    axios.post(`http://localhost:8080/cassa-automatica/scan/${decodedText}`).then(
        (response) => {
            console.log("Product added: ",response.data);

            updateCartUI(response.data);
            
            setTimeout(() => {
                scanner.resume();
            }, 1000);
        },
        (error) => {
            console.log("Scan error:", error);
            showToast(error.response.data);
            setTimeout(() => {
                scanner.resume();
            }, 2000);
        }
    );

}

//Manual insert button
document.getElementById('btn-send-scan').addEventListener('click', e => {
    const barcodeManual = document.getElementById('input-barcode');
    const manualValue = barcodeManual.value;
    if (manualValue.trim() !== "") {
        onScanSuccess(manualValue);
        barcodeManual.value = ''; //emptying the field
    }
});

//Sertting and rendering the actual scanner
let scanner = new Html5QrcodeScanner("reader", { 
    fps: 30,
    qrbox: { width: 350, height: 150 } 
}, false);

scanner.render(onScanSuccess);
