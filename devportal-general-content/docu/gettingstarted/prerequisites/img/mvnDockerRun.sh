docker run -it --rm -v "$PWD":/usr/src/mymaven -v "$HOME/.m2:/root/.m2" -w /usr/src/mymaven -p 8080:8080 maven:alpine "${@:-/bin/bash}"
