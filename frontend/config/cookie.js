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