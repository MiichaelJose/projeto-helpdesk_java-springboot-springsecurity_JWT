const modal_atividades = () => {
    area_container.style.height = '90%'
    area_container.style.overflowY = 'scroll'
    atividade_center_header.style.display  = 'flex'
}

const listar_atividades_fechadas = () => {
    area_container.innerHTML = ""

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

    textos[0].innerHTML = data.ticket.equipamento
    textos[1].innerHTML = data.ticket.departamento
    textos[2].innerHTML = data.ticket.prioridade
    textos[3].innerHTML = data.ticket.data

    textos[4].innerHTML = data.tecnico.usuario
    textos[6].innerHTML = "CPF: " + data.tecnico.cpf
    textos[7].innerHTML = data.statusServico
    textos[8].innerHTML = data.dataServicoInicializado
    textos[9].innerHTML = data.dataServicoFinalizado
    console.log(textos);
    console.log(data);
    area_container.appendChild(caixa)
}

let servico = {
    "idTecnico":{
        "id": 0
    },
    "idAdmin":{
        "id": data.id
    },
    "idTicket":{
        "id": 0
    },
    "tecnicoAceitarServico":"observação",
    "statusServico":"",
    "statusTicket":"",
    "dataServicoInicializado":"",
    "dataServicoFinalizado":""
}


const listar_atividades_abertas = () => {
    area_container.innerHTML = ""

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
    const button    = caixa.querySelector('.circle')
    const textos    = caixa.querySelectorAll('p')

    caixa.style.display = 'flex'
    textos[0].innerHTML = data.usuario.usuario
    textos[1].innerHTML = data.status
    textos[3].setAttribute('id', data.id)
    textos[4].innerHTML = data.equipamento
    textos[5].innerHTML = data.departamento
    textos[6].innerHTML = data.descricao
    textos[7].innerHTML = data.data
   
    area_container.appendChild(caixa)

    console.log(button);

    button.addEventListener('click', () => {
       // const idTicket = document.querySelector('.idTicket')

        //servico.idTicket = Number(idTicket.getAttribute('id'))

        servico.idTicket = data.id

        modal_definir_funcionario()
    })

    console.log(textos);
    console.log(data);
    
}

const fechar_modal_definir_funcionario = () => {
    const caixa_selecionar_funcionario = document.querySelector(".selecionar-funcionario-caixa ")

    caixa_selecionar_funcionario.style.display = "none"

}

const modal_definir_funcionario = () => {
    const caixa_selecionar_funcionario = document.querySelector(".selecionar-funcionario-caixa ")
    
    caixa_selecionar_funcionario.style.display = "flex"

    listar_adicionar_tecnicos()
}

const listar_adicionar_tecnicos = () => {
    const seletor_funcionarios = document.querySelector('.selector-funcionario')

    seletor_funcionarios.innerHTML = ""

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
                result.forEach(e => {
                    criar_caixa_tecnico(e)
                })
            })
        }
    })
}

const criar_caixa_tecnico = (tecnico, idTicket) => {
    const seletor_funcionarios = document.querySelector('.selector-funcionario')
    const dados_funcionario = document.querySelector('.dados-funcionario')

    const img = dados_funcionario.querySelector('div')
    const paragrafos = dados_funcionario.querySelectorAll('p')
    const button = dados_funcionario.querySelector('button')


    const p = document.createElement('p')

    p.addEventListener('click', () => {
        paragrafos[0].innerHTML = tecnico.usuario
        paragrafos[1].innerHTML = tecnico.cpf
        servico.idTecnico = tecnico.id

        if(tecnico.image != null) img.style.backgroundImage = "url('"+ tecnico.image +"')";
    })

    p.innerHTML = tecnico.usuario
    seletor_funcionarios.appendChild(p)
}

const cadastrar_servico = () => {
    cadastrar_servico
    console.log(servico);
}