@echo off
IF "%~1"=="" GOTO setDefault

@setlocal
:takeParamAsCommand
	SET toRun=%*
GOTO runDocker

:setDefault
	SET toRun="/bin/bash"
GOTO runDocker

:runDocker
set workDir=//%cd%
set workDir=%workDir:\=/%
set workDir=%workDir::=%
set m2Dir=//%USERPROFILE%/.m2
set m2Dir=%m2Dir:\=/%
set m2Dir=%m2Dir::=%
docker run -it --rm -v %workDir%:/usr/src/mymaven -v %m2Dir%:/root/.m2 -w /usr/src/mymaven -p 8080:8080 maven:alpine %toRun%
@endlocal