#!/bin/bash

# Cabeçalho
# ----------------------------------------------------------------------------
# Launcher do MusicOrganizer
#
# Autor: Frank Junior <frankcbjunior@gmail.com>
# Desde: 2014-06-16
# Versão: 1
# ----------------------------------------------------------------------------

# Configurações
# ----------------------------------------------------------------------------

# set:
# -e: se encontrar algum erro, termina a execução imediatamente
set -e

# Variáveis
# ----------------------------------------------------------------------------

# DEBUG = 0, desligado
# DEBUG = 1, ligado
DEBUG=0

# Funções
# ----------------------------------------------------------------------------

# funcao de debug
function debug(){
	[ "$DEBUG" = 1 ] && echo "[DEBUG] "
}

# Find a java installation.
function validacoes(){

	if [ -z "${JAVA_HOME}" ]; then
 		echo "Warning: $JAVA_HOME environment variable not set! Consider setting it."
 		echo "         Attempting to locate java..."
 		j=`which java 2> /dev/null`
	 	if [ -z "$j" ]; then
     		echo "Failed to locate the java virtual machine! Bailing..."
     		exit 1
 		else
			echo "Found a virtual machine at: $j..."
     		JAVA="$j"
	 	fi
	else
 		JAVA="${JAVA_HOME}/bin/java"
	fi

}

function launch(){

	cd $(dirname $0)

	$JAVA -jar $(dirname $0)/MusicOrganizer.jar

	cd $OLDPWD

}

# Main
# ----------------------------------------------------------------------------

validacoes
launch


