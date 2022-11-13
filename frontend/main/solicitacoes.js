
// prepara o local para a listagem das solicitacoes
const modal_solicitacoes = () => {
    area_container.style.height = '90%'
    area_container.style.overflowY = 'scroll'
    
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
    const caixa = document.querySelector('#solicitacao').cloneNode(true)
    const textos = caixa.querySelectorAll('p')

    caixa.style.display = 'flex'
    //caixa.classList.remove('solicitacao')

    textos[0].innerHTML = data.usuario
    textos[1].innerHTML = data.cargo
    textos[2].innerHTML = data.cpf

    const check     = caixa.querySelector('#check')

    check.checked   = data.permissao

    check.addEventListener('click', (element) => {
        alterar_acesso(data.id, element.target)
    })

    area_container.appendChild(caixa)
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