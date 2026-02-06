# Démarrage rapide du backend sur Tomcat 10

## ✅ État actuel
- ✅ Projet compilé avec succès
- ✅ WAR généré : `backend-1.0.0.war` (35 MB)
- ✅ Compatibilité : Tomcat 10.1.31 + Java 17
- ✅ Corrections appliquées : SLF4J + DatabaseCleanupListener

## 🚀 Déployer maintenant

### Option 1 : Script automatique (RECOMMANDÉ)
```batch
cd "d:\etudes\Mr_Naina\Framework\Projet\projet-framework\backend"
deploy.bat
```

### Option 2 : Copie manuelle rapide
```batch
copy "d:\etudes\Mr_Naina\Framework\Projet\projet-framework\backend\target\backend-1.0.0.war" "D:\logiciels\apache-tomcat-10.1.31\webapps\backend.war"
```

Puis démarrez Tomcat :
```batch
D:\logiciels\apache-tomcat-10.1.31\bin\startup.bat
```

## 🌐 Accès après déploiement
- Interface JSP : http://localhost:8080/backend/reservations
- API REST : http://localhost:8080/backend/api/
- Manager Tomcat : http://localhost:8080/manager

## 📋 Que faire ensuite ?

1. **Lancez deploy.bat** ou copiez le WAR manuellement
2. **Attendez 10-15 secondes** que Tomcat déploie l'application
3. **Vérifiez les logs** : pas d'erreurs attendues
4. **Testez l'application** via votre navigateur

## ⚠️ En cas de problème

### Vérifier que Tomcat tourne
```batch
netstat -ano | findstr :8080
```

### Consulter les logs
```batch
type "D:\logiciels\apache-tomcat-10.1.31\logs\catalina.out"
```

### Logs en temps réel
```batch
powershell Get-Content "D:\logiciels\apache-tomcat-10.1.31\logs\catalina.out" -Wait -Tail 50
```

## 📦 Fichiers générés

- `backend-1.0.0.war` → WAR à déployer
- `DatabaseCleanupListener.java` → Nettoie le driver JDBC
- `simplelogger.properties` → Configuration SLF4J
- `CORRECTIONS_TOMCAT.md` → Documentation complète

---

**Tout est prêt !** Il ne reste plus qu'à déployer avec `deploy.bat` 🎉
