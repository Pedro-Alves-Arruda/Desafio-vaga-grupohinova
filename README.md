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
