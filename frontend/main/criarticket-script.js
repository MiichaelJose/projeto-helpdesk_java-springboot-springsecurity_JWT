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
    "equipamento":"",
    "departamento":"",
    "prioridade":"",
    "cor":"",
    "data":"",
    "descricao":"",
    "serial":"",
    "imagem":""
}

let contador_pergunta = 0
let contador_repostas = 0



const menu_chat = () => {
    area_container.style.overflowY = 'scroll'
    // primeira pergunta
    criar_pergunta(mensagens[contador_pergunta])
}

const proxima_pergunta = () => {
    contador_pergunta++
    criar_pergunta(mensagens[contador_pergunta])
}

const enviar_resposta = () => {
    const input = document.querySelector('.input-resposta')

    criar_resposta(input.value)
    criar_json(contador_pergunta, input.value)

    contador_repostas++

    contador_repostas < 7 
    ? proxima_pergunta() 
    : menu_confirmar_dados()

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
                ticket.imagem           = value
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
    const container_section = document.querySelector('.container-section')
    const modal_confirmardados = document.querySelector('.modal-confirmardados').cloneNode(true)
    //const area_enviar_mensagem = document.querySelector('.area-enviar-mensagem')

   
    modal_confirmardados.style.display = 'flex'
    area_container.style.display = 'none'
    area_mensagem.style.display = 'none'


    //area_enviar_mensagem.style.zIndex  = 1

    container_section.appendChild(modal_confirmardados)
}

const criar_texto_menudados = (n, value) => {
    const caixa_text = document.querySelector('.caixa-geral')
    const descricao = document.querySelector('.textodescricao').querySelector('p')
    const imagem_dispositivo = document.querySelector('#img-dispositivo')

    const paragrafos = caixa_text.querySelectorAll('p')[n]

    console.log(paragrafos);
    if(n == 5) {
        descricao.innerHTML = value
        paragrafos.innerHTML = "21/10/2002"
    }
    else if(n == 6)
        imagem_dispositivo.src  = ""
    else 
        paragrafos.innerHTML = '' + value
} 


const cadastrar_ticket = () => {
    const url = "http://localhost:8080/ticket"

    fetch(url, {
        method: "POST",
        headers: {"Content-Type": "application/json"},     
        body: JSON.stringify(ticket)
    })
    .then(resp => resp)
    .then(data => {
        if(data.status == 200) {

        }
    })
}



// FINALIZADO
const iniciar_chat = () => {
    const modal_ativarchat = document.querySelector(".modal-ativarchat")
    modal_ativarchat.style.display = 'none'
    const area_mensagem = document.querySelector(".area-enviar-mensagem")
    area_mensagem.style.display = 'flex'

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
    oscillator = context.createOscillator(),
    contextGain = context.createGain();
 

    oscillator.type = 'sine'
    contextGain.gain.value = 0.2

    oscillator.connect(contextGain);
    contextGain.connect(context.destination);

    oscillator.start(0)
    contextGain.gain.exponentialRampToValueAtTime(
        0.00001, context.currentTime + 1
    )
}