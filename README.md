# WSWork Cars
Projeto criado para o teste da empresa ``WS Work`` 

Você pode encontrar informações sobre os endpoints [aqui](/API_DOCS.md)

## Pré-requisitos
* JDK 17 instalado
* MySQL Server instalado e configurado

## Configuração do banco de dados e servidor
Antes de rodar o projeto, configure as informações do MySQL e do servidor no arquivo `application.properties` localizado em `src/main/resources`:
```
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

Substitua `nome_do_seu_banco`, `seu_usuario` e `sua_senha` pelos valores adequados para o seu ambiente.

## Rodando o projeto

Para rodar o projeto, siga os passos abaixo:

* Abra um terminal na pasta raiz do projeto.
* Execute o comando `./gradlew bootRun` para iniciar o servidor.
* O servidor estará acessível em http://localhost:8080.

### Método alternativo (Compilando e rodando)

* Abra um terminal na pasta raiz do projeto.
* Compile o projeto usando o comando `./gradlew build`.
* Mova o terminal para a pasta `build/libs`
* Execute o comando `java -jar wswork_cars-1.0-SNAPSHOT.jar`