
const modal_funcionario = () => {
    area_container.style.height = '90%'
    area_container.style.overflowY = 'scroll'

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
                console.log(result);

            })
        }
    })
}