const criar_cookie = (token_acesso, token_atualizado) => {
    document.cookie = "token_acesso="+token_acesso+";path=/main"
    document.cookie = "token_atualizado="+token_atualizado+";path=/main"
}

const apagar_cookie = () => {
    document.cookie = "token_acesso="
    document.cookie = "token_atualizado="
}

const pegar_token_cookie = () => {
    let data = {
        'token_acesso':(document.cookie.split('token_acesso=')[1]).split(';')[0],
        'token_atualizado':(document.cookie.split('token_atualizado=')[1]).split(';')[0]
    }

    return data
}



const tempo_minutes = (data_user) => {
    
    let exp = new Date(data_user.exp*1000).getTime() // data e hora de expiracao em milisegundos

    let data_atual = new Date().getTime() // data e hora atual em milisegundos

    let result = exp - data_atual // só o tempo de expiração em milisegundos

    let segundo_finais = 0 // segundos 60s

   // if(Math.sign(result) != '-1') {
        const interval = setInterval(function() {
            if (result > 60000) result -= 1000  
    
            if(result < 60000) {
                
                segundo_finais = result / 1000 // milisegundos em segundos = 60s aproximadamente
    
                console.log("SEGUNDOS FINAIS " + segundo_finais);
    
                clearInterval(interval)
    
                tempo_segundo(segundo_finais)
                console.log("menor q " + result);
            }
    
            console.log(result);
        }, 1000) // 1s
   // }
}

const tempo_segundo = (s) => {
    let segundos =  Math.ceil(s)
    modal_refreshToken(segundos)
}

const modal_refreshToken = (time) => {
    let segundos = time

    const container_token = document.querySelector('.container-token')

    container_token.style.display = 'flex'

    const element_tempo = container_token.querySelector('h2')

    console.log("SEG "+segundos + " aqui " + Math.sign(segundos));

    if(Math.sign(segundos) != '-1') {
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
}

const pegar_token_json = (token) => {
    try {
      return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
      return null;
    }
}