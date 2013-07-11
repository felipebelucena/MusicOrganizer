MusicOrganizer
==============

## Uso
Programa feito em Java Desktop, para organizar as Metatags de arquivos de musica e organiza-las no diretorio de musica.
O programa carrega as músicas e suas tags, o usuário digita as tags corretamente na interface gráfica, junto com uma
imagem da capa do disco (artwork), que por sua vez, pode vir da internet (através de uma url),
ou de um arquivo .png ou .jpg.

Uma vez as tags digitadas corretamente, o programa faz as seguinte alterações:
- altera seus arquivos de .mp3 com as tags corretas. 
- Renomeia eles com a seguinte sintaxe: `<Numero-da-musica> - <Nome da música>.mp3`
- Cria uma arvore de diretório na seguinte sintaxe: `<Artista>/<Ano-do-album> - <album>`
- e coloca toda essa estrutura dentro de sua pasta oficial de música

Ficando como o exemplo a seguir:
`Gilberto Gil/1972 - Expresso 2222/01 - Pipoca Moderna.mp3`

Explicação mais detalhada sobre o Objetivo do Projeto, lá na página de [Wiki](https://github.com/frankjuniorr/MusicOrganizer/wiki/Objetivo-do-Projeto)

## Instalação (Para Desenvolvedores)
O projeto é feito no Eclipse, e eu versionei os arquivos próprios dele, logo, é só fazer um 
`git clone https://github.com/frankjuniorr/MusicOrganizer.git`
no seu worspace e abrir a pasta do projeto pelo Eclipse, =D

## Instalação (Para Usuários)
EM CONSTRUÇÃO

## Como contribuir?

é só dá um fork e um pull, pra quem tiver dúvida, aqui tem alguns link de ajuda:

- [Fork, Branch, Track, Squash and Pull Request](http://gun.io/blog/how-to-github-fork-branch-and-pull-request/)
- [Send pull requests](http://help.github.com/send-pull-requests/)

Pra quem precisa de ajuda com git, acesse esses links:
- [Managing Projects with GitHub](http://www.lullabot.com/blog/managing-projects-github)
- [Guia pra quem saca de SVN](https://git.wiki.kernel.org/articles/g/i/t/GitSvnCrashCourse_512d.html)
- [Git Reference](http://gitref.org) — Guia rápido, com exemplos
- [The Git Parable](http://tom.preston-werner.com/2009/05/19/the-git-parable.html) — Entenda a filosofia por trás do git
- [Help GitHub](https://help.github.com/) — A propria página de help do Github é muito boa


Todas as tarefas e implementações a serem feitas (como bug, melhoria e outras) 
estarão lá na parte de [Issues](https://github.com/frankjuniorr/MusicOrganizer/issues) do projeto.
