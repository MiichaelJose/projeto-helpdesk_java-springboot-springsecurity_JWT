const options = [
    "CRIAR TICKET", 
    "FUNCIONÁRIOS",
    "SOLICITAÇõES DE ACESSO", 
    "ATIVIDADES FECHADAS", 
    "ATIVIDADES ABERTAS"
]

const modal_ativarchat  = document.querySelector(".modal-ativarchat")
const area_container    = document.querySelector('.area-container')
const area_mensagem     = document.querySelector(".area-enviar-mensagem")
   

const buttons_options = () => {
    const buttons = document.querySelectorAll('li')

    buttons.forEach(i => {
        i.addEventListener("click", () => modal(i.getAttribute('id')))
    })
}

const modal = (id) => {
    limpar_modal_anterior()
    
    ativar_modal(id)
}

const limpar_modal_anterior  = () => {
    const modal_confirmardados  = document.querySelector('.modal-confirmardados')
    const input                 = document.querySelector('.input-resposta')
   
    if(contador_repostas == 7) {
        contador_pergunta = 0
        contador_repostas = 0
    }

    area_container.innerHTML            = ""
    area_mensagem.style.display         = 'none'
    modal_ativarchat.style.display      = 'none'
    modal_confirmardados.style.display  = 'none'
    labelimg.style.display              = 'none' 
    input.removeAttribute('hidden', 'hidden')
}

const ativar_modal = (id) => {
    const header_section    = document.querySelector('.section-header')
    const container_section = document.querySelector('.container-section')
    header_section.removeAttribute("hidden")
    container_section.removeAttribute("hidden")

    let title = header_section.querySelector("h1")
    
    switch (id) {
        case "1":
                ativar_modal_chat()
                title.innerHTML = options[0]
            break;
        case "2":
                modal_funcionario()
                title.innerHTML = options[1]
            break;
        case "3":
                title.innerHTML = options[2]
            break;
        case "4":
                title.innerHTML = options[3]
            break;
        case "5":
                title.innerHTML = options[4]
            break;
    }
}

