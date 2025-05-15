
# Mottu Guard API

A Mottu Guard é uma API para solucionar o problema de organização dos pátios da Mottu, onde será utilizado a tecnologia Híbrida RFID + BLE para identificação da motos dentro de um ambiente fechado.

# Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Security
- Hibernate
- Banco de Dados H2 (ambiente de desenvolvimento)
- Maven
- Spring Validation
- Spring Web
- Spring DevTools
- Lombok


## Documentação da API

#### Registra usuário

```bash
  POST /auth/register
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `login` | `string` | Nome do seu usuário |
| `password` | `string` | Sua senha |


#### Login de  um usuário

```bash
  POST /auth/login
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `login` | `string` | Nome do seu usuário |
| `password` | `string` | Sua senha |

#### Retorna todas as motos

```bash
  GET /moto
```

#### Retorna a moto com certo id

```bash
  GET /moto/id/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id` | `Long` | Id da moto desejada |

#### Retorna a moto com certo modelo

```bash
  GET /moto/modelo/{modelo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `modelo` | `ModeloMoto` | Modelo da moto desejada |

#### Retorna a moto com certo status

```bash
  GET /moto/modelo/{modelo}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `status` | `StatusMoto` | Modelo da moto desejada |

#### Adiciona moto

```bash
  POST /moto/adicionarMoto/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `motoDTO` | `MotoDTO` | Moto para ser adicionada |
| `id` | `Long` | Id da UwbTag relacionada a essa moto |

#### Atualiza moto

```bash
  PUT /moto/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `motoDTO` | `MotoDTO` | Moto para ser atualizada |
| `id` | `Long` | Id da moto a ser atualizada |

#### Deleta moto

```bash
  DELETE /moto/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id` | `Long` | Id da moto a ser deletada |

#### Retorna todas as tags

```bash
  GET /uwbTag
```

#### Retorna a tag com certo id

```bash
  GET /uwbTag/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id` | `Long` | Id da tag desejada |


#### Adiciona Tag

```bash
  POST /uwbTag
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `tagDTO` | `UwbTagDTO` | Tag a ser adicionada |

#### Atualizada Tag

```bash
  PUT /uwbTag/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `tagDTO` | `UwbTagDTO` | Tag a ser adicionada |
| `id` | `Long` | Id da tag a ser atualizada |

#### Deleta Tag

```bash
  DELETE /uwbTag/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id` | `Long` | Id da tag a ser deletada |







## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/MottuGuard/back-end.git
```

Entre no diretório do projeto

```bash
  cd back-end/mottuguard
```

Compile e execute a aplicação

```bash
  mvn spring-boot:run
```

A API estará disponível em:

```bash
  http://localhost:8080
```

