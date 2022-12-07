@echo off
javac -classpath .;postgresql-42.5.0.jar;quizlib Driver.java
java -classpath .;postgresql-42.5.0.jar;quizlib Driver
