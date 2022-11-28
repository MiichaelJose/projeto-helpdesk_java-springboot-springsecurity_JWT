
// prepara o local para a listagem das solicitacoes
const modal_solicitacoes = () => {
    let container_main      = document.querySelector('.container__main')
    let container_header    = document.querySelector('.container__header')
    let container_acess     = document.querySelector('.container-acess')
    
    container_main.style.display = 'flex'
    container_header.style.display = 'flex'
    container_acess.style.display = 'flex'

    listar_solicitacoes()
}

// lista as solicitacoes no banco e gera o card
const listar_solicitacoes = () => {
    const url = "http://localhost:8080/usuarios/solicitando-acesso"
     
    fetch(url, {
        headers: {
            'Accept': 'application/json',
            "Authorization": "Bearer " + pegar_token_cookie().token_acesso
        }
    })
    .then(resp => resp)
    .then(data => {
        if(data.status == 200) {
            data.json().then( result => {
                result.forEach(Element => {
                    console.log(Element);
                    caixa_solicitacao(Element)
                });
            })
        }
    })
}

// criar card de solicitacao
const caixa_solicitacao = (data) =>  {
    let container_main = document.querySelector('.container__main')
    const caixa = document.querySelector('.box').cloneNode(true)
    const textos = caixa.querySelectorAll('p')

    caixa.style.display = 'flex'
    //caixa.classList.remove('requests')

    let button_acess = caixa.querySelector('.box__button-acesso')
    
    button_acess.style.display = 'flex'

    let button_alterar = caixa.querySelector('.box__button-alterar')
    
    button_alterar.style.display = 'none'

    textos[0].innerHTML = data.usuario
    textos[1].innerHTML = data.cpf
    textos[2].innerHTML = data.cargo

    const check     = caixa.querySelector('#check')

    check.checked   = data.permissao

    check.addEventListener('click', (element) => {
        alterar_acesso(data.id, element.target)
    })

    container_main.appendChild(caixa)
}

// button para alterar acesso do funcionario
const alterar_acesso = (id, element) => {
    const url = "http://localhost:8080/usuarios/"+id+"/alterar-acesso"

    const data = {
        "permissao":element.checked
    }

    fetch(url, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            "Content-Type":"application/json",
            "Authorization": "Bearer " + pegar_token_cookie().token_acesso
        },     
        body: JSON.stringify(data)
    })
    .then(resp => resp)
    .then(data => {
        if(data.status == 200) console.log('alterado');
    })
}