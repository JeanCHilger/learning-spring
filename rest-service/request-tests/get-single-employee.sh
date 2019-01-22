if [ -z "$1" ]
then
    echo -e "\tGETTING EMPLOYEES WITH ID=1 AND ID=2"
    echo ""
    curl -v localhost:8080/employees/1
    echo ""
    echo "--------------------------------------------------------------"
    echo "--------------------------------------------------------------"
    curl -v localhost:8080/employees/2
    echo ""

else
    echo -e "\tGETTING EMPLOYEE WITH ID=$1"
    echo ""
    curl -v localhost:8080/employees/$1
    echo""
fi
