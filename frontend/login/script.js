
function logar() {
    const url = "http://localhost:8080/login"
   
    const cpf = document.getElementById("cpf").value

    const senha = document.getElementById("senha").value

    fetch(url, {
        method: "POST",
        headers: {"Content-Type": "application/json"},     
        body: JSON.stringify({
            'cpf':cpf,
            'senha':senha
        })
    })
    .then(resp => resp)
    .then(data => {
        if(data.status == 200) {
            data.json().then(result => {
                criar_cookie(result.token_acesso)
                const data_user = pegar_token_json(result.token_acesso);
                const funcao_user = data_user.roles[0];

                //tempo_minutes(data_user)

                if(funcao_user === "ADMIN") {
                    
                    localStorage.setItem("data", JSON.stringify(data_user)) 
                    document.location.href = "../main"
                }   
            })
        }else { 
            alert("dados incorretos")
        }
    })
}




const modelcadastrar = () => {

}


const formatCPF = (i) => {
    var v = i.value;
    
    if(isNaN(v[v.length-1])){ // impede entrar outro caractere que não seja número
       i.value = v.substring(0, v.length-1);
       return;
    }
    
    i.setAttribute("maxlength", "14");
    if (v.length == 3 || v.length == 7) i.value += ".";
    if (v.length == 11) i.value += "-";
 }