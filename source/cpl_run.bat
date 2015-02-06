@echo off
cd src
javac -d ../class app/main/Application.java
cd..
cd class
java app.main.Application
cd..
