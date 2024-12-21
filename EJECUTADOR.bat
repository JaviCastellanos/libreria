@echo off

:: Navegar al directorio del proyecto
cd /d "C:\Users\jc_68\Downloads\ALURA\ENTREGABLE 2\LiterAlura"
pause

:: Limpiar directorios previos de compilación
echo Limpiando compilaciones previas...
if exist out rmdir /s /q out
mkdir out
pause

:: Establecer la variable CLASSPATH temporalmente
set CLASSPATH="C:\Users\jc_68\Downloads\ALURA\ENTREGABLE 2\LiterAlura\lib\gson-2.10.1.jar"

:: Verificar la variable CLASSPATH
echo %CLASSPATH%
pause

:: Compilar los archivos .java incluyendo las librerías necesarias
echo Compilando los archivos Java...
javac -d out -cp %CLASSPATH% src\main\java\api\*.java src\main\java\*.java
pause

:: Verificar si la compilación fue exitosa
if errorlevel 1 (
    echo Error en la compilación.
    pause
    exit /b 1
)

:: Ejecutar la aplicación incluyendo las librerías necesarias
echo Ejecutando el proyecto...
java -cp "out;C:\Users\jc_68\Downloads\ALURA\ENTREGABLE 2\LiterAlura\lib\gson-2.10.1.jar" Main
pause

:: Mantener la ventana abierta
cmd /k