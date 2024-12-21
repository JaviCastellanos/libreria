@echo off

:: Navegar al directorio del proyecto
cd /d "C:\Users\jc_68\Downloads\ALURA\ENTREGABLE 2\LiterAlura"

:: Compilar los archivos .java incluyendo la librería Gson
javac -cp "lib\gson-2.11.0.jar" -d out src\main\java\ApiResponse.java
pause
