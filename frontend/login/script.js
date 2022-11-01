
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

                let exp = new Date(data_user.exp*1000)
               
               
                const data_atual = new Date();
                console.log(exp);
                //console.log(data_token.getTime());

                tempo_minutes(data_user)
            
                //console.log(new Date(Date.now()));
                if(funcao_user === "ADMIN") {
                    
                    localStorage.setItem("id", data_user.id) 
                    //document.location.href = "../main"
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



const tempo_minutes = (data_user) => {
    
    let exp = new Date(data_user.exp*1000).getTime()

    let data_atual = new Date().getTime()

    let result = exp - data_atual

    let segundo_finais = 0
    console.log(result);
    
    const interval = setInterval(function() {
        result -= 1000  
      //  console.log("EXP : " + exp);

        if(result < 60000) {
            
            segundo_finais = result / 1000
            tempo_segundo(segundo_finais)

            clearInterval(interval)
           
            console.log("menor q 10000" + result);
        }

        console.log(result);
    }, 1000) // 1 segundo

   
}

const tempo_segundo = (s) => {
    let segundos =  Math.ceil(s)
   
    console.log(segundos);
    const interval = setInterval(function() {
        --segundos;
        console.log("segundos" + segundos);
        if(segundos == 0) {
            console.log("ACABOU OS SEGUNDOS");
            clearInterval(interval)
        }
    }, 1000)
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