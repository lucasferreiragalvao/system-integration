# System Integration

## System Integration é uma api responsável por transformar dados desnormalizados em um arquivo json normalizado

# Sumário

- [Pré-requisitos](#Pré-requisitos)
- [Configuração](#Configuração)
- [Testes](#Testes)
- [Executar](#Executar)

# Pré-requisitos

- [Java-11](https://sdkman.io/install)
- [Java-3.6.3 ou superior](https://maven.apache.org/)
- [Docker-24.0.5 ou superior](https://docs.docker.com/desktop/install/linux-install/)

# Configuração

**Faça um clone do projeto:**

https<br/>
https://github.com/lucasferreiragalvao/system-integration.git

**Instale as dependências do projeto:**<br/>

```
Java 11 - SDKMAN:
https://sdkman.io/install
sdk i java 11.0.2-open
```

# Testes

Para executar os testes do projeto, execute o comando abaixo

```
sdk use java 11.0.2-open
mvn clean install ou mvn test
```

Foram criados testes unitários e de integração.

# Executar

```
Docker
Na raiz do projeto execute:

docker build -t [nome-container] . (Para gerar a imagem do projeto)
Exemplo: docker build -t my-java-app

E depois:
docker run -p 8080:8080 [nome-container]
Exemplo:
docker run -p 8080:8080 my-java-app

```

```
Java
Na raiz do projeto execute:
mvn clean install (Instalar as dependências do projeto)

Depois o comando:
mvn package 

E por fim:
java -jar target/system-integration-1.0-SNAPSHOT.jar
```

# Testando o endpoint
```
Exemplo de CURL para testar o endpoint:
curl  -X POST \
  'http://localhost:8080/api/normalize-file' \
  --form 'file=@/home/galvao/Downloads/data_1.txt'
  
  Onde o caminho do arquivo deve ser do arquivo que deseja normalizar
  
  Caso prefira a chamada também pode ser a partir das principais ferramentas do
  mercado para realizar requsição (Postman e Insomnia)
  
  Observação: o campo file deve ser enviado como multipart/formdata
```

Exemplo de retorno
![img.png](src/main/resources/images/img.png)
