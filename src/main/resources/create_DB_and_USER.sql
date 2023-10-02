CREATE DATABASE cabmanager_db;
USE cabmanager_db;

CREATE USER 'devuser1'@'localhost' IDENTIFIED BY 'khz@BxDZ';
GRANT ALL PRIVILEGES ON cabmanager_db.* TO 'devuser1'@'localhost';
FLUSH PRIVILEGES;
