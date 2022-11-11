const modal_atividades = () => {
    //area_container.style.height = '90%'
    //area_container.style.overflowY = 'scroll'
}

const listar_atividades_fechadas = () => {
    //limpar_modal_anterior()

    const url = "http://localhost:8080/servico/fechados"
     
    fetch(url, {
        headers: {
            'Accept': 'application/json',
            "Authorization": "Bearer " + pegar_token_cookie().token_acesso
        }
    })
    .then(resp => resp)
    .then(data => {
        if(data.status == 200) {
            data.json().then(result => {
                result.forEach(element => {
                    caixa_atividade_fechada(element)
                });
            })
        }
    })
}

// cria o card do funcionario
const caixa_atividade_fechada = (data) =>  {
    const caixa     = document.querySelector('.atividade-fechada').cloneNode(true)
    const textos    = caixa.querySelectorAll('p')

    caixa.style.display = 'flex'

    console.log(textos);
    console.log(data);
    area_container.appendChild(caixa)
}


const listar_atividades_abertas = () => {
   // limpar_modal_anterior()

    const url = "http://localhost:8080/servico/abertos"
     
    fetch(url, {
        headers: {
            'Accept': 'application/json',
            "Authorization": "Bearer " + pegar_token_cookie().token_acesso
        }
    })
    .then(resp => resp)
    .then(data => {
        if(data.status == 200) {
            data.json().then(result => {
                result.forEach(element => {
                    caixa_atividade_aberta(element)
                });
            })
        }
    })
}


// cria o card do funcionario
const caixa_atividade_aberta = (data) =>  {
    const caixa     = document.querySelector('.atividade-aberta').cloneNode(true)
    const textos    = caixa.querySelectorAll('p')

    caixa.style.display = 'flex'

    console.log(textos);
    console.log(data);
    area_container.appendChild(caixa)
}