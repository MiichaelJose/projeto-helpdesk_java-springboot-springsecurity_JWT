let contador_pergunta = 0
let contador_repostas = 0
let imagem            = ''
var data              = JSON.parse(localStorage.getItem('data'))

const mensagens = [
    "nome equipamento?",
    "qual o departamento?",
    "cor do equipamento",
    "serial do equipamento? (opcional)",
    "status de prioridade para o equipamento?",
    "descrição do equipamento?",
    "imagem do equipamento? (opcional)",
]

let ticket = {
    "idUsuario": {
        "id":Number(data.id)
    },
    "equipamento":"",
    "departamento":"",
    "prioridade":"",
    "cor":"",
    "data":"",
    "descricao":"",
    "serial":"",
    "imagem":""
}

const inputimg = document.querySelector('.input-img')
const labelimg = document.querySelector('.label-img')

const menu_chat = () => {
    area_container.style.overflowY = 'scroll'
    criar_pergunta(mensagens[contador_pergunta])
}

const proxima_pergunta = () => {
    contador_pergunta++
    criar_pergunta(mensagens[contador_pergunta])
}

const enviar_resposta = () => {
    const input = document.querySelector('.input-resposta')

    //if(input.value != '' || inputimg.value != '') {
        criar_resposta(input.value)
        criar_json(contador_pergunta, input.value)
    
        contador_repostas++
    
        if (contador_repostas == 6) {
            input.setAttribute('hidden', 'hidden')
            labelimg.style.display = 'flex'
        }
    
        contador_repostas < 7 
        ? proxima_pergunta() 
        : menu_confirmar_dados()
    //}
   
    //input.value = ''
    area_container.scroll(0, 500)
}

const criar_json = (n, value) => {
    criar_texto_menudados(n, value)

    switch (n) {
        case 0:
                ticket.equipamento      = value
            break;
        case 1:
                ticket.departamento     = value
            break;
        case 2:
                ticket.cor              = value
            break;
        case 3:
                ticket.serial           = value
            break;
        case 4:
                ticket.prioridade       = value 
            break;
        case 5:
                ticket.descricao        = value
            break;
        case 6:
                ticket.imagem           = imagem
            break;
    }
}

const criar_resposta = (value) => {
    const resposta = document.querySelector('.resposta-mensagem').cloneNode(true)

    resposta.querySelectorAll("p")[1].innerHTML = value

    resposta.style.display = 'block'

    area_container.appendChild(resposta)
}

const criar_pergunta = (mensagem) => {
    som_pergunta()
    
    const pergunta = document.querySelector('.pergunta-mensagem').cloneNode(true)

    pergunta.querySelector("p").innerHTML = mensagem

    pergunta.style.display = 'flex'

    area_container.appendChild(pergunta)
}

const menu_confirmar_dados = () => {
    const container_section     = document.querySelector('.container-section')
    const modal_confirmardados  = document.querySelector('.modal-confirmardados').cloneNode(true)
    const areamensagem          = document.querySelector(".area-enviar-mensagem")
    const buttao_cadastrar      = modal_confirmardados.querySelector('.cadastrarticket-button')
    // analisar essas 3 opções para melhor gerenciamento
    modal_confirmardados.style.display  = 'flex'
    area_container.style.display        = 'none'
    areamensagem.style.display          = 'none'

    container_section.appendChild(modal_confirmardados)
  
    buttao_cadastrar.addEventListener('click', () => {
        cadastrar_ticket(ticket)
    })
}

const criar_texto_menudados = (n, value) => {
    const caixa_text            = document.querySelector('.caixa-geral')
    const descricao             = document.querySelector('.textodescricao').querySelector('p')
    const imagem_dispositivo    = document.querySelector('#img-dispositivo')

    const paragrafos = caixa_text.querySelectorAll('p')[n]

    console.log(paragrafos);
    if(n == 5) {
        descricao.innerHTML  = value
        paragrafos.innerHTML = "21/10/2002"
    }
    else if(n == 6)
        imagem_dispositivo.src  = imagem
    else    
        paragrafos.innerHTML    = '' + value
} 

const cadastrar_ticket = (ticket) => {
    const url = "http://localhost:8080/ticket"
   
    fetch(url, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            "Content-Type":"application/json",
            "Authorization": "Bearer " + pegar_token_cookie().token_acesso
        },     
        body: JSON.stringify(ticket)
    })
    .then(resp => resp)
    .then(data => {
        console.log(data.status);
        if(data.status == 200) alert('cadastrado')
    })
}

const cancelar_ticket = (e) => {
    let confirmardados = e.parentElement.parentElement
    
    limpar_modal_anterior()

    confirmardados.style.display    = 'none'
    area_container.style.display    = 'block'
    area_container.style.overflowY  = 'hidden'

    ativar_modal_chat()
}

// FINALIZADO
const iniciar_chat = () => {
    const modal_ativarchat  = document.querySelector(".modal-ativarchat")
    const area_mensagem     = document.querySelector(".area-enviar-mensagem")
    modal_ativarchat.style.display  = 'none'
    area_mensagem.style.display     = 'flex'

    menu_chat()

    document.querySelector('.input-resposta').addEventListener('keydown', (e) => {
        if(e.key === "Enter")
            enviar_resposta()
    })
    
}

const ativar_modal_chat = () => {
    const modal_chat = modal_ativarchat.cloneNode(true)
    modal_chat.style.display = 'flex'
    area_container.appendChild(modal_chat)
}

const som_pergunta = () => {
    let context = new AudioContext(),
    oscillator  = context.createOscillator(),
    contextGain = context.createGain();
 

    oscillator.type         = 'sine'
    contextGain.gain.value  = 0.2

    oscillator.connect(contextGain);
    contextGain.connect(context.destination);

    oscillator.start(0)
    contextGain.gain.exponentialRampToValueAtTime(
        0.00001, context.currentTime + 1
    )
}

inputimg.addEventListener("change", (e) => {
    let file            = e.target.files[0];
    let reader          = new FileReader();
    labelimg.innerHTML  = file.name

    reader.onload = (data) => {
        imagem = data.target.result;
    }

    reader.readAsDataURL(file);
});