# TheatersInfo Project - Spring Profile Implementation Guide

## Overview
Your TheatersInfo microservice now implements complete Spring Profile concept with three profiles: **prod**, **dev**, and **local**.

---

## Project Structure

```
src/main/java/com/alg/minfo/config/
├── SecurityConfig.java                    (Existing - permissive security)
├── ProdDatabaseConfig.java               (NEW - @Profile("prod"))
├── DevDatabaseConfig.java                (NEW - @Profile("dev"))
├── LocalDatabaseConfig.java              (NEW - @Profile("local"))
├── ProfileEnvironmentChecker.java        (NEW - Logs active profile on startup)
└── AppProperties.java                    (NEW - @ConfigurationProperties)

src/main/java/com/alg/minfo/service/
├── LoggingService.java                   (NEW - Interface for logging)
└── serviceimpl/
    ├── ProdLoggingService.java           (NEW - @Profile("prod"))
    ├── DevLoggingService.java            (NEW - @Profile({"dev", "local"}))
    └── ... (existing services)

src/main/resources/
├── application.yml                       (Base config - sets profile=prod)
├── application-dev.yml                   (NEW - Development config with H2)
└── application-local.yml                 (NEW - Local config without config server)

src/main/java/com/alg/minfo/controller/
└── mdetails.java                         (UPDATED - Added LoggingService)
```

---

## Profiles Explained

### 1. **PROD Profile** (Production)
- **Activation:** `spring.profiles.active: prod` (in application.yml)
- **Database:** MySQL (fetched from Config Server at localhost:8888)
- **Logging:** JSON format (suitable for log aggregation)
- **Config Source:** Git repo via Config Server
- **Use Case:** When app is running in production with config server

**Classes that load:**
- `ProdDatabaseConfig` (@Profile("prod"))
- `ProdLoggingService` (@Profile("prod"))

**Run command:**
```powershell
.\mvnw.cmd spring-boot:run -Dspring.profiles.active=prod
# or
java -jar target/MINFO-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

---

### 2. **DEV Profile** (Development)
- **Activation:** `-Dspring.profiles.active=dev`
- **Database:** H2 In-Memory (jdbc:h2:mem:theatersdb_dev)
- **Logging:** Human-readable format with emojis
- **Config:** From `application-dev.yml` (no config server needed)
- **Use Case:** For local development and testing
- **Features:**
  - SQL logging enabled
  - H2 console accessible at `http://localhost:8080/h2-console`
  - DEBUG level logging

**Classes that load:**
- `DevDatabaseConfig` (@Profile("dev"))
- `DevLoggingService` (@Profile({"dev", "local"}))

**Run command:**
```powershell
.\mvnw.cmd spring-boot:run -Dspring.profiles.active=dev
# or
java -jar target/MINFO-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

---

### 3. **LOCAL Profile** (Local Testing - No Config Server)
- **Activation:** `-Dspring.profiles.active=local`
- **Database:** H2 In-Memory (jdbc:h2:mem:theatersdb_local)
- **Logging:** Human-readable format with emojis
- **Config:** From `application-local.yml` (NO config server dependency)
- **Use Case:** When config server is down or you want to run standalone
- **Features:**
  - Minimal dependencies
  - H2 console accessible
  - INFO level logging

**Classes that load:**
- `LocalDatabaseConfig` (@Profile("local"))
- `DevLoggingService` (@Profile({"dev", "local"}))

**Run command:**
```powershell
.\mvnw.cmd spring-boot:run -Dspring.profiles.active=local
# or
java -jar target/MINFO-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```

---

## Key Spring Profile Annotations Used

### 1. `@Profile("prod")` - Single Profile
```java
@Configuration
@Profile("prod")
public class ProdDatabaseConfig {
    // Loads ONLY when profile = prod
}
```

### 2. `@Profile({"dev", "local"})` - Multiple Profiles
```java
@Component
@Profile({"dev", "local"})
public class DevLoggingService {
    // Loads when profile is EITHER dev OR local
}
```

### 3. `@ConfigurationProperties` - Property Binding
```java
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    // Reads app.* properties from application-{profile}.yml
}
```

### 4. `CommandLineRunner` - Profile Detection
```java
@Bean
public CommandLineRunner printActiveProfile(Environment env) {
    return args -> {
        String[] activeProfiles = env.getActiveProfiles();
        // Logs which profile is active on startup
    };
}
```

---

## Configuration Properties by Profile

### DEV Profile (application-dev.yml)
```yaml
app:
  environment: development
  log-level: DEBUG
  cache:
    ttl: 60              # 60 seconds
    enabled: false       # Cache disabled in dev
  notification:
    enabled: false
    type: MOCK           # Mock notifications
```

### LOCAL Profile (application-local.yml)
```yaml
app:
  environment: local
  log-level: INFO
  cache:
    ttl: 120             # 120 seconds
    enabled: true        # Cache enabled
  notification:
    enabled: false
    type: MOCK           # Mock notifications
```

### PROD Profile
Profile-specific config is served by Config Server from Git repo:
- File: `minfo-service-prod.yml` (in your git repo)
- Contains: Real database credentials, cache settings, notification config

---

## Quick Start Commands

### 1. Build the project
```powershell
.\mvnw.cmd clean package -DskipTests
```

### 2. Run with different profiles

**Production (with Config Server running on localhost:8888):**
```powershell
.\mvnw.cmd spring-boot:run -Dspring.profiles.active=prod
```

**Development (with H2 database):**
```powershell
.\mvnw.cmd spring-boot:run -Dspring.profiles.active=dev
```

**Local (standalone, no config server needed):**
```powershell
.\mvnw.cmd spring-boot:run -Dspring.profiles.active=local
```

### 3. Access the application

**Base URL:** `http://localhost:8080`

