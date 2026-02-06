@echo off
REM ===================================================================
REM Script de deploiement du backend vers Tomcat
REM ===================================================================

echo.
echo ===================================================================
echo   Deploiement du projet Backend vers Tomcat
echo ===================================================================
echo.

REM Configuration - MODIFIER SELON VOTRE ENVIRONNEMENT
SET TOMCAT_HOME=D:\logiciels\apache-tomcat-10.1.31
SET TOMCAT_WEBAPPS=%TOMCAT_HOME%\webapps
SET PROJECT_DIR=%~dp0
SET WAR_FILE=backend-1.0.0.war
SET APP_NAME=backend

REM Verification de l'existence de Maven
where mvn >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERREUR] Maven n'est pas installe ou n'est pas dans le PATH
    echo Veuillez installer Maven et l'ajouter au PATH
    pause
    exit /b 1
)

REM Verification de l'existence du repertoire Tomcat
if not exist "%TOMCAT_HOME%" (
    echo [ERREUR] Tomcat n'existe pas a l'emplacement: %TOMCAT_HOME%
    echo Veuillez modifier la variable TOMCAT_HOME dans ce script
    pause
    exit /b 1
)

echo [INFO] Compilation du projet avec Maven...
cd /d "%PROJECT_DIR%"
call mvn clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo [ERREUR] La compilation Maven a echoue
    pause
    exit /b 1
)

echo.
echo [INFO] Compilation reussie!
echo.

REM Verification de l'existence du fichier WAR
if not exist "target\%WAR_FILE%" (
    echo [ERREUR] Le fichier WAR n'a pas ete genere: target\%WAR_FILE%
    pause
    exit /b 1
)

echo [INFO] Arret de Tomcat si en cours d'execution...
call "%TOMCAT_HOME%\bin\shutdown.bat" >nul 2>&1
timeout /t 5 /nobreak >nul

echo [INFO] Suppression de l'ancienne application...
if exist "%TOMCAT_WEBAPPS%\%APP_NAME%" (
    rmdir /s /q "%TOMCAT_WEBAPPS%\%APP_NAME%"
)
if exist "%TOMCAT_WEBAPPS%\%APP_NAME%.war" (
    del /f /q "%TOMCAT_WEBAPPS%\%APP_NAME%.war"
)

echo [INFO] Copie du nouveau WAR vers Tomcat...
copy "target\%WAR_FILE%" "%TOMCAT_WEBAPPS%\%APP_NAME%.war"
if %ERRORLEVEL% NEQ 0 (
    echo [ERREUR] Impossible de copier le fichier WAR vers Tomcat
    pause
    exit /b 1
)

echo.
echo [INFO] Demarrage de Tomcat...
call "%TOMCAT_HOME%\bin\startup.bat"

echo.
echo ===================================================================
echo   Deploiement termine avec succes!
echo ===================================================================
echo.
echo   L'application sera accessible a:
echo   http://localhost:8080/%APP_NAME%
echo.
echo   Pour voir les logs:
echo   %TOMCAT_HOME%\logs\catalina.out
echo.
echo ===================================================================

pause
