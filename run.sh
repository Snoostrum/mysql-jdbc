#! /bin/bash
set -e

javac Db.java NoteApp.java
java -cp "lib/mysql-connector-j-9.4.0.jar:." NoteApp