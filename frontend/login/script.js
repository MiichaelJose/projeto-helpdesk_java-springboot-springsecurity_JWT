
function logar() {
    const url = "http://localhost:8080/login"

    const usuario = document.getElementById("usuario").value

    const senha = document.getElementById("senha").value

    fetch(url, {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},     
        body: JSON.stringify({
            'usuario':usuario,
            'senha':senha
        })
    })
    .then(resp => resp)
    .then(data => {
        if(data.status == 200) {
            data.json().then( result => {
                criar_cookie(result.token_acesso, result.token_atualizado)
                const data_user = converter_jwt(result.token_acesso);
                const funcao_user = data_user.roles[0];

                if(funcao_user === "ADMIN") {
                    document.location.href = "../main"
                }   
            })
        }else {
            alert("dados incorretos")
        }
    })
}

const pegar_data_token = () => {
    //return converter_jwt((document.cookie.split('=')[1]).split(';')[0])
}

const criar_cookie = (token_acesso, token_atualizado) => {
    if(document.cookie != "") {
        console.log('cookie já esta cadastrado');
    }else {
        console.log(document.cookie);
        document.cookie = "token_acesso="+token_acesso
        document.cookie = "token_atualizado="+token_atualizado
    }
}

const apagar_cookie = () => {
    document.cookie = "token_acesso="
    document.cookie = "token_atualizado="
}

const converter_jwt = (token) => {
    try {
      return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
      return null;
    }
}


const modelcadastrar = () => {

}