<h1> API TODO </h1>


<h2> :page_facing_up: Descrição do projeto </h2>
<p align="justify">
  API para criação de TODO utilizando autenticação basica
</p>



<h2> :golf: Índice de conteúdos </h2>

   * [Início](#título-do-projeto)
   * [Descrição](#-descrição-do-projeto)
   * [Tecnologias](#-tecnologia)
   * [Como rodar a aplicação](#-como-rodar-a-aplicação)
   * [Novidades e correções](#-novidades-e-correções)


<!--* [Exemplo de criação de tópico](#-nome-do-topico-criado)-->
Crie um índice de navegação entre os tópicos.

<h2> :dart: Tecnologias</h2>

- Springboot
- Lombok
- Spring Security Basic Auth
- Junit
- BCrypt
- Swagger




<h2> :hammer: Como rodar a aplicação </h2>

No terminal, clone o projeto:
```
cd nome-do-projeto
```
```
mvn clean install
```
```
mvn spring-boot:run
```


<!--```-->
<!-- exemplo de código bash-->
<!--```-->


<h2> :newspaper: Novidades e correções</h2>

<h3> 1.0.0 Primeira versão</h3>

- **Funcionalidade principal:** Rota inicial para criação de usuario, /user/new, somente depois de autenticado permite a criação de novos TODO

- **Correção execução de testes:** Necessário ajuste de plugin e versão do Junit para execução de testes

- **Novas funcionalidades em desenvolvimento**