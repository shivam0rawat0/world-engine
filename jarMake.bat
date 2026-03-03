@echo off
REM Clean previous JAR
if exist engine.jar del engine.jar

REM Compile all Java files into out/ folder
if exist out rmdir /s /q out
mkdir out

REM Compile Java source files recursively
javac -d out decisionengine\*.java decisionengine\service\*.java decisionengine\util\*.java

REM Create META-INF folder for manifest
mkdir out\META-INF
copy manifest.txt out\META-INF\MANIFEST.MF

REM Package into engine.jar
jar cfm engine.jar out\META-INF/MANIFEST.MF -C out .

REM Optional cleanup
rmdir /s /q out
REM ::del /f *.class

echo Build complete.
pause