**H2 Console (dev/local only):**
- URL: `http://localhost:8080/h2-console`
- Driver: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:theatersdb_dev` (dev) or `jdbc:h2:mem:theatersdb_local` (local)
- Username: `sa`
- Password: (empty)

### 4. Test the application

**Create a theatre (same for all profiles):**
```powershell
$body = @{
    theatreName = "Landmark Cinema"
    city = "Bengaluru"
    maps = "https://maps.example.com/landmark"
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/theatre/save" `
  -Method POST `
  -ContentType 'application/json' `
  -Body $body
```

**Create a movie (same for all profiles):**
```powershell
$body = @{
    movieName = "Interstellar"
    language = "English"
    theatredetails = @("Landmark Cinema")
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8080/movie/save" `
  -Method POST `
  -ContentType 'application/json' `
  -Body $body
```

**Observe profile-specific logging in console:**

*When running with DEV profile:*
```
🎭 [DEV] Theatre Saved: Landmark Cinema
🎬 [DEV] Movie Saved: Interstellar
```

*When running with LOCAL profile:*
```
🎭 [DEV] Theatre Saved: Landmark Cinema
🎬 [DEV] Movie Saved: Interstellar
```

*When running with PROD profile (JSON format):*
```
{"level":"INFO","event":"THEATRE_SAVED","theatreName":"Landmark Cinema","environment":"PROD"}
{"level":"INFO","event":"MOVIE_SAVED","movieName":"Interstellar","environment":"PROD"}
```

---

## Environment Variables (Alternative to Command Line)

Instead of `-Dspring.profiles.active=local`, you can set environment variable:

**PowerShell:**
```powershell
$env:SPRING_PROFILES_ACTIVE="local"
.\mvnw.cmd spring-boot:run
```

**Command Prompt:**
```cmd
set SPRING_PROFILES_ACTIVE=local
.\mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
export SPRING_PROFILES_ACTIVE=local
./mvnw spring-boot:run
```

---

## Common Use Cases

### Use Case 1: Run locally without Config Server (recommended for development)
```powershell
.\mvnw.cmd spring-boot:run -Dspring.profiles.active=local
```
✅ No config server needed  
✅ H2 database included  
✅ Quick debugging  

### Use Case 2: Run with all features but mock data (DEV environment)
```powershell
.\mvnw.cmd spring-boot:run -Dspring.profiles.active=dev
```
✅ SQL logging enabled  
✅ H2 console accessible  
✅ Debug logging  

### Use Case 3: Run in production (with Config Server)
```powershell
.\mvnw.cmd spring-boot:run -Dspring.profiles.active=prod
```
✅ Real MySQL database  
✅ Production logging  
✅ Config server integration  

### Use Case 4: Run JAR file with different profiles
```powershell
java -jar target/MINFO-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```

---

## H2 Console Access

**Only available in dev and local profiles**

1. Start app with `local` or `dev` profile
2. Navigate to: `http://localhost:8080/h2-console`
3. Connection details:
   - **JDBC URL:** `jdbc:h2:mem:theatersdb_dev` (dev) OR `jdbc:h2:mem:theatersdb_local` (local)
   - **User Name:** `sa`
   - **Password:** (leave empty)
4. Click "Connect"

---

## Troubleshooting

### Problem: "Config server not available" error in PROD profile
**Solution:** Make sure minfoconfig (config server) is running on localhost:8888
```powershell
# Start config server (if available)
cd ../minfoconfig
.\mvnw.cmd spring-boot:run
```

### Problem: "Database connection error" in PROD profile
**Solution:** Verify the database configuration in your Config Server Git repo (minfo-service-prod.yml)

### Problem: Cannot access H2 console
**Solution:** H2 console is only available in `dev` or `local` profiles. Check:
```powershell
# Make sure you're running with correct profile
.\mvnw.cmd spring-boot:run -Dspring.profiles.active=local
```

### Problem: "Port 8080 already in use"
**Solution:** Change port in profile YAML or kill existing process:
```powershell
netstat -ano | findstr :8080  # Find process using port 8080
taskkill /PID <PID> /F         # Kill process
```

---

## Next Steps

1. ✅ Build and test with each profile:
   ```powershell
   .\mvnw.cmd clean package -DskipTests
   java -jar target/MINFO-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
   ```

2. ✅ Test API endpoints and verify profile-specific logging

3. ✅ (Optional) Add more profile-specific implementations:
   - Different cache strategies (prod vs dev)
   - Different notification services (real vs mock)
   - Different data validation levels

4. ✅ (Optional) Extend AppProperties to include other profile-specific configs

5. ✅ Document in your Config Server Git repo what each profile expects

---

## Summary

Your TheatersInfo app now fully implements Spring Profiles with:
- ✅ Three profiles: prod, dev, local
- ✅ Profile-specific database configs (@Profile annotation)
- ✅ Profile-specific service implementations
- ✅ Profile-specific properties (YAML files)
- ✅ Profile detection on startup (logs active profile)
- ✅ No code changes needed to switch profiles
- ✅ Ready for Kafka integration (can be profile-specific too)

