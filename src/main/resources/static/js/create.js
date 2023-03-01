console.log('JS OK!');


function createPizza(event) {
    event.preventDefault();

    const pizza = {
        name: document.querySelector('#pizzaName').value,
        description: document.querySelector('#pizzaDescription').value,
        imgUrl: document.querySelector('#pizzaImgUrl').value,
        price: document.querySelector('#pizzaPrice').value,
    }

    axios.post('http://localhost:8080/api/pizzas', pizza)
        .then((result) => {
            console.log("Inserimento dati di", pizza);
            window.location.replace("http://localhost:8080");
        }).catch((result) => {
            console.error("Errore nell'inserimento", result);
        })

}