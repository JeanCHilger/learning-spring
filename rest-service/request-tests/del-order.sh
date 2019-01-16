if [ -z $1 ]
then
    echo -e "\tDELETING ORDER WITH ID=3"
    curl -v -X DELETE localhost:8080/orders/3/cancel
    echo ""

else
    echo -e "\tDELETING ORDER WITH ID=$1"
    curl -v -X DELETE localhost:8080/orders/$1/cancel
    echo ""
fi
