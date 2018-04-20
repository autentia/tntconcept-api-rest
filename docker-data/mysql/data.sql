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

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `tntconcept` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;

-- -----------------------------------------------------------------------------
-- Organization
-- -----------------------------------------------------------------------------

-- Create table
create table `Organization` (
  `id` int not null auto_increment,
  `name` varchar(128) not null,
  `cif` varchar(50),
  `phone` varchar(15),
  `street` varchar(256),
  `number` varchar(16) comment 'Building number in street',
  `locator` varchar(256) comment 'Location information inside building',
  `postalCode` varchar(32),
  `city` varchar(256),
  `provinceId` int not null,
  `state` varchar(256),
  `country` varchar(256),
  `fax` varchar(16),
  `email` varchar(256),
  `website` varchar(256),
  `ftpsite` varchar(256),
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='los clientes';

-- Insert default own company
insert into Organization(id,name,provinceId) values ( 1, 'Nuestra empresa', 28 );

  -- -----------------------------------------------------------------------------
  -- Project
  -- -----------------------------------------------------------------------------

  -- Create table
  create table `Project` (
    `id` int not null auto_increment,
    `organizationId` int not null,
    `startDate` date not null,
    `endDate` date,
    `name` varchar(128) not null,
    `description` varchar(2048),
    primary key  (`id`),
    index `ndx_project_organizationId` (`organizationId`),
    constraint `fk_project_organizationId` foreign key (`organizationId`) references `Organization` (`id`)
  ) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Projects';

  -- Insert generic projects
  insert into Project(id,organizationId,startDate,name) values (1, 1, CURDATE(),'Vacaciones');
  insert into Project(id,organizationId,startDate,name) values (2, 1, CURDATE(),'Permiso extraordinario');
  insert into Project(id,organizationId,startDate,name) values (3, 1, CURDATE(),'Baja por enfermedad');
  insert into Project(id,organizationId,startDate,name) values (4, 1, CURDATE(),'Auto-formación');

    -- Create table
    create table `User` (
      `id` int not null auto_increment,
      -- Datos de la aplicacion
      `login` varchar(50) not null,
      `password` varchar(50) not null,
      primary key (`id`)
    ) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Users de la aplicacin';

    -- Create administrator user (login=admin, password=adminadmin)
    INSERT INTO `User` (`id`,`login`,`password`) VALUES
        (1,'admin','dd94709528bb1c83d08f3088d4043f4742891f4f');

        -- -----------------------------------------------------------------------------
        -- Activity
        -- -----------------------------------------------------------------------------

        -- Create table
        create table `Activity` (
          `id` int not null auto_increment,
          `projectId` int not null,
          `userId` int not null,
          `startDate` date not null,
          `duration` int not null comment 'Duración en minutos',
          `description` varchar(2048),
          primary key (`id`),
          index `ndx_activity_userId` (`userId`),
          index `ndx_activity_projectId` (`projectId`),
          constraint `fk_activity_userId` foreign key (`userId`) references `User` (`id`),
          constraint `fk_activity_projectId` foreign key (`projectId`) references `Project` (`id`)
        ) engine=innodb default charset=utf8 collate=utf8_spanish_ci comment='Activityes de los Users';        
