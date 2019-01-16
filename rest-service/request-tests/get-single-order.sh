if [ -z "$1" ]
then
    echo -e "\tGETTING ORDERS WITH ID=1 AND ID=2"
    echo ""
    curl -v localhost:8080/orders/1
    echo ""
    echo "--------------------------------------------------------------"
    echo "--------------------------------------------------------------"
    curl -v localhost:8080/orders/2
    echo ""

else
    echo -e "\tGETTING ORDER WITH ID=$1"
    echo ""
    curl -v localhost:8080/orders/$1
    echo""
fi
