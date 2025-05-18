# Solari - Payment Service

Este microsserviço é responsável por gerenciar os pagamentos do sistema, incluindo operações de criação e processamento de pagamentos. Ele faz parte do sistema de gerenciamento de pedidos do projeto **Solari**, desenvolvido no **Tech Challenge - Fase 4** da pós-graduação em Arquitetura e Desenvolvimento Java - FIAP.

---

## 🧩 Tecnologias Utilizadas

- **Java 21**: Linguagem principal do projeto.
- **Spring Boot**: Framework para criação de aplicações Java.
- **Maven**: Gerenciador de dependências e build.
- **Flyway**: Controle de versão do banco de dados.
- **JPA / Hibernate**: Persistência de dados.
- **Docker**: Containerização da aplicação.
- **JaCoCo**: Ferramenta para análise de cobertura de testes.
- **JUnit 5 + Mockito**: Frameworks para testes unitários e mocks.

---

## 🧱 Estrutura do Projeto

O projeto segue a Clean Architecture, dividindo responsabilidades em camadas bem definidas:

- **application**: Contém os casos de uso e regras de negócio.
- **domain**: Representa as entidades e objetos de domínio.
- **infrastructure**: Implementações de gateways, repositórios, controladores e configurações.
- **tests**: Testes unitários e de integração.

---

## 🚀 Como executar localmente

### Pré-requisitos
- Java 21+
- Maven
- Docker

### Passos
1. Clonar o repositório:
- git clone https://github.com/BrunaCasagrande/solari-payment-service.git
- cd solari-payment-service

2. Executar o projeto com Maven:
- mvn spring-boot:run

---

## 📌 Endpoints Principais

### Pagamentos

- **POST** `/solari/v1/payments`  
  Cria e processa um novo pagamento.

---

## ✅ Testes

Para executar os testes e gerar o relatório de cobertura com JaCoCo:

1. Executar os testes: mvn test
2. Gerar o relatório de cobertura: mvn jacoco:report
3. Acessar o relatório em:  
   file:///C:/solari/solari-payment-service/target/site/jacoco/index.html

---

## 🐳 Executando com Docker

### Build da imagem Docker:
docker build -t solari-payment-service .

### Executar o container:
docker run -p 8085:8085 solari-payment-service

### Acessar a aplicação:
http://localhost:8085/solari/v1/payments

---

## 👩‍💻 Autor

Desenvolvido por **Bruna Casagrande RM: 359536** como parte do projeto **Solari**.
