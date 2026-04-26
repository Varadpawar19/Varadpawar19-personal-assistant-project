# 🛡️ Aegis-AI — Autonomous Agentic Personal Assistant

> An enterprise-ready Agentic AI assistant built with Java and Spring Boot, powered by Google's Gemini API via Spring AI — designed for scalable, stateful, and autonomous task execution.

---

## 📌 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [License](#license)

---

## Overview

**Aegis-AI** is an autonomous agentic AI assistant that goes beyond simple chatbot interactions. Rather than responding only to direct prompts, Aegis-AI reasons over user intent, selects appropriate tools dynamically, and executes multi-step tasks autonomously — all within a stateful, memory-aware session.

The system is built on a production-grade Java/Spring Boot backbone, integrating Google Gemini as its core LLM reasoning engine via Spring AI. It is designed with enterprise scalability in mind: clean layered architecture, secure persistence, and structured tool-calling patterns.

**Why "Aegis"?**  
Aegis (from Greek mythology) was the shield of the gods — protective, powerful, and intelligent. This assistant is designed to act as a capable, autonomous shield against repetitive cognitive work.

---

## Architecture

```
┌─────────────────────────────────────────────────────┐
│                   Client / API Layer                │
│              (REST Endpoints via Spring Boot)       │
└────────────────────────┬────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────┐
│               Agentic Reasoning Core                │
│   Spring AI ←──────────────────────► Gemini API    │
│                                                     │
│   ┌─────────────────────────────────────────────┐  │
│   │         Tool Registry & Dispatcher          │  │
│   │  Intent Detection → Tool Selection → Exec   │  │
│   └─────────────────────────────────────────────┘  │
└────────────────────────┬────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────┐
│              Persistence & Memory Layer             │
│     MySQL — Conversation History & User Context     │
└─────────────────────────────────────────────────────┘
```

### Key Design Decisions

| Decision | Choice | Rationale |
|---|---|---|
| LLM Provider | Gemini API via Spring AI | Native Java ecosystem integration, avoids Python dependency |
| Persistence | MySQL | Relational structure suits conversation threading and user sessions |
| Architecture | Layered (Controller → Service → DAO) | Separation of concerns, enterprise-standard |
| Tool Orchestration | Spring AI Tool Calling | Declarative, type-safe, idiomatic for Java |

---

## Features

### ✅ Currently Implemented
- [x] Gemini API integration via Spring AI for LLM-powered responses
- [x] Structured prompt templates for consistent, parseable LLM outputs
- [x] MySQL-backed conversation persistence (stateful multi-turn memory)
- [x] REST API layer for client interaction
- [x] DTO/DAO layered architecture with clean separation of concerns
- [x] Dynamic tool-calling — autonomous function selection based on user intent
- [x] Environment-based configuration management (`.properties` profiles)

### 🚧 In Progress
- [ ] Long-term contextual memory summarization (compressing old turns)
- [ ] Streaming response support (SSE for real-time output)
- [ ] Multi-tool chaining (sequential tool invocations in a single reasoning pass)
- [ ] User authentication and session management

### 🗓️ Planned
- [ ] Web UI (Streamlit or React frontend)
- [ ] File upload and document-based Q&A
- [ ] Calendar and task management tool integrations
- [ ] Observability: logging, token usage tracking, and latency metrics

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17+ |
| Framework | Spring Boot 3.x |
| AI Integration | Spring AI |
| LLM Provider | Google Gemini API (gemini-1.5-pro) |
| Persistence | MySQL + Spring Data JPA |
| Build Tool | Maven |
| API Style | REST (JSON) |
| Dev Tools | IntelliJ IDEA, Postman, Git |

---

## Project Structure

```
aegis-ai/
├── src/
│   └── main/
│       ├── java/com/varad/aegisai/
│       │   ├── AegisAiApplication.java         # Entry point
│       │   │
│       │   ├── controller/
│       │   │   └── AssistantController.java    # REST endpoints
|       |   |
│       │   ├── dto/
│       │   |    └── request/
|       |   |    └── response/
│       │   │
│       │   ├── service/                        # Business Logic layer
│       │   │
│       │   ├── ai/                             # AI layer
│       │   │
│       │   ├── entity/                         # entities layer
│       │   │
│       │   ├── repository/
|       |   |
│       │   ├── scheduler/
│       │
│       └── resources/
│           ├── application.yml                 # Base config
│
├── pom.xml
└── README.md
```

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- MySQL 8.0+
- A Google Gemini API key ([Get one here](https://aistudio.google.com/app/apikey))


## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

---

<div align="center">

Built by [Varad Pawar](https://linkedin.com/in/varad-pawar-) · Pune, India

*"Autonomy is not a feature. It's the foundation."*

</div>
