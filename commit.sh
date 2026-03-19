#!/bin/bash

set -e

sleep 5

echo "========================================"
echo " [1/5] Gerando Fat JAR da aplicação..."
echo "========================================"
mvn clean install

sleep 5

echo "========================================"
echo " [2/5] Análise de cobertura por mutação..."
echo "========================================"
mvn test-compile org.pitest:pitest-maven:mutationCoverage

sleep 5

echo "========================================"
echo " [3/5] Análise de cobertura JaCoCo..."
echo "========================================"
mvn test

sleep 5

echo "========================================"
echo " [4/5] Enviando para o GitHub..."
echo "========================================"
git add .
sleep 2
git commit -m "Atualização do projeto bean-validation"
sleep 2
git push origin master

sleep 5

echo "========================================"
echo " [5/5] Publicando no Maven Central..."
echo "========================================"
mvn clean deploy

sleep 5

echo "========================================"
echo " Todas as etapas concluídas com sucesso!"
echo "========================================"

exit 0