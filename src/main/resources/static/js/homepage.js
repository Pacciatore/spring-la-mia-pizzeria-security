console.log('JS OK!');

pizzasList();

function pizzasList() {
    axios.get('http://localhost:8080/api/pizzas')
        .then((result) => {

            const pizzasList = result.data;
            console.log('Richiesta ok', pizzasList);

            document.querySelector('#pizzas_table').innerHTML = '';

            pizzasList.forEach(pizza => {

                const ingredients = pizza.ingredients;

                console.log('expected ingredients', ingredients);

                document.querySelector('#pizzas_table').innerHTML += `
                <tr>
                    <td><a href="./pizza?id=${pizza.id}"> ${pizza.id} </a></td>
                    <td>${pizza.name}</td>
                    <td>${pizza.description}</td>
                    <td><ul id="ingredients-list-${pizza.id}"></ul></td>
                    <td>${pizza.price}€</td>
                </tr>`;

                ingredients.forEach(ingredient => {
                    document.querySelector(`#ingredients-list-${pizza.id}`).innerHTML += `
                    <li>${ingredient.name}</li>
                    `;
                })


            });

        }).catch((result) => {
            console.error('Errore nella richiesta', result);
            alert('Errore durante la richiesta');
        })
}