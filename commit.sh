#!/bin/bash

set -e

echo "========================================"
echo " [1/4] Gerando Fat JAR da aplicação..."
echo "========================================"
mvn clean install

sleep 5

echo "========================================"
echo " [2/4] Análise de cobertura por mutação..."
echo "========================================"
mvn test-compile org.pitest:pitest-maven:mutationCoverage

sleep 5

echo "========================================"
echo " [3/4] Enviando para o GitHub..."
echo "========================================"
git add .
sleep 2
git commit -m "Atualização do projeto bean-validation"
sleep 2
git push origin master

sleep 5

echo "========================================"
echo " [4/4] Publicando no Maven Central..."
echo "========================================"
mvn clean deploy

sleep 5

echo "========================================"
echo " Todas as etapas concluídas com sucesso!"
echo "========================================"

exit 0