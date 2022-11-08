const criar_cookie = (token_acesso) => {
    document.cookie = "token_acesso="+token_acesso+";path=/main"
}

const apagar_cookie = () => {
    document.cookie = "token_acesso="
}

const pegar_token_cookie = () => {
    let data = {
        'token_acesso':(document.cookie.split('token_acesso=')[1]).split(';')[0],
        //'token_atualizado':(document.cookie.split('token_atualizado=')[1]).split(';')[0]
    }

    return data
}

const tempo_minutes = (data_user) => {
    let exp = new Date(data_user.exp*1000).getTime() // data e hora de expiracao em milisegundos

    let data_atual = new Date().getTime() // data e hora atual em milisegundos

    let result = exp - data_atual // só o tempo de expiração em milisegundos  
    let minutos = Math.trunc(result / 60000)
    let segundo_finais = 60

    const container_token = document.querySelector('.container-token')

    container_token.style.display = 'none'
   
    if(Math.sign(result) != '-1') {
        setTimeout(function() {
            console.log("entrou");
            modal_refreshToken(segundo_finais)

            //clearInterval(interval)
        }, minutos * 55000) // 8m e 8s
    }else {
        container_token.style.display = 'flex'
    }
}


/*
const tempo_minutes = (data_user) => {
    
    let exp = new Date(data_user.exp*1000).getTime() // data e hora de expiracao em milisegundos

    let data_atual = new Date().getTime() // data e hora atual em milisegundos

    let result = exp - data_atual // só o tempo de expiração em milisegundos

    let segundo_finais = 0 // segundos 60s
   
    if(Math.sign(result) != '-1') {
        console.log("ENTOU");
        const interval = setInterval(function() {
            //if (result > 60000) result -= 300000
            if (result > 300000) result -= 300000

            console.log('entrou 1');
            console.log("RESULT " + result);
            if(result < 300000) {
                result = Math.ceil(result)

                const interval2 = setInterval(function() {
                    if (result > 60000) result -= 60000

                    if(result < 60000) {
                        console.log(result);
                        segundo_finais = result / 1000 // milisegundos em segundos = 60s aproximadamente
            
                        console.log("SEGUNDOS FINAIS " + segundo_finais);
            
                        clearInterval(interval)
                        clearInterval(interval2)
            
                        tempo_segundo(segundo_finais)
                        console.log("menor q " + result);
                    }
                }, 60000)
            }
        }, 300000) // 5minutos = 300s
   }else {
    const container_token = document.querySelector('.container-token')

    container_token.style.display = 'flex'
   }
}*/

const tempo_segundo = (s) => {
    let segundos =  s
    modal_refreshToken(segundos)
}

const modal_refreshToken = (time) => {
    let segundos = 30

    const container_token = document.querySelector('.container-token')

    container_token.style.display = 'flex'

    const element_tempo = container_token.querySelector('h2')

    console.log("SEG "+segundos + " aqui " + Math.sign(segundos));

    console.log(time);
    const interval = setInterval(function() {
        --segundos;
        console.log("segundos" + segundos);

        element_tempo.innerHTML = segundos

        if(segundos == 0) {
            // tempoToken = false
            console.log("ACABOU OS SEGUNDOS");
            clearInterval(interval)
        }
    }, 1000)

}

const refreshToken = () => {
    const url = "http://localhost:8080/usuarios/token/atualizado"

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

const pegar_token_json = (token) => {
    try {
      return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
      return null;
    }
}