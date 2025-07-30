@echo off
REM Blokada uruchamiania w system32
if /I "%cd%"=="C:\Windows\System32" (
    echo ❌ Nie uruchamiaj tego skryptu z folderu System32!
    echo Przejdź do folderu z projektem i uruchom ponownie.
    pause
    exit /b 1
)

REM Konfiguracja użytkownika Git (możesz edytować poniższe dane)
git config user.name "Adam"
git config user.email "adam@example.com"

REM Inicjalizacja Git
git init

REM Dodanie zdalnego repo
git remote add origin https://github.com/AdmixosssXD/menelopa.git

REM Dodanie plików i commit
git add .
git commit -m "Czysty commit moda bez tokenów"

REM Ustawienie głównej gałęzi na main
git branch -M main

REM Wypchnięcie zmian
git push --force origin main

REM Dodanie i wypchnięcie taga
git tag v1.0.0
git push origin v1.0.0

echo ✅ Gotowe!
pause
