# Migration de Spring Boot vers Spring MVC

## Changements effectués

### 1. Configuration Maven (pom.xml)
- Supprimé le parent Spring Boot
- Ajouté les dépendances Spring MVC individuelles
- Changé le packaging en `war`
- Ajouté le plugin Jetty pour le développement

### 2. Configuration d'application
- **Supprimé** : `BackendApplication.java` (@SpringBootApplication)
- **Ajouté** : `WebAppInitializer.java` (remplace web.xml)
- **Modifié** : `WebConfig.java` (configuration Spring MVC complète)
- **Ajouté** : `DataConfig.java` (configuration JPA/Hibernate)

### 3. Structure des configurations
- `WebAppInitializer` : Point d'entrée de l'application webapp
- `DataConfig` : Configuration base de données, JPA, et services
- `WebConfig` : Configuration Spring MVC, CORS, et contrôleurs

## Comment démarrer l'application

### Option 1 : Avec Maven et Jetty (recommandé pour le développement)
```bash
cd backend
mvn clean compile
mvn jetty:run
```

### Option 2 : Compilation en WAR pour déploiement
```bash
mvn clean package
# Le fichier WAR sera généré dans target/backend-1.0.0.war
```

## Avantages de Spring MVC vs Spring Boot

### Spring MVC (configuration actuelle)
✅ **Contrôle total** sur la configuration  
✅ **Plus léger** - seulement les dépendances nécessaires  
✅ **Déploiement traditionnel** en WAR sur des serveurs d'application  
✅ **Transparence** sur toutes les configurations  

### Spring Boot (ancienne configuration)  
✅ **Auto-configuration** automatique  
✅ **Démarrage rapide** pour prototyping  
✅ **JAR exécutable** avec serveur intégré  
✅ **Convention over configuration**  

## Architecture des fichiers

```
backend/
├── src/main/java/com/framework/backend/
│   ├── WebAppInitializer.java          # Point d'entrée webapp
│   ├── config/
│   │   ├── DataConfig.java             # Configuration DB/JPA
│   │   ├── WebConfig.java              # Configuration MVC
│   │   └── ServiceConfig.java          # Configuration services
│   ├── controller/
│   ├── model/
│   ├── repository/
│   └── service/
└── src/main/resources/
    └── application.properties          # Propriétés d'application
```

## Tests

Pour tester que tout fonctionne :

1. Démarrer l'application : `mvn jetty:run`
2. Accéder à H2 Console : http://localhost:8080/h2-console
3. Tester l'API REST : http://localhost:8080/api/users

## Notes importantes

- L'application est maintenant packagée en WAR au lieu de JAR
- Le serveur Jetty est configuré pour le développement
- Tous les beans sont scannés automatiquement
- Les configurations sont explicites et modifiables