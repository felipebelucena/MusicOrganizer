#!/bin/bash

# Cabeçalho
# ----------------------------------------------------------------------------
# TODO: Prototipo de Instalador do MusicOrganizer
#
# Uso: ./install.sh
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
JAR="MusicOrganizer-0.0.1-SNAPSHOT.jar"
LAUNCH="MusicOrganizer.sh"

# Funções
# ----------------------------------------------------------------------------

# funcao de debug
function debug(){
	[ "$DEBUG" = 1 ] && echo "[DEBUG] "
}

function validacoes(){
	# verifica se o script foi rodado com sudo
	if [ ! $(id -u) -eq 0 ];then
		PATH_DESTINO="$HOME/MusicOrganizer"
		PATH_BIN="$HOME/bin"
	else
		PATH_DESTINO="/usr/local/share/MusicOrganizer"
		PATH_BIN="/usr/bin"
	fi

}

# verificando se o path já existe
function verifica_path(){

	path="$1"
	if [ ! -d $path ]; then
		mkdir -p $path
	else
		rm -rf $path/*
	fi

}

function create_shortcut(){

echo "[Desktop Entry]
Exec="$PATH_BIN"/MusicOrganizer
Name=MusicOrganizer
Icon="$PATH_DESTINO"/ic_launcher.jpg
Type=Application
Terminal=false
StartupNotify=true
MimeType=text/plain;
" > MusicOrganizer.desktop
}

function install(){

	verifica_path "$PATH_DESTINO/"
	verifica_path "$PATH_BIN/"
	cp "$(dirname $0)/../target/$JAR" "$PATH_DESTINO/"
	cp "$(dirname $0)/../src/main/resources/images/imagem-padrao.jpg" "$PATH_DESTINO/"
	mv "$PATH_DESTINO/imagem-padrao.jpg" "$PATH_DESTINO/ic_launcher.jpg"
	mv "$PATH_DESTINO/$JAR" "$PATH_DESTINO/MusicOrganizer.jar"
	ln -s "$PATH_DESTINO/MusicOrganizer.jar" "$PATH_BIN/MusicOrganizer.jar"
	cp "$LAUNCH" "$PATH_BIN"
	mv "$PATH_BIN/$LAUNCH" "$PATH_BIN/MusicOrganizer"
	chmod 777 "$PATH_BIN/MusicOrganizer"

}

# Main
# ----------------------------------------------------------------------------

validacoes
install
create_shortcut


