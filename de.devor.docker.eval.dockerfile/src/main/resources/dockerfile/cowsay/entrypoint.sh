#!/bin/bash
## check if we have anm argunent from the command line.
if [ $# -eq 0 ]; then
	## no -> start cowsay with fhe output from fortune
	/usr/games/fortune | /usr/games/cowsay
else
	## yes -> use the argument
	/usr/games/cowsay "$@"
fi