# GreenCycle API

Projeto de API desenvolvido em Java com Spring Boot e MongoDB, voltado para a simulação de um ambiente de Cidades Inteligentes. O projeto faz parte de um exercício acadêmico da FIAP, com integração de práticas de DevOps, como CI/CD, Docker e orquestração de contêineres.

## Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.2.5** - Framework para criação de microserviços
- **MongoDB** - Banco de dados NoSQL utilizado na aplicação
- **Docker** - Ferramenta de containerização
- **Docker Compose** - Orquestração de múltiplos containers
- **GitHub Actions** - Integração contínua e entrega contínua (CI/CD)

## Funcionalidades

A GreenCycle API oferece as seguintes funcionalidades:
- Gerenciamento de coleta de residuos sólidos, como cadastro, atualização, consulta e remoção.
- Persistência de dados no MongoDB.
- API segura, com autenticação e autorização via JWT (JSON Web Tokens).

## Estrutura do Projeto

### Diretórios principais:

- **/src**: Contém o código-fonte da API.
  - **/main**: Código principal da aplicação.
    - **/java**: Pacotes Java com controladores, modelos e repositórios.
    - **/resources**: Arquivos de configuração e propriedades da aplicação (como `application.properties`).
  - **/test**: Testes unitários e de integração para a API.
  
- **/docker**: Arquivos Docker e Docker Compose para containerização e orquestração da aplicação.

- **/.github/workflows**: Arquivo `ci.yml` para configuração do pipeline de CI/CD com GitHub Actions.

## Pré-requisitos

Antes de executar a aplicação, certifique-se de ter as seguintes ferramentas instaladas:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven](https://maven.apache.org/)

## Configuração e Execução

### 1. Clonar o repositório:

```bash
git clone https://github.com/CassianoSantosTech/greecycle-fiap.git
cd greecycle-fiap
```

### 2. Configuração do Docker Compose:

O projeto utiliza MongoDB como banco de dados, configurado no arquivo `docker-compose.yml`. Certifique-se de que as variáveis de ambiente estão corretas para conectar ao banco.

```yaml
version: '3.8'

services:
  # Serviço do MongoDB
  mongodb:
    image: mongo:6.0
    container_name: mongo
    ports:
      - "27017:27017"
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: root_pass
    volumes:
      - mongodb_data:/data/db

  # Serviço da API Java (Greencycle)
  api:
    build: .
    ports:
      - "8080:8080"
    environment:
      - PROFILE=dev
      - SPRING_DATA_MONGODB_URI=mongodb+srv://cassivariedadesAdmin:admin%401234@cluster0.o10wb.mongodb.net/greencycle?retryWrites=true&w=majority
    depends_on:
      - mongodb

volumes:
  mongodb_data:
    driver: local
```

### 3. Build e Execução do Projeto

Execute os seguintes comandos para compilar e rodar o projeto localmente usando Docker Compose:

```bash
docker-compose up --build
```

O aplicativo estará disponível em `http://localhost:8080`.

### 4. CI/CD com GitHub Actions

O projeto possui um pipeline configurado com GitHub Actions para realizar build e testes automáticos em cada push ou pull request para as branches `main`, `develop`, ou `feature/**`.

#### Exemplo de configuração no arquivo `.github/workflows/ci.yml`:

```yaml
name: CI/CD Pipeline

on:
  push:
    branches:
      - main
      - develop
      - "feature/**"
  pull_request:
    branches:
      - main
      - develop
      - "feature/**"

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - name: Checkout Code
      uses: actions/checkout@v2

    - name: Set up JDK 21
      uses: actions/setup-java@v2
      with:
        java-version: '21'

    - name: Build with Maven
      run: mvn clean install

    - name: Test Application
      run: mvn test
```

### 6. Testes

Para rodar os testes unitários e de integração, use o comando:

```bash
mvn test
```

## Melhorias Futuras

- **Escalabilidade**: Orquestração com Kubernetes para ambientes de produção de larga escala.
- **Monitoramento**: Integração com ferramentas de monitoramento e logs, como Prometheus e Grafana.
- **Documentação**: Documentação técnica com swagger.
- **Autenticação e Autorização Avançadas**: Melhorar a segurança com integração de OAuth2.

## Contribuição

Sinta-se à vontade para abrir issues e enviar pull requests para contribuir com o projeto.

1. Faça um fork do projeto.
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`).
3. Commit suas alterações (`git commit -am 'Adiciona Minha Feature'`).
4. Dê um push na branch (`git push origin feature/MinhaFeature`).
5. Crie um novo Pull Request.

## Licença

Este projeto está licenciado sob a licença MIT - consulte o arquivo LICENSE.md para obter mais detalhes.
