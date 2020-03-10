USE `uber`;
SET SQL_SAFE_UPDATES = 0;

DELETE FROM `uber`.`Users`
WHERE `name` = 'Judith';

DELETE FROM `uber`.`Cars`
WHERE `model` = 'Corsa';

DELETE FROM `uber`.`Drivers`
WHERE `name` = 'Emma';