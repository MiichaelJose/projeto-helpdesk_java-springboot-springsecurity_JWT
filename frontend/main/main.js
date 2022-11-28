const options = [
    "CRIAR TICKET", 
    "FUNCIONÁRIOS",
    "SOLICITAÇõES DE ACESSO", 
    "ATIVIDADES", 
    "ATIVIDADES ABERTAS"
]

const buttons_options = () => {
    let buttons = document.querySelectorAll('li')

    //ativar_modal_chat()

    tempo_minutes(pegar_token_json(pegar_token_cookie().token_acesso))

    buttons.forEach(i => {
        i.addEventListener("click", () => modal(i.getAttribute('id')))
    })
}

const modal = (id) => {
    limpar_modal_anterior()
    
    ativar_modal(id)
}

const limpar_modal_anterior  = () => {
    let container_main      = document.querySelector('.container__main')
    let confimar_dados      = document.querySelector('.confirm-data')
    let input               = document.querySelector('.input-resposta')
    let container_ticket    = document.querySelector('.container__ticket')
    let area_mensagem       = document.querySelector(".container__input-mensagem")
    let labelimg            = document.querySelector('.label-img')
    let ativar_chat         = document.querySelector('.modal-ativarchat')
    let container_header    = document.querySelector('.container__header')
    let container_employee  = document.querySelector('.container-employee')
    let container_acess     = document.querySelector('.container-acess')
    let container_activity  = document.querySelector('.container-activity')
    let cofirm_data         = document.querySelector('.confirm-data')
    
    if(contador_repostas == 7) {
        contador_pergunta = 0
        contador_repostas = 0
    }

    // inputs
    container_header.style.display      = 'none'
    container_employee.style.display    = 'none'
    container_acess.style.display       = 'none'
    container_activity.style.display    = 'none'
    input.removeAttribute('hidden', 'hidden')

    // ticket
    container_ticket.innerHTML          = ''
    container_ticket.style.display      = 'none'
    ativar_chat.style.display           = 'none'
    confimar_dados.style.display        = 'none'
    labelimg.style.display              = 'none' 

    // main
    container_main.innerHTML            = ''
    container_main.style.display        = 'none'
    area_mensagem.style.display         = 'none'

    cofirm_data.style.display           = 'none'
}

const ativar_modal = (id) => {
    let header      = document.querySelector('.section__header')
    let section     = document.querySelector('.section__container')
    
    header.removeAttribute("hidden")
    section.removeAttribute("hidden")

    let title = header.querySelector("h1")
    
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
                modal_solicitacoes()
                title.innerHTML = options[2]
            break;
        case "4":
                modal_atividades()
                title.innerHTML = options[3]
            break;
        case "5":
                title.innerHTML = options[4]
            break;
    }
}

