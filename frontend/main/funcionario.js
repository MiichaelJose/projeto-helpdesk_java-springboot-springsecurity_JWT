// prepaara o local para a listagem dos funcionarios 
const modal_funcionario = () => {
    let container_main      = document.querySelector('.container__main')
    let container_header    = document.querySelector('.container__header')
    let container_employee       = document.querySelector('.container-employee')
    
    container_main.style.display = 'flex'
    container_header.style.display = 'flex'
    container_employee.style.display = 'flex'

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
    let container_main = document.querySelector('.container__main')
    const caixa     = document.querySelector('.box').cloneNode(true)
    const textos    = caixa.querySelectorAll('p')

    caixa.style.display = 'flex'
    
    let button_alterar = caixa.querySelector('.box__button-alterar')
    
    button_alterar.style.display = 'block'

    let button_acess = caixa.querySelector('.box__button-acesso')
    
    button_acess.style.display = 'none'

    textos[0].innerHTML = data.usuario
    textos[1].innerHTML = data.cpf
    textos[2].innerHTML = data.cargo

    container_main.appendChild(caixa)
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