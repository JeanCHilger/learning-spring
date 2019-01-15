echo "\tPOSTING NEW RANDOM EMPLOYEE"

first_names="Tommy Roland Raelynn Ibrahim Levi Alberto Ayanna Koen Thomas Kiersten Andy Lillianna Jett Korbin Lesly"
f_names_array=($first_names)
f_n_sel=$(($$%15)) # random number from 0 to 14
first_name=${f_names_array[f_n_sel]}

last_names="Haas Mcdaniel Davis Maxwell Frey Reeves Davenport Duran Gibbs Alvarez Stephens Durham Bean Kelley Owen"
l_names_array=($last_names)
l_n_sel=$(($$%15)) # random number from 0 to 14
last_name=${l_names_array[l_n_sel]}

roles="Reporter Mason Lawyer Telemarketer Musician Historian Burglar Thief Psychologist Coach Scientist Writer Judge Physicist Slave"
roles_array=($roles)
r_sel=$(($$%15)) # random number from 0 to 14
role=${roles_array[r_sel]}

diff=$((101-15)) # defines the range of the age
age=$(($(($$%$diff))+15)) # random number from 15 to 100

curl -v -X POST localhost:8080/employees -H 'Content-type:application/json' -d '{"firstName":"'$first_name'","lastName":"'$last_name'","role":"'$role'", "age":"'$age'"}'
echo ""
