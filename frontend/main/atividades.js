const modal_atividades = () => {
    let container_main      = document.querySelector('.container__main')
    let container_header    = document.querySelector('.container__header')
    let container_activity  = document.querySelector('.container-activity')
    
    container_main.style.display            = 'flex'
    container_header.style.display          = 'flex'
    container_activity.style.display        = 'flex'
    //atividade_center_header.style.display   = 'flex'
}

const listar_atividades_fechadas = () => {
    let container_main  = document.querySelector('.container__main')
    
    container_main.innerHTML = ""

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
    const caixa = document.querySelector('.card-atividade-fechado').cloneNode(true)
    const status_funcionario_caixa = caixa.querySelector('.bottom-margin-fechado')
    const alterar_funcionario = caixa.querySelector('#alt-tecnico')
    const textos    = caixa.querySelectorAll('p')

    caixa.style.display = 'block'
    console.log(data);
    console.log(textos);
    textos[0].innerHTML = data.ticket.equipamento
    textos[1].innerHTML = data.ticket.departamento
    textos[2].innerHTML = data.ticket.data
    textos[3].innerHTML = data.ticket.prioridade

    textos[5].innerHTML = data.tecnico.usuario
    textos[6].innerHTML = "CPF: " + data.tecnico.cpf

    if (data.tecnicoAceitarServico == "ACEITO") {
        textos[7].innerHTML = "Status: " + data.statusServico
        textos[8].innerHTML = "Data inicializado: " + data.dataServicoInicializado
        textos[9].innerHTML = "Data finalizado: " + data.dataServicoFinalizado
        status_funcionario_caixa.style.display = 'none'
        alterar_funcionario.setAttribute('hidden', 'hidden')
    }else {
        console.log('entrou');
        alterar_funcionario.removeAttribute('hidden')
        status_funcionario_caixa.style.display = 'flex'
    }
   
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
    let container_main  = document.querySelector('.container__main')
    
    container_main.innerHTML = ""

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
    let container_main = document.querySelector('.container__main')
    const caixa     = document.querySelector('.open__activity').cloneNode(true)
    const button    = caixa.querySelector('.bottom__button')
    const textos    = caixa.querySelectorAll('p')

    caixa.style.display = 'block'

    console.log(data);

    //textos[0].innerHTML = data.usuario.usuario
    textos[1].innerHTML = data.equipamento
    textos[3].innerHTML = data.departamento
    textos[5].innerHTML = data.data
    //textos[3].setAttribute('id', data.id)
    textos[7].innerHTML = data.prioridade

    container_main.appendChild(caixa)
    
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