console.log('JS OK!');

const URLParams = new URLSearchParams(window.location.search);
const pizzaId = URLParams.get('id');

pizzaDetail();

function pizzaDetail() {
    axios.get(`http://localhost:8080/api/pizzas/${pizzaId}`)
        .then((result) => {

            const pizza = result.data;
            console.log('Richiesta ok', pizza);

            document.querySelector('#pizza-name').innerHTML = `${pizza.name}`;

            document.querySelector('#pizza-img').src = `${pizza.imgUrl}`;
            document.querySelector('#pizza-img').alt = `${pizza.name}`;

            document.querySelector('#pizza-description').innerHTML = `${pizza.description}`;

            pizza.ingredients.forEach(ingredient => {
                document.querySelector('#pizza-ingredients').innerHTML += `
                <li>${ingredient.name}</li>
                `;
            });


            document.querySelector('#pizza-price').innerHTML = `${pizza.price}€`;

        }).catch((result) => {
            console.error('Errore nella richiesta', result);
            alert('Errore durante la richiesta');
        })
}

function deletePizza() {
    const risposta = confirm('Sei sicuro?');

    if (risposta) {
        axios.delete(`http://localhost:8080/api/pizzas/${pizzaId}`)
            .then((result) => {
                //ok => ricarico l'elenco dei libri
                window.location.replace("http://localhost:8080");;
            }).catch((result) => {
                console.error('Errore nella richiesta', result);
                alert('Errore durante la richiesta!');
            });
    }

}