# Gestione Viaggi Aziendali - REST API

## Descrizione
API RESTful sviluppata in Java con Spring Boot per la gestione di viaggi ed eventi aziendali.  
Il sistema consente la registrazione e l'autenticazione degli utenti tramite JWT, la creazione e gestione di eventi aziendali e la prenotazione dei viaggi da parte dei dipendenti.

## Tecnologie Utilizzate
- Java 17
- Spring Boot
- Spring Security + JWT
- JPA (Hibernate)
- PostgreSQL
- Maven
- Swagger (OpenAPI)
- Postman (per testing)

## Funzionalità Principali
- 🔐 Autenticazione e autorizzazione con JWT
- 👥 Gestione utenti (registrazione, login)
- 🗓️ CRUD completo per eventi aziendali
- 🚗 Gestione prenotazioni viaggi
- 📄 Documentazione API con Swagger
- 🛡️ Validazione dati e gestione errori

## Come Eseguire il Progetto
1. Clona la repository:
   ```bash
   git clone https://github.com/uba92/gestione-viaggi-aziendali-jwt.git
   
2.Configura il Database
Crea un database PostgreSQL.
Inserisci le credenziali nel file src/main/resources/application.properties:
-spring.datasource.url=jdbc:postgresql://localhost:5432/nome_database
-spring.datasource.username=tuo_username
-spring.datasource.password=tuo_password

3.Avvia l'applicazione:
-mvn spring-boot:run

4.Accedi alla documentazione Swagger:
-http://localhost:8080/swagger-ui/index.html

5.Esempi di Endpoint:
Metodo	  Endpoint	               Descrizione
POST	    /api/auth/register	     Registrazione nuovo utente
POST	    /api/auth/login	         Login e ricezione JWT Token
GET	      /api/events	             Visualizza eventi aziendali
POST	    /api/bookings	           Prenota un viaggio
