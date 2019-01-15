if [ -z $1 ]
then
    echo -e "\tDELETING EMPLOYEE WITH ID=3"
    curl -v -X DELETE localhost:8080/employees/3
    echo ""

else
    echo -e "\tDELETING EMPLOYEE WITH ID=$1"
    curl -v -X DELETE localhost:8080/employees/$1
    echo ""
fi
