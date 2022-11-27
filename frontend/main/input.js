const open_acess_filter = () => {
    let container_acess = document.querySelector('.container-acess')

    container_acess.style.width = "70%"

    let input_acess = document.querySelector('.input-acess')

    input_acess.style.width = '40%'

    let filter = document.querySelector('.js-acess-filter')

    filter.style.display = 'none'

    let op_acess = document.querySelector('.input__acess-filter')

    op_acess.style.display = "flex"

    console.log(filter);
}

const close_acess_filter = () => {
    let container_acess = document.querySelector('.container-acess')

    container_acess.style.width = "50%"

    let input_acess = document.querySelector('.input-acess')

    input_acess.style.width = '70%'

    let filter = document.querySelector('.js-acess-filter')

    filter.style.display = 'flex'

    let op_acess = document.querySelector('.input__acess-filter')

    op_acess.style.display = "none"
}



const open_activity_filter = () => {
    let filter = document.querySelector('.js-activity-filter')

    filter.style.display = 'none'

    let op_acess = document.querySelector('.input__activity-filter')

    op_acess.style.display = "flex"

    console.log(filter);
}

const close_activity_filter = () => {
    let filter = document.querySelector('.js-activity-filter')

    filter.style.display = 'flex'

    let op_acess = document.querySelector('.input__activity-filter')

    op_acess.style.display = "none"

    console.log(filter);
}