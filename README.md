# CERN Equipment Maintenance Tracker (Simulated)

A full-stack workflow-driven application to track and visualize maintenance logs for scientific lab equipment.  
Built with **Spring Boot**, **React**, and **Camunda BPM** to simulate real-world engineering use-cases inspired by CERN.

---

## 🚀 Features

- 📋 Equipment maintenance logging & visualization  
- 🔄 Workflow orchestration with **Camunda BPMN**  
- 🛠️ Backend powered by **Spring Boot** + **JPA**  
- 🌐 Frontend built using **React.js**  
- 🧩 Modular architecture for easy extension  
- ☁️ Deployed on **AWS** with CI/CD via GitLab

---

## 🧱 Tech Stack

| Layer      | Tech Used                |
|------------|--------------------------|
| Frontend   | React.js, Tailwind CSS   |
| Backend    | Spring Boot, Spring Data JPA |
| Workflow   | Camunda Platform         |
| Database   | PostgreSQL / Oracle      |
| DevOps     | Docker, GitLab CI/CD, AWS |

---

## 📄 BPMN Overview

This project uses **Camunda BPMN** to define and execute maintenance processes like:
- New maintenance request handling
- Technician assignment
- Equipment inspection & logging
- Maintenance status update and closure

---

## 🛠️ Setup Instructions

1. **Clone the repo**  
   `git clone https://github.com/siddddd17/cern-maintenance-tracker.git`

2. **Backend**  
   - Navigate to `backend/`  
   - Configure `application.properties` for your DB  
   - Run: `./mvnw spring-boot:run`

3. **Frontend**  
   - Navigate to `frontend/`  
   - Run: `npm install && npm start`

---

## 🎯 Use Case

Designed for scenarios where critical lab equipment undergoes periodic or urgent maintenance. The system helps manage dependencies, track resolution progress, and visualize bottlenecks in the process.

---

## 📌 Note

This is a **simulated project** for learning/demo purposes inspired by CERN-like infrastructure and engineering workflows.

---

## 📃 License

MIT License



