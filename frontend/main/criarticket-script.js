const mensagens = [
    "nome equipamento?",
    "nome departamento?",
    "serial do equipamento? (opcional)",
    "imagem do equipamento? (opcional)",
    "descrição do equipamento?",
    "status de prioridade para o equipamento?"
]

const status_equipamento = [
    "INDEFINIDO",
    "MÉDIO",
    "URGENTE"
]

let contador_pergunta = 0
let contador_repostas = 0


let ticket = {
    "nomeEquipamento":"",
    "departamento":"",
    "statusPrioridade":"",
    "cor":"",
    "data":"",
    "descricao":"",
    "serial":"",
    "imagem":""
}

const menu_chat = () => {
    // primeira pergunta
    criar_pergunta(mensagens[contador_pergunta])
}

const proxima_pergunta = () => {
    contador_pergunta++
    criar_pergunta(mensagens[contador_pergunta])
}



const enviar_resposta = () => {
    const input = document.querySelector('.input-resposta').value
    
    // no 5 encerra as perguntas
    if(contador_repostas < 6) {
        criar_resposta(input)
        criar_json(contador_pergunta, input)

        if(contador_repostas < 5) 
            proxima_pergunta()
        contador_repostas++
    }

    area_container.scroll(0, 500)
}


const criar_json = (n, value) => {
    switch (n) {
        case 0:
                ticket.nomeEquipamento  = value
            break;
        case 1:
                ticket.departamento     = value
            break;
        case 2:
                ticket.serial           = value
            break;
        case 3:
                ticket.imagem           = value
            break;
        case 4:
                ticket.descricao        = value
            break;
        case 5:
                ticket.statusPrioridade = value
            break;
    }
    console.log(ticket);
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




const criar_ticket = () => {
    console.log('oi 2');
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