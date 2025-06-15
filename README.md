
# CRUD USER COM ADMIN E USER COM PERMISSOES DIFERENTES

# O PROJETO CONTEM NOS ARQUIVOS UM ARQUIVO PARA SER IMPORTADO NO INSOMNIA COM AS ROTAS PRONTAS PARA SEREM UTILZIADAS

# UTILIZADO BANCO DE DADOS POSTGRES








## ROTAS PARA REGISTRO E LOGIN

• ROTA PARA REGISTRO
    
    - (POST) http://localhost:8080/auth/register

    - PASSE O CORPO DO USUARIO COMO O EXEMPLO ABAIXO

    {
	    "username":"joao",
	    "email":"joao@silva.com",
	    "password":"senha",
	    "role": "ADMIN"
    }

    - VOCE TERA A SEGUINTE RESPOSTA CONFORME O EXEMPLO, RETORNANDO OS DADOS REGISTRADOS E COM SENHA CRIPTOGRAFADA

    {
	    "username": "joao",
	    "email": "joao@silva.com",
	    "password": "$2a$10...",
	    "role": "ADMIN"
    }

    ** VOCE PODE UTILIZAR AS ROLES (ADMIN OU USER) **

• ROTA PARA LOGIN 

    - (POST) http://localhost:8080/auth/login

    - PASSE O CORPO DO USUARIO COMO O EXEMPLO ABAIXO, INFORMANDO USERNAME E PASSWORD

    {
        "username": "joao",
        "password": "senha"
    }

    - VOCE TERA A SEGUINTE RESPOSTA CONFORME O EXEMPLO, RETORNANDO O TOKEN DE AUTENTICAÇÃO PARA UTILIZAR NAS DEMAIS ROTAS 

    {
    	"message": "Token para acesso",
	    "token": "eyJhbGciOiJIUzM4NCJ9..."
    }

    **ESTE TOKEN DEVE SER UTILIZADO PARA TER PERMISSAO PARA UTILIZAR OUTRAS ROTAS DA APLICAÇÃO, SENDO NECESSARIO INSERI-LO NO CAMPO "AUTH -> Bearer token "**





## ROTAS PARA ADMIN

• ROTA PARA BUSCAR TODOS OS USUARIOS CRIADOS

    - (GET) http://localhost:8080/users

    - VOCE TERA A SEGUINTE RESPOSTA CONFORME O EXEMPLO, RETORNANDO OS DADOS DE TODOS OS USUARIOS CADASTRADOS
    [
	    {
	    	"username": "joao",
	    	"email": "joao@silva.com",
	    	"password": "$2a$10$TDPktArr...",
	    	"role": "ADMIN"
	    }
    ]

• ROTA PARA LISTAR UM USUARIO POR ID 

    - (GET) http://localhost:8080/users/admin/{ID}

    - INFORME O ID DO USUARIO QUE DESEJA BUSCAR(UTILIZAR ID DO BANCO DE DADOS) 

    - TERA COMO RESPOSTA AS INFORMAÇOES DO USUARIO BUSCADO, POR EXEMPLO 

    
    {
	    "username": "joao",
	    "email": "joao@silva.com",
	    "password": "$2a$10$LgZr0S...",
	    "role": "ADMIN"
    }

• ROTA PARA ATUALIZAR UM USUARIO QUALQUER (O ADMIN PODE ATUALIZAR TODOS OS USUARIOS REGISTRADOS) 
- IMPORTANTE: SEMPRE QUE ATUALIZADO UM USUARIO (ADMI OU USER) É NECESSARIO REFAZER O LOGIN PARA GERAR O NOVO TOKEN E UTILZIAR AS ROTAS NOVAMENTE

    - (PUT) http://localhost:8080/users/admin/{ID}
    - VOCE DEVE INFORMAR O ID DO USUARIO QUE DESEJA ALTERAR OS DADOS

    - PASSE O CORPO DO USUARIO QUE DESEJA ATUALIZAR OOS DADOS COMO O EXEMPLO ABAIXO

    {
	    "username":"maria",
	    "email":"maria@clara.com",
	    "password":"123",
	    "role":"USER"
	}

    - TERA COMO RESPOSTA AS INFORMAÇOES DO USUARIO ALTERADAS, POR EXEMPLO 

    {
	    "username":"maria",
	    "email":"maria@clara.com",
	    "password":"123",
	    "role":"USER"
	}

• ROTA PARA DELETER UM USUARIO

    - (DELETE) http://localhost:8080/users/admin/{ID}
    - VOCE DEVE INFORMAR O ID DO USUARIO QUE DESEJA DELETAR

    - TERA COMO RESPOSTA APENAS UMA CONFIRMAÇÃO DO SISTEMA QUE O USUARIO FOI REMOVIDO (204 NO CONTENT)




## ROTAS PARA USUARIO

• ROTA PARA BUSCAR O PRÓPRIO PERFIL 

    - (GET) http://localhost:8080/users/me

    - VOCE TERA A SEGUINTE RESPOSTA CONFORME O EXEMPLO, RETORNANDO OS DADOS DO PROPRIO USUARIO
    
	    {
	    	"username": "joao",
	    	"email": "joao@silva.com",
	    	"password": "$2a$10$TDPktArr...",
	    	"role": "USER"
	    }
    
• ROTA PARA ATUALIZAR O PRÓPRIO PERFIL
- IMPORTANTE: SEMPRE QUE ATUALIZADO UM USUARIO (ADMI OU USER) É NECESSARIO REFAZER O LOGIN PARA GERAR O NOVO TOKEN E UTILZIAR AS ROTAS NOVAMENTE

    - (PUT) http://localhost:8080/users/me

     - PASSE O CORPO DO USUARIO QUE DESEJA ATUALIZAR OOS DADOS COMO O EXEMPLO ABAIXO

    {
	    "username":"maria",
	    "email":"maria@clara.com",
	    "password":"123",
	    "role":"USER"
	}

    - TERA COMO RESPOSTA AS INFORMAÇOES DO USUARIO ALTERADAS, POR EXEMPLO 

    {
	    "username":"maria",
	    "email":"maria@clara.com",
	    "password":"123",
	    "role":"USER"
	}

    

    
## ESTAS SÃO TODAS A ROTAS PARA UTILIZAR, LEMBRANDO QUE PARA UTILIZAR AS ROTAS PRECISA ESTAR LOGADO COM O USUARIO E INFORMAR O TOKEN DE AUTENTICAÇÃO EM TODAS AS ROTAS PARA UTILIZA-LA
