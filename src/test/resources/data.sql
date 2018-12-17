--
-- TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
-- Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
--
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU General Public License as published by
-- the Free Software Foundation, either version 3 of the License.
--
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
-- GNU General Public License for more details.
--
-- You should have received a copy of the GNU General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

INSERT INTO `Organization` (`id`,`name`) VALUES
    (1,'Nuestra empresa');
INSERT INTO `Organization` (`id`,`name`) VALUES
    (2,'Indefinida');
INSERT INTO `Organization` (`id`,`name`) VALUES
    (3,'EMPRESA PRUEBA');
INSERT INTO `Organization` (`id`,`name`) VALUES
    (4,'Empresa Anónima');

INSERT INTO `Project` (`id`,`organizationId`,`open`,`name`) VALUES
    (1,1,1,'Vacaciones');
INSERT INTO `Project` (`id`,`organizationId`,`open`,`name`) VALUES
    (2,1,1,'Permiso extraordinario');
INSERT INTO `Project` (`id`,`organizationId`,`open`,`name`) VALUES
    (3,1,1,'Baja por enfermedad');
INSERT INTO `Project` (`id`,`organizationId`,`open`,`name`) VALUES
    (4,1,1,'Auto-formación');
INSERT INTO `Project` (`id`,`organizationId`,`open`,`name`) VALUES
    (5,1,1,'Histórico');
INSERT INTO `Project` (`id`,`organizationId`,`open`,`name`) VALUES
    (6,3,1,'Proyecto Facturable');
INSERT INTO `Project` (`id`,`organizationId`,`open`,`name`) VALUES
    (7,4,1,'Proyecto anónimo');

INSERT INTO `ProjectRole` (`id`,`projectId`,`name`) VALUES
    (1,1,'Vacaciones');
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`) VALUES
    (2,2,'Permiso extraordinario');
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`) VALUES
    (3,3,'Baja por enfermedad');
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`) VALUES
    (4,4,'Auto-formación');
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`) VALUES
    (5,5,'Histórico');
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`) VALUES
    (6,6,'RolFacturable');
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`) VALUES
    (7,7,'RolAnónimo');
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`) VALUES
    (8,6,'OtroRol');
INSERT INTO `ProjectRole` (`id`,`projectId`,`name`) VALUES
    (9,6,'RolDiferente');

INSERT INTO `User` (`id`,`login`,`password`) VALUES
    (1,'admin','dd94709528bb1c83d08f3088d4043f4742891f4f');
INSERT INTO `User` (`id`,`login`,`password`) VALUES
    (2,'iperez','8d31b31651adcdb499f386b1f70dccd91b6bc70c');
INSERT INTO `User` (`id`,`login`,`password`) VALUES
    (3,'alopez','da2eb4bde429a13f183ad2ca226227b474187453');
INSERT INTO `User` (`id`,`login`,`password`) VALUES
    (4,'portiz','836c2c739066f716f58db234b68f35d2b64069a1');
INSERT INTO `User` (`id`,`login`,`password`) VALUES
    (5,'jmuera','d6aa83e0a01f052d632bda5d47e32c53e0228ef2');
INSERT INTO `User` (`id`,`login`,`password`) VALUES
    (6,'aortiz','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');


INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (1,2,'2018-02-01 09:00:00',360,'Prueba',1,4);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (2,3,'2018-02-01 09:00:00',420,'Horas facturables',1,4);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (3,3,'2018-02-05 09:00:00',420,'Facturando....',1,6);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (4,3,'2018-01-31 09:00:00',240,'Estoy Facturando...',1,6);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (5,5,'2018-02-01 09:00:00',180,'.....',1,7);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (6,5,'2018-02-02 09:00:00',240,'$$$$$$',1,6);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (7,4,'2018-01-31 09:00:00',180,'.....',1,9);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (8,4,'2018-02-01 09:00:00',480,'Descripción',1,8);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (9,3,'2018-01-30 09:00:00',300,'NO FACTURABLE',0,4);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (10,1,'2018-02-01 09:00:00',60,'Descripciónasd',0,6);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (11,1,'2018-02-08 09:00:00',60,'Descripción',1,6);
INSERT INTO `Activity` (`id`,`userId`,`hiringDate`,`duration`,`description`,`billable`,`roleId`) VALUES
  (12,1,'2018-02-08 10:00:00',120,'Descripciónsfdg',1,6);

INSERT INTO `Holiday` (`id`,`description`,`date`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES (216,'San Isidro','2018-05-15 00:00:00',14,5,'2017-11-29 12:50:51','2017-11-29 12:50:51');
INSERT INTO `Holiday` (`id`,`description`,`date`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES (217,'Ascensión de la Virgen','2018-08-15 00:00:00',14,5,'2017-11-29 12:51:47','2017-11-29 12:51:47');
INSERT INTO `Holiday` (`id`,`description`,`date`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES (218,'Fiesta Nacional de España','2018-10-12 00:00:00',14,5,'2017-11-29 12:52:12','2017-11-29 12:52:33');
INSERT INTO `Holiday` (`id`,`description`,`date`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES (195,'Traslado San José','2017-03-20 00:00:00',14,5,'2016-11-02 13:56:51','2017-01-11 13:57:43');
INSERT INTO `Holiday` (`id`,`description`,`date`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES (196,'Jueves Santo','2017-04-13 00:00:00',14,5,'2016-11-02 13:57:08','2016-11-02 13:57:08');
INSERT INTO `Holiday` (`id`,`description`,`date`,`ownerId`,`departmentId`,`insertDate`,`updateDate`) VALUES (197,'Viernes Santo','2017-04-14 00:00:00',14,5,'2016-11-02 13:57:21','2016-11-02 13:57:21');