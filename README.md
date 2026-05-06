API REST focada somente no backend de um comércio com autenticação JWT e gerenciamento de pedidos.

✨ Principais Funcionalidades
Catálogo público de produtos com filtro por categoria e paginação
Autenticação JWT com cadastro de usuários
Histórico de compras do usuário autenticado
Os dados são salvos no H2-Database um banco de dados que pode ser acessado via web por localhost:8080

🛠 Stack Tecnológica
Backend	Banco	Segurança	Ferramentas
Java 17+	H2-Database	Spring Security + JWT	Maven
Spring Boot 3.x	JPA/Hibernate	BCrypt	MapStruct
Spring Data JPA	Flyway		Lombok
Spring Validation			JUnit 5

🚀 Como Executar
Pré-requisitos
Java 17+
Maven 3.8+
Postman/Insomnia (para testar APIs)

1. Passo
baixar projeto e abrir em alguma IDE que suporte Java17+
2. Passo
rodar projeto e acessar no postman
3. Testar APIs

```text
# Listar produtos (público)
GET http://localhost:8080/produtos

# Cadastro
POST http://localhost:8080/auth/register

# Login (pega o JWT)
POST http://localhost:8080/auth/login

# Meus pedidos (com token)
GET http://localhost:8080/pedidos
Authorization: Bearer SEU_JWT_TOKEN


✅ GET /produtos - Lista produtos (público)
✅ GET /produtos/{id} - Detalhes do produto
✅ POST /auth/register - Cadastro de usuário
✅ POST /auth/login - Autenticação JWT
🔒 GET /pedidos - Histórico de compras
🔒 POST /pedidos - Finalizar compra
🔒 POST /enderecos - Cadastrar endereço
```

## Estrutura do Projeto

```text
src/main/java/br/edu/utfpr/pw44s/
├── controller/     # APIs REST
├── dto/            # Data Transfer Objects
├── mapper/         # MapStruct mappers
├── model/          # Entidades JPA
├── repository/     # Spring Data JPA
├── service/        # Regras de negócio
└── security/       # JWT + Spring Security
```
