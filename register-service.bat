@echo off

if "%1"=="build" (
    echo Building Java files...
    for /R adapter %%f in (*.java) do javac -cp engine.jar "%%f"
    goto :eof
)

if "%1"=="clean" (
    echo Cleaning class files...
    for /R adapter %%f in (*.class) do del /f "%%f"
    goto :eof
)