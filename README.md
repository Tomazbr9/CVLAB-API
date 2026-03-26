# CVLab API - Backend Service

RESTful API desenvolvida em Java e Spring Boot para o ecossistema CVLab. Este serviço é o núcleo do aplicativo de criação de currículos, responsável por gerenciar a persistência de dados, aplicar regras de negócio do modelo Freemium/Premium, processar pagamentos nativos e renderizar templates dinâmicos para exportação.

---

## Tecnologias e Arquitetura

- **Linguagem & Framework**: Java + Spring Boot
- **Geração de Documentos**: Thymeleaf (Motor de renderização de HTML dinâmico com suporte a conversão de alta fidelidade para PDF)
- **Inteligência Artificial**: Spring AI (Integração para reescrita inteligente e otimização de textos profissionais)
- **Pagamentos & Assinaturas**: Google Play Developer API (Validação server-side de recibos para In-App Purchases e Subscriptions)
- **Persistência**: Spring Data JPA (PostgreSQL / Banco de Dados Relacional)
- **Segurança**: Autenticação e autorização via tokens (JWT), com controle de rotas baseado no plano do usuário (Free vs. Premium)

---

## Funcionalidades Principais (Core Domain)

- **Motor de Renderização Dinâmica**: Recebe o payload (DTO) com os dados do usuário e processa templates HTML sob medida utilizando Thymeleaf, prontos para pré-visualização em WebViews (retornando texto leve) ou conversão final em binário (PDF).

- **Controle de Estado de Pagamentos**: Integração direta com os servidores do Google para validar tokens de compra (PurchaseToken). Diferencia compras únicas (produtos in-app) de assinaturas recorrentes, garantindo a proteção das rotas pagas.

- **Gestão de Regras de Negócio (SaaS Freemium)**:
    - Bloqueio inteligente de criação de múltiplos currículos para usuários Free.
    - Gerenciamento de créditos de uso da IA (Controle rigoroso no banco de dados para a compra avulsa).
    - Aplicação dinâmica de marca d'água nos documentos gerados conforme o status da licença do usuário.

- **API de Templates Dinâmicos**: Fornece aos clientes (Mobile/Web) a lista de layouts de currículo disponíveis no sistema, permitindo a adição de novos designs no backend sem necessidade de atualizar os aplicativos nas lojas.
