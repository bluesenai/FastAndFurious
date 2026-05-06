const API = "http://localhost:1910/fastfurious/produtos";

let carrinho = JSON.parse(localStorage.getItem("carrinho")) || [];

async function carregarCardapio(){

    const resposta = await fetch(API);

    const produtos = await resposta.json();

    const cardapio = document.getElementById("cardapio");

    cardapio.innerHTML = "";

    produtos.forEach(produto => {

        let imagem = "img/burger.png";

        if(produto.categoria === "BEBIDA"){
            imagem = "img/refri.png";
        }

        if(produto.nome.toLowerCase().includes("batata")){
            imagem = "img/batata.png";
        }

        cardapio.innerHTML += `

            <div class="produto">

                <img src="${imagem}">

                <div class="info">

                    <h2>${produto.nome}</h2>

                    <p>${produto.categoria}</p>

                    <span class="preco">
                        R$ ${produto.preco.toFixed(2)}
                    </span>

                    <button
                        class="adicionar"
                        onclick='adicionarCarrinho(${JSON.stringify(produto)})'
                    >
                        Adicionar
                    </button>

                </div>

            </div>
        `;
    });

    atualizarContador();
}

function adicionarCarrinho(produto){

    carrinho.push(produto);

    localStorage.setItem(
        "carrinho",
        JSON.stringify(carrinho)
    );

    atualizarContador();

    alert(produto.nome + " adicionado!");
}

function atualizarContador(){

    document.getElementById("contador")
        .innerText = carrinho.length;
}

function abrirCarrinho(){

    window.location.href = "carrinho.html";
}

carregarCardapio();