javac Program.java
echo $?
if [ $? -ne 0 ] then
    echo "Compilation error, exiting"
    exit 1
fi
java Program