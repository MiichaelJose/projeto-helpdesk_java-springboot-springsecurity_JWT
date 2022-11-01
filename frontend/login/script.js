
function logar() {
    const url = "http://localhost:8080/login"
   
    const usuario = document.getElementById("usuario").value

    const senha = document.getElementById("senha").value

    fetch(url, {
        method: "POST",
        headers: {"Content-Type": "application/json"},     
        body: JSON.stringify({
            'cpf':usuario,
            'senha':senha
        })
    })
    .then(resp => resp)
    .then(data => {
        if(data.status == 200) {
            data.json().then( result => {
                criar_cookie(result.token_acesso, result.token_atualizado)
                const data_user = pegar_token_json(result.token_acesso);
                const funcao_user = data_user.roles[0];
                console.log('oi');
                if(funcao_user === "ADMIN") {
                    
                    localStorage.setItem("id", data_user.id) 
                    document.location.href = "../main"
                }   
            })
        }else {
            alert("dados incorretos")
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







const modelcadastrar = () => {

}