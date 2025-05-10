# Desafio Técnico Itaú - Backend

Este projeto é uma solução para o desafio proposto neste repositório:  
🔗 [Repositório do desafio](https://github.com/feltex/desafio-itau-backend)

## 📌 Sobre o Projeto

A API foi desenvolvida utilizando **Java com Spring Boot**, seguindo os princípios de **Clean Architecture**. Ela permite o cadastro de transações e o cálculo de estatísticas em tempo real, considerando um intervalo de tempo configurável (padrão de 60 segundos, ajustável via variável de ambiente).

Todos os dados são armazenados **em memória**, conforme especificado no desafio.

### Funcionalidades implementadas

- Registro de transações com valor e data/hora.
- Cálculo de estatísticas com base nas transações dos últimos *X* segundos:
    - ✅ Total de transações (`count`)
    - ✅ Soma dos valores (`sum`)
    - ✅ Média (`avg`)
    - ✅ Maior valor (`max`)
    - ✅ Menor valor (`min`)
- Limpeza total das transações registradas.
- Tempo de cálculo e estatísticas otimizadas para performance.
- Implementação de regras de negócio como:
    - Não aceitar valores negativos.
    - Não aceitar transações com data futura.
- Suporte a configuração de intervalo de análise via variável de ambiente (`STATISTICS_SECONDS`).

## 🚀 Endpoints da API

| Método | Endpoint         | Descrição                           |
|--------|------------------|-------------------------------------|
| POST   | `/transacao`     | Cadastra uma nova transação         |
| DELETE | `/transacao`     | Remove todas as transações          |
| GET    | `/estatistica`   | Retorna estatísticas das transações |

## 🔧 Tecnologias e Ferramentas Utilizadas

- **Java 17**
- **Spring Boot**
- **Clean Architecture**
- **Swagger/OpenAPI** para documentação da API
- **GraalVM** para geração de executável nativo
- **Docker** para containerização da aplicação

## ✅ Extras implementados

- ✅ **Testes unitários** e de integração com cobertura das principais regras de negócio
- ✅ **Tratamento de erros estruturado**, com mensagens de erro customizadas
- ✅ **Swagger UI** acessível via `/swagger-ui.html` para facilitar testes e visualização da API
- ✅ **Aplicação containerizada com Docker**
- ✅ **Configuração dinâmica** do tempo de análise via variável de ambiente (`STATISTICS_SECONDS`)

## 📦 Como Executar o Projeto

### Requisitos

- Docker (recomendado) ou Java 21+ e Maven

### Com Docker

```bash
docker-compose up -d
```


### Com Java

```bash
export STATISTICS_SECONDS=60
./mvnw spring-boot:run
```