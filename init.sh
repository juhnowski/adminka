brew install postgresql
nano .bash_profile
export PATH="/usr/local/Cellar/postgresql/10.0/bin:$PATH"
initdb pg
pg_ctl -D pg -l logfile start
createdb adminka
lein repl
