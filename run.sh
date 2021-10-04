#!/bin/sh

mvn -B clean package
java -jar target/calculator.jar