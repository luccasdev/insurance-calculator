
# API Desafio de Seguros

Esse projeto consiste em uma API para cadastrar, atualizar e consultar produtos de seguros.

Sendo a principal caracteristica o cálculo automático do preço tarifado levando em consideração o preço base e a categoria do seguro.



## Tecnologias utilizada
- Java 17
- Spring Boot
- Clean Architecture
- Jaeger (Tracing)
- Docker
- Lombok

## Como executar o projeto
O projeto foi configurado com o docker-compose, para rodar o projeto basta executar os seguintes comandos:

```
./gradlew clean build
```

```
docker-compose up
```

## Documentação da API
Para facilitar foi adicionado uma collection com todos os endpoints já configurados, o nome do arquivo é:
#### **`insurance-calculator.postman_collection.json`**

## Decisões

#### Design Pattern Strategy:
Optei por utilizar o Strategy nesse cenário de cálculos de impostos de diferentes categorias de seguros, por várias razões:

**Flexibilidade**: Permite adicionar, remover ou alterar estratégias de cálculo de impostos de forma independente.

**Manutenção Simplificada**: Cada estratégia está encapsulada em sua própria classe, facilitando a manutenção e reduzindo o impacto de alterações.

**Adaptabilidade Dinâmica**: Permite a seleção dinâmica da estratégia de cálculo com base nas condições em tempo de execução.

**Melhor Testabilidade**: Facilita a criação de testes unitários específicos para cada estratégia.

**Organização e Legibilidade**: Torna o código mais modular, organizado e compreensível, com cada estratégia em sua própria classe.

#### Utilização do Clean Architecture:
Foi decidido utilizar o Clean Architecture baseando-se em um cenário onde exista a necessidade de construir **um sistema altamente modular e independente de frameworks externos.**

Essa abordagem permite separar as preocupações de negócios das implementações técnicas, facilitando a manutenção e o teste de cada componente de forma isolada. Além disso, a Clean Architecture promove a reutilização de código, escalabilidade e flexibilidade na evolução do sistema ao longo do tempo.

Foi adaptado conforme a necessidade do projeto, mas mantendo os conceitos e ganhos da utilização desse padrão de arquitetura.

#### Utilização do Jaeger:
Baseando-se no cenário que em um sistema escalável e com diversos clientes em produção utilizando, se faz necessário ferramentas de tracing para facilitar o troubleshooting.

Evidência da utilização do Jaeger no projeto:
![Logo](https://i.imgur.com/Ikih9j8.png)

## Testes Unitários e Integrados

No projeto foram criados testes unitários e testes integrados, garantindo 100% de cobertura em todo o projeto, conforme a evidência do Jacoco abaixo:

![Logo](https://i.imgur.com/oMNvIxR.png)

## Autor

- [@luccasdev](https://www.linkedin.com/in/lucasrsouza-ti/)

