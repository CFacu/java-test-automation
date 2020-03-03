USE `uber`;

SELECT d.name AS driver_name, c.model, c.make, c.model_year, c.color FROM Cars c
LEFT JOIN Drivers d ON c.drivers_id = d.id
WHERE model_year > 2013;

SELECT d.name AS driver_name, c.model, c.model_year, c.color FROM Cars c
LEFT JOIN Drivers d ON c.drivers_id = d.id
WHERE make = 'Chevrolet' OR make = 'Peugeot';

SELECT count(name) AS users_with_rate, rate FROM Users
GROUP BY rate
HAVING rate > 3
ORDER BY rate;

SELECT count(model_year) AS number_of_cars, model_year  FROM Cars
GROUP BY model_year
HAVING model_year < 2019
ORDER BY model_year;