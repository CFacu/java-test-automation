USE `uber`;

ALTER TABLE `uber`.`Users`
ADD last_name varchar(255);

ALTER TABLE `uber`.`Drivers`
DROP COLUMN phone_number;

