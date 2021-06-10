
-- SQL TASK 1 var.1

select name, surname
from users
where
      (Select extract(year FROM (age(birth_date))))
between 18 and 24;

-- SQL TASK 1 var.2

select *
from users
where
    (Select extract(year FROM (age(birth_date))))
between 18 and 24;


-- SQL TASK 2 var.1

WITH user_car_count AS
    (SELECT owner AS id, count(owner) AS cars_count
    FROM cars
    GROUP BY owner)

SELECT concat(users.name, ' ', users.surname) AS User_full_name,
       COALESCE(user_car_count.cars_count, 0) AS count
FROM users LEFT JOIN user_car_count
ON users.id = user_car_count.id;

-- SQL TASK 2 var.2

WITH user_car_count AS
    (SELECT owner AS id, count(owner) AS cars_count
     FROM cars
     GROUP BY owner)

SELECT concat(users.name, ' ', users.surname, ' | ',
    COALESCE(user_car_count.cars_count, 0)) AS result
FROM users LEFT JOIN user_car_count
ON users.id = user_car_count.id;

-- SQL TASK 3

WITH model_group_table AS (SELECT dealer_id AS id,
                    concat(name, ' ', model) AS car_model,
                    row_number() OVER (PARTITION BY dealer_id
                       ORDER BY count(dealer_id) desc) AS row
             FROM cars
             GROUP BY name, model, dealer_id)


SELECT dealer.name, model_group_table.car_model
FROM model_group_table JOIN dealer
ON model_group_table.id = dealer.id
WHERE model_group_table.row <= 3
ORDER BY model_group_table.id, model_group_table.row;


-- SQL TASK 4

SELECT login
FROM users
WHERE id IN
	(SELECT owner AS id
	FROM cars
	GROUP BY owner
	HAVING count(owner) > 3);


-- SQL TASK 5

WITH total_amount_dealer AS
	(SELECT dealer_id AS deler, sum(price) AS total_amount
	FROM cars
	GROUP BY dealer_id)

SELECT  dealer.name, total_amount_dealer.total_amount
FROM dealer JOIN total_amount_dealer
ON dealer.id = total_amount_dealer.deler;


-- SQL TASK 6

WITH owner_table AS
    (SELECT DISTINCT owner
    FROM cars
    WHERE price > (SELECT avg(price)
               FROM cars))

SELECT count(owner)
FROM owner_table;