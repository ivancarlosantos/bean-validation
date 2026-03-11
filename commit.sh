#!/bin/bash
# Link medium: https://medium.com/@othamane.outame/step-by-step-publishing-java-libraries-to-maven-central

set -e

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

print_info() {
    echo -e "${BLUE}ℹ $1${NC}"
}

print_success() {
    echo -e "${GREEN}✓ $1${NC}"
}

print_info ">>> Enviando para o GitHub..."
git add .
git commit -m "feat: Atualização do projeto bean-validation"
git push origin master
sleep 5

print_info ">>> Enviando para o Maven Central Repository..."
mvn clean deploy -DskipTests
sleep 5

print_success ">>> Concluído com sucesso!"

exit 0