# Corrections pour Tomcat 10 - Documentation

## Problèmes identifiés et corrigés

### 1. Absence de SLF4J (Erreur principale)
**Problème** : "No SLF4J providers were found" - Cette erreur empêchait le démarrage des listeners Spring

**Solution** :
- Ajout de `slf4j-api` version 2.0.9
- Ajout de `slf4j-simple` version 2.0.9
- Création du fichier de configuration `simplelogger.properties`

### 2. Fuite mémoire du driver JDBC H2
**Problème** : Le driver JDBC H2 n'était pas désenregistré lors de l'arrêt de l'application

**Solution** :
- Création de `DatabaseCleanupListener.java` pour gérer proprement le désenregistrement des drivers JDBC
- Enregistrement du listener dans `WebAppInitializer.java`
- Le listener nettoie tous les drivers JDBC au shutdown

### 3. Versions optimisées pour Tomcat 10.1.31
**Configuration finale** :
- Spring Framework 6.0.13 (Jakarta EE 9+)
- Hibernate 6.2.13.Final
- Jakarta Servlet API 6.0.0
- Jakarta Validation API 3.0.2
- SLF4J 2.0.9

## Fichiers modifiés

1. **pom.xml** - Ajout des dépendances SLF4J et optimisation pour Tomcat 10
2. **WebAppInitializer.java** - Ajout du DatabaseCleanupListener
3. **DatabaseCleanupListener.java** (nouveau) - Nettoyage du driver JDBC
4. **simplelogger.properties** (nouveau) - Configuration SLF4J

## Déploiement sur Tomcat 10.1.31

### ✅ Méthode recommandée : Script deploy.bat
Le script est déjà configuré pour votre Tomcat 10.1.31

1. Ouvrez un terminal dans le dossier backend :
   ```batch
   cd "d:\etudes\Mr_Naina\Framework\Projet\projet-framework\backend"
   ```

2. Lancez le script de déploiement :
   ```batch
   deploy.bat
   ```

Ce script va :
- ✓ Compiler le projet (déjà fait)
- ✓ Arrêter Tomcat
- ✓ Supprimer l'ancienne version
- ✓ Copier le nouveau WAR
- ✓ Redémarrer Tomcat

### Méthode 2 : Interface Tomcat Manager
1. Accédez à : http://localhost:8080/manager
2. Section "Fichier WAR à déployer"
3. Sélectionnez : `d:\etudes\Mr_Naina\Framework\Projet\projet-framework\backend\target\backend-1.0.0.war`
4. Cliquez sur "Déployer"

### Méthode 3 : Copie manuelle
```batch
copy "d:\etudes\Mr_Naina\Framework\Projet\projet-framework\backend\target\backend-1.0.0.war" "D:\logiciels\apache-tomcat-10.1.31\webapps\backend.war"
```

## Vérification du déploiement

1. **Vérifiez les logs** (aucune erreur attendue) :
   ```
   D:\logiciels\apache-tomcat-10.1.31\logs\catalina.out
   ```

2. **Vous devriez voir** :
   - ✅ "Application démarrée - DatabaseCleanupListener initialisé"
   - ✅ Pas d'erreurs "SEVERE"
   - ✅ Pas d'avertissements SLF4J
   - ✅ Pas d'avertissements sur les drivers JDBC

3. **Testez l'application** :
   - Page JSP : http://localhost:8080/backend/reservations
   - API : http://localhost:8080/backend/api/...

## Configuration système

- ✅ Tomcat 10.1.31 (Jakarta EE 9+)
- ✅ Java 17
- ✅ Spring 6.0.13
- ✅ Hibernate 6.2.13
- ✅ SLF4J 2.0.9

## Fichier WAR généré

- **Nom** : backend-1.0.0.war
- **Taille** : 35.0 MB
- **Date** : 06-Feb-2026 16:49
- **Localisation** : `d:\etudes\Mr_Naina\Framework\Projet\projet-framework\backend\target\backend-1.0.0.war`

## Résolution des problèmes

Si vous rencontrez encore des erreurs :

1. **Vérifiez la version de Tomcat** :
   - Tomcat 10+ requis pour Jakarta EE 9+
   - Version actuelle configurée : 10.1.31 ✓

2. **Vérifiez Java** :
   - Java 17+ requis
   - Exécutez : `java -version`

3. **Consultez les logs** :
   - `D:\logiciels\apache-tomcat-10.1.31\logs\catalina.out`
   - `D:\logiciels\apache-tomcat-10.1.31\logs\localhost.<date>.log`
