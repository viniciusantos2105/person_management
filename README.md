## Desafio API - Gerenciamento De Pessoas </h2>

## Sobre
   
  - Padrão Adapter - Adotei o padrão de projeto estrutural Adapter, que consiste em permitir transformar um objeto em outro, ele foi utilizado para converter entidades em outras
  - Padrão Factory Method - Adotei o padrão de projeto criacional Factory Method, apliquei nas classes de exceção do projeto. Esse padrão é usado quando uma classe precisa de uma maneira de criar um objeto, mas quer deixar as subclasses decidirem qual classe instanciar.
  - Docker - Utilizei o docker para conteinerizar a aplicação e facilitar o build e execução do projeto

## Clean Code
  Este projeto adere aos princípios do Clean Code e TDD. A legibilidade do código é priorizada, com variáveis, métodos e classes nomeados de maneira descritiva para indicar claramente sua função. A modularidade é mantida, com cada método executando uma única responsabilidade. Além disso, a metodologia TDD (Test Driven Development) foi seguida durante o desenvolvimento, assegurando que cada funcionalidade seja coberta por testes. Isso resulta em uma cobertura de teste de linha de código de pelo menos 80%.

## Tecnologias
  - Java 17
  - JPA/Hibernate  
  - Maven  
  - Spring Boot  
  - Spring Web  
  - Spring Data
  - Spring Doc  
  - PostgreSQL  
  - Bean Validation  
  - JUnit  
  - Docker
## Build Docker
   1 - Descompacte o projeto    
   2 - Abra a pasta person_management   
   3 - Abra o terminal e rode o comando mvn clean package   
   4 - Volte para pasta desafio_tecnico e rode o comando  docker compose up -d --build   
   5 - API fica na porta http://localhost:8080

## Build IDEA   
   1 - Abra a pasta person_management   
   2 - Abra o `application.yml` e configure o spring.datasource.username e spring.datasource.password para suas configurações   
   3 - API fica na porta http://localhost:8080

## Documentação 
  Utilizei o Swagger API para fazer a documentação do projeto, ao iniciar o projeto ele ficará disponivel   
  link: http://localhost:8080/swagger-ui/index.html#/
