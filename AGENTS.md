# AGENTS.md

## Visão Geral do Projeto
GestFood - Aplicação Spring Boot para [finalidade]

## Estrutura do Projeto
```
src/
├─── main/
│   ├─── java/com/gestfood/gestfood/
│   │   │    GestfoodApplication.java
│   │   ├─── business/                      # Lógica de negócio e Data Transfer Object
│   │   │   ├─── dto/
│   │   │   └─── service/
│   │   ├─── model/                         # JPA Repositories e Entidades JPA
│   │   │   ├─── entity/
│   │   │   └─── repository/
│   │   └─── presentation/controller/       # REST Controllers
│   └─── resources/
│       │   application.properties
│       ├─── static/                        # Arquivos estáticos
│       │   ├─── css/
│       │   └─── js/
│       └─── templates/                     # Templates HTML
│           ├─── boards/
│           ├─── products/
│           └─── users/
└── test/
```

## Tecnologias
- Java 25
- Spring Boot 3.5.8
- Spring Data JPA
- PostgreSQL 18.1
- Thymeleaf

## Endpoints da API

### Mesas
| Método | Endpoint | Descrição | Autenticação |
| ------ | -------- | --------- | ------------ |
| GET | `/api/boards` | Lista todas as mesas | Não(ainda) |
| GET | `/api/boards/{id}` | Busca mesa por ID | Não(ainda) |
| POST | `/api/boards` | Cadatra nova mesa | Não(ainda) |
| PUT | `/api/boards/{id}` | Atualiza mesa | Não(ainda) |
| DELETE | `/api/boards/{id}` | Remove mesa | Não(ainda) |

### Produtos
| Método | Endpoint | Descrição | Autenticação |
| ------ | -------- | --------- | ------------ |
| GET | `/api/products` | Lista todos os produtos | Não(ainda) |
| GET | `/api/products/{id}` | Busca produto por ID | Não(ainda) |
| POST | `/api/products` | Cadatra novo produto | Não(ainda) |
| PUT | `/api/products/{id}` | Atualiza produto | Não(ainda) |
| DELETE | `/api/products/{id}` | Remove produto | Não(ainda) |

### Funcionários
| Método | Endpoint | Descrição | Autenticação |
| ------ | -------- | --------- | ------------ |
| GET | `/api/employees` | Lista todos os funcionários | Sim(ADMIN) |
| GET | `/api/employees/{id}` | Busca funcionário por ID | Sim(ADMIN) |
| POST | `/api/employees` | Cadatra novo funcionário | Sim(ADMIN) |
| PUT | `/api/employees/{id}` | Atualiza funcionário | Sim(ADMIN) |
| DELETE | `/api/employees/{id}` | Remove funcionário | Sim(ADMIN) |

## Comandos Úteis
```bash
# Compilar
./mvnw clean install

# Executar
./mvnw spring-boot:run

# Testes
./mvnw test

# Build para produção
./mvnw clean package -DskipTests
```

## Convenções de Código
- Use @RestController para APIs REST
- Separe lógica de negócio em usiness/services
- DTOs para requisições/respostas de API
- Validação com Bean Validation (@Valid, @NotNull, etc)
- Exceções customizadas em business/services/

## Configuração
- Perfis: dev, test, prod
- Variáveis de ambiente necessárias(.env):
  - DB_URL
  - DB_USERNAME
  - DB_PASSWORD
- Porta padrão: 8080

## Padrões de Arquitetura
- Controller → Service → Repository
- Injeção de dependência via @Autowired
- ResponseEntity para respostas HTTP