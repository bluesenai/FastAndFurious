const carrinho =
JSON.parse(localStorage.getItem("carrinho")) || [];

const area =
document.getElementById("carrinho-area");

let total = 0;

carrinho.forEach(produto => {

    total += produto.preco;

    area.innerHTML += `

        <div class="item-carrinho">

            <h2>${produto.nome}</h2>

            <p>${produto.categoria}</p>

            <strong>
                R$ ${produto.preco.toFixed(2)}
            </strong>

        </div>
    `;
});

area.innerHTML += `

    <h1>
        Total: R$ ${total.toFixed(2)}

        </h1>
 `;      