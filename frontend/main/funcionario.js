// prepaara o local para a listagem dos funcionarios 
const modal_funcionario = () => {
    area_container.style.height = '90%'
    area_container.style.overflowY = 'scroll'
  
    listar_funcionarios()
}

// lista os funcionarios no banco e gera o card
const listar_funcionarios = () => {
    const url = "http://localhost:8080/usuarios"
     
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
                    caixa_funcionario(element)
                });
            })
        }
    })
}

// cria o card do funcionario
const caixa_funcionario = (data) =>  {
    const caixa     = document.querySelector('#funcionario').cloneNode(true)
    const textos    = caixa.querySelectorAll('p')

    caixa.style.display = 'flex'
    //caixa.classList.remove('funcionario')

    textos[0].innerHTML = data.usuario
    textos[1].innerHTML = data.cargo
    textos[2].innerHTML = data.cpf

    area_container.appendChild(caixa)
}



const alterar_funcionario = () => {

}

const acesso_funcionario = () => {
    
}
const check =  () => {
    const check = document.querySelector('#check')

    if(check.checked) {
        check.checked = false
    } else if(!check.checked) {
        check.checked = true
    }
}