SDK_HOME=$(echo `pwd` | sed 's/bin//')
MESSAGEPIDFILE=$SDK_HOME/data/sdk.pid

case $1 in
start)
    echo  "Starting sdk server... "

    HEAP_MEMORY=1024m
    PERM_MEMORY=128m
    JMX_PORT=8777
    JAVA_OPTS="-server -Djava.nio.channels.spi.SelectorProvider=sun.nio.ch.EPollSelectorProvider -XX:+HeapDumpOnOutOfMemoryError -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=218.244.138.233"
    
    shift
    ARGS=($*)
    for ((i=0; i<${#ARGS[@]}; i++)); do
        case "${ARGS[$i]}" in
        -D*)    JAVA_OPTS="${JAVA_OPTS} ${ARGS[$i]}" ;;
        -Heap*) HEAP_MEMORY="${ARGS[$i+1]}" ;;
        -Perm*) PERM_MEMORY="${ARGS[$i+1]}" ;;
        -JmxPort*)  JMX_PORT="${ARGS[$i+1]}" ;;
        esac
    done
    JAVA_OPTS="${JAVA_OPTS} -Xms${HEAP_MEMORY} -Xmx${HEAP_MEMORY} -XX:PermSize=${PERM_MEMORY} -XX:MaxPermSize=${PERM_MEMORY} -Dcom.sun.management.jmxremote.port=${JMX_PORT} -Dyunpay.home=${MESSAGE_HOME}"
    echo "start jvm args ${JAVA_OPTS}"
    nohup java -classpath .:./cm-1.0.0.jar:$CLASSPATH $JAVA_OPTS com.tianzh.cm.ConnectionServer &
    echo $! > $MESSAGEPIDFILE
    echo STARTED
    ;;

stop)
    echo "Stopping sdk server ... "
    if [ ! -f $MESSAGEPIDFILE ]
    then
        echo "error: count not find file $MESSAGEPIDFILE"
        exit 1
    else
        kill -15 $(cat $MESSAGEPIDFILE)
        rm $MESSAGEPIDFILE
        echo STOPPED
    fi
    ;;

*)
    echo "Exec ... "
    ;;
   
esac

exit 0
