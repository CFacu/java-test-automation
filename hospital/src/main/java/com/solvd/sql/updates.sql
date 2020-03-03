USE `uber`;
SET SQL_SAFE_UPDATES = 0;

UPDATE `uber`.`Drivers`
SET `name` = 'David'
WHERE `name` = 'Denis';

UPDATE `uber`.`Drivers`
SET `name` = 'Robert'
WHERE `name` = 'Joe';

UPDATE `uber`.`Drivers`
SET `name` = 'Giny'
WHERE `name` = 'Robert';

UPDATE `uber`.`Drivers`
SET `birth_date` = '1975-02-13'
WHERE `name` = 'Giny';