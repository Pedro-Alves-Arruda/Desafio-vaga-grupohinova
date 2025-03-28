# Desafio-vaga-grupohinova

Execução do docker para kafka e redis

    Abra o terminal

    *Entre na pasta Docker com comando cd Docker
    *Rode o comando docker compose up


Execução do banco de dados

    *Altere spring.datasource.url= no arquivo application.properties e adicione um caminho existente na sua maquina jdbc:h2:file:C:/Users/<seu_usuario>;AUTO_SERVER=TRUE;IFEXISTS=FALSE
    *É nevessario criar um arquivo Data_base.mv.db no diretorio C:\Users\<seu_usuario>
    *para conectar no banco, após a aplicação estar rodando entre no navegador na url localhost:8080/h2-console
    *altere JDBC URL para jdbc:h2:file:~/Data_base caso não esteja
    *Apos conectar rode o comando que esta em src/main/resources/schema.sql para construção das tabelas


Rotas REST

Rotas controller Usuarios

GET - localhost:8080/usuarios
    Retorna um List<Usuarios>

GET - http://localhost:8080/usuarios/dataInicial/dataFinal
    Retorna um List<Usuarios> nesse intervalo de tempo

    exemplo de data valida:
    http://localhost:8080/usuarios/2025-03-26T14:30:00/2025-03-26T14:30:00

POST - localhost:8080/usuarios
    Parametros: Um Json de Usuarios ex:

    {
        "name": "João Silva",
        "phone": "31997813334",
        "email": "joao.silva@email.com",
        "cpf": "12345678900",
        "zip_code": "01001-000",
        "address": "Rua das Flores",
        "number": "123",
        "complemet": "Apartamento 45",
        "status": true
    }

    name, phone, email, cpf são obrigatorios;
    formato do cpd deve ser apenas numeros.

DELETE - localhost:8080/usuarios/{id}
    
    Parametros: id do usuario a ser deletado

PUT - localhost:8080/usuarios/{id}

    parametros: 
        id do usuario a ser atualizado
        Json com os novos valores, ex:
             {
                "name": "João Silva",
                "phone": "31997813334",
                "email": "joao.silva@email.com",
                "cpf": "12345678900",
                "zip_code": "01001-000",
                "address": "Rua das Flores",
                "number": "123",
                "complemet": "Apartamento 45",
                "status": true
            }

Rotas controller Veiculos

GET - localhost:8080/veiculos
    Retorna um List<veiculos>

POST - localhost:8080/veiculos
    Parametros: Um Json de veiculos ex:

        {
            "plate": "OUI-5035",
            "advertisedPrice": 100000,
            "brandId": 59,
            "modelId": 5940,
            "fipePrice": "R$ 95.999,00",
            "ano": "2014-3",
            "usuario": {
                "id": 4
            }
        }

    name, phone, email, cpf são obrigatorios;
    formato do cpd deve ser apenas numeros.

DELETE - localhost:8080/veiculos/{id}
    
    Parametros: id do usuario a ser deletado

PUT - localhost:8080/veiculos/{id}

    parametros: 
        id do usuario a ser atualizado
        Json com os novos valores, ex:

        {
            "plate": "OUI-5035",
            "advertisedPrice": 100000,
            "brandId": 59,
            "modelId": 5940,
            "fipePrice": "R$ 95.999,00",
            "ano": "2014-3",
            "usuario": {
                "id": 4
            }
        }
            