
const modal_funcionario = () => {
    area_container.style.height = '90%'
    area_container.style.overflowY = 'scroll'
    console.log('oi');
   listar_funcionarios()
}

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
            data.json().then( result => {
                
                result.forEach(Element => {
                    console.log(Element);
                    caixa_funcionario(Element)
                });
            })
        }
    })
}


const caixa_funcionario = (data) =>  {
    const caixa = document.querySelector('.funcionario').cloneNode(true)
    caixa.style.display = 'flex'
    caixa.classList.remove('funcionario')
    const textos = caixa.querySelectorAll('p')

    textos[0].innerHTML = data.usuario
    textos[1].innerHTML = data.cargo
    textos[2].innerHTML = data.cpf


    console.log(area_container);
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

    console.log(check.checked);
}