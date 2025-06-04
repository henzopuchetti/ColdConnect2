
# 🧊 ColdConnect – API para Gestão de Emergências em Situações de Frio Extremo

A **ColdConnect** é uma API RESTful desenvolvida com Java + Spring Boot, projetada para monitorar temperaturas críticas, emitir alertas automáticos e coordenar ações emergenciais para proteger populações vulneráveis em situações de frio intenso.

---

## 🚀 Visão Geral

A API conecta sensores de temperatura, abrigos, voluntários e instituições, centralizando a **detecção de risco**, a **comunicação de emergência** e o **gerenciamento de recursos humanitários**.

Ela oferece endpoints para registrar leituras de temperatura, gerar alertas, criar ações emergenciais, cadastrar abrigos, solicitar recursos e responder solicitações — tudo com lógica automatizada e estrutura modular.

---

## ⚙️ Principais Funcionalidades da API

| Módulo                    | Descrição                                                                 |
|--------------------------|---------------------------------------------------------------------------|
| **Leitura de Temperatura** | Recebe dados geográficos e térmicos via sensores ou entrada manual.        |
| **Alerta de Frio**         | Gerado automaticamente com grau de alerta com base na temperatura.       |
| **Ação Emergencial**       | Criada automaticamente após o alerta; pode ser resgate, suporte, etc.     |
| **Abrigo**                 | Cadastro e status de abrigos com controle de capacidade.                 |
| **Solicitação de Recurso** | Abrigos solicitam mantimentos, cobertores, alimentos, etc.               |
| **Resposta à Solicitação** | Voluntários ou instituições respondem às solicitações e atualizam o status.|

---

## 🔁 Fluxo Automatizado da API

```plaintext
[Sensores IoT ou sistemas externos]
       ↓
POST /leituras → Envia dados de latitude, longitude e temperatura
       ↓
[API ColdConnect]
  ↳ Cria nova LeituraTemperatura com data/hora
  ↳ Calcula automaticamente o GrauAlerta:
       - < 0°C → GRAVE
       - < 5°C → MODERADO
       - < 10°C → LEVE
       - ≥10°C → Nenhum alerta
  ↳ Se grau válido → cria AlertaFrio e identifica a região (ex: Sul/Sudeste/Norte)
       ↓
Cria AcaoEmergencial com base no grau:
  - LEVE → "MONITORAMENTO"
  - MODERADO → "SUPORTE INTERMEDIÁRIO"
  - GRAVE → "RESGATE URGENTE"
       ↓
[Instituições e abrigos são notificados na mesma região]
       ↓
Abrigos consultam alertas ativos:
GET /alertas?regiao=...
       ↓
Abrigo cria uma solicitação de recurso (cobertores, remédios, etc.):
POST /solicitacoes
       ↓
Instituições ou voluntários acessam as solicitações:
GET /solicitacoes
       ↓
Respondem com informações, observações e atualização de status:
POST /respostas
```

---

## 📦 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5**
- **Spring Web, JPA, Validation e Security**
- **MapStruct** – Mapeamento entre entidades e DTOs
- **Lombok** – Redução de boilerplate
- **H2 Database** – Banco em memória para testes
- **Swagger/OpenAPI** – Documentação automática
- **Camadas: Controller / Service / Repository / DTO / Mapper / Model**

---

## 💡 Lógica de Negócio

A API não é apenas CRUD. Ela aplica lógica automatizada no backend para reagir a eventos em tempo real:

- **Leituras de temperatura** disparam **alertas automáticos**;
- **Ações emergenciais** são criadas com base na gravidade do alerta;
- **Solicitações e respostas** são atualizadas com o status correto da operação;
- A **região geográfica** é deduzida com base na latitude recebida.

---

## 🛡️ Segurança

- **Spring Security configurado** com acesso livre durante testes (`permitAll`).
- Ideal para evoluir com autenticação via JWT ou OAuth2.

---

## 🔍 Documentação da API

Após rodar o projeto, acesse:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Documentação JSON: `http://localhost:8080/api-docs`

---

## 🧪 Como rodar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/ColdConnect.git
   ```

2. Abra no VS Code ou Eclipse com suporte ao Maven.

3. Execute o projeto como aplicação Spring Boot.

4. Acesse `http://localhost:8080` para testar os endpoints.

5. [Clique aqui para baixar o .txt com os endpoints para colocar no postman](postman.txt)

---

## 🔮 Possíveis Melhorias Futuras

- Autenticação JWT e controle de usuários
- Integração com sensores reais (IoT, MQTT, etc)
- Notificações via SMS, WhatsApp ou Firebase
- Painel web com visualização geográfica dos alertas
- Machine Learning para prever alertas com base em histórico climático

---

