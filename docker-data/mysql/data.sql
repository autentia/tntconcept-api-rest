CREATE DATABASE tntconcept;
USE tntconcept;
# ************************************************************
# Sequel Pro SQL dump
# Versión 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.39)
# Base de datos: tntconcept
# Tiempo de Generación: 2018-04-20 16:40:19 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Volcado de tabla Account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Account`;

CREATE TABLE `Account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account descriptive name',
  `number` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account number',
  `accountTypeId` int(11) NOT NULL COMMENT 'Account type',
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Account description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_account_accountTypeId` (`accountTypeId`),
  CONSTRAINT `fk_account_accountTypeId` FOREIGN KEY (`accountTypeId`) REFERENCES `AccountType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='almacenan las cuentas';

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;

INSERT INTO `Account` (`id`, `name`, `number`, `accountTypeId`, `description`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Caja','0000000000000000000',1,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla AccountEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AccountEntry`;

CREATE TABLE `AccountEntry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT 'account where the entry is charged',
  `accountEntryTypeId` int(11) NOT NULL COMMENT 'Account entry type',
  `entryDate` date NOT NULL COMMENT 'account entry date',
  `entryAmountDate` date NOT NULL COMMENT 'account entry amount date (fecha valor)',
  `concept` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `amount` decimal(10,2) NOT NULL COMMENT 'account entry amount',
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `entryNumber` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `docNumber` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_accountEntry_accountId` (`accountId`),
  KEY `ndx_accountEntry_accountEntryTypeId` (`accountEntryTypeId`),
  CONSTRAINT `fk_accountEntry_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
  CONSTRAINT `fk_accountEntry_accountId` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='los movimientos';



# Volcado de tabla AccountEntryGroup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AccountEntryGroup`;

CREATE TABLE `AccountEntryGroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account entry group descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='grupos de movimiento';

LOCK TABLES `AccountEntryGroup` WRITE;
/*!40000 ALTER TABLE `AccountEntryGroup` DISABLE KEYS */;

INSERT INTO `AccountEntryGroup` (`id`, `name`, `description`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Ingreso','Ingresos en cuenta',NULL,NULL,NULL,NULL),
	(2,'Gasto','Gastos en cuenta',NULL,NULL,NULL,NULL),
	(3,'Traspaso','Traspasos',NULL,NULL,NULL,NULL),
	(4,'Arranque anual','Movimiento que representa al arranque anual',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `AccountEntryGroup` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla AccountEntryType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AccountEntryType`;

CREATE TABLE `AccountEntryType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountEntryGroupId` int(11) NOT NULL COMMENT 'Account entry group',
  `name` varchar(256) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account descriptive name',
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `accountEntryTypeId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `customizableId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_accountEntryType_accountEntryGroupId` (`accountEntryGroupId`),
  KEY `FK_accountentrytype_accountEntryTypeId` (`accountEntryTypeId`),
  CONSTRAINT `FK_accountentrytype_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
  CONSTRAINT `fk_accountEntryType_accountEntryGroupId` FOREIGN KEY (`accountEntryGroupId`) REFERENCES `AccountEntryGroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de movimientos';

LOCK TABLES `AccountEntryType` WRITE;
/*!40000 ALTER TABLE `AccountEntryType` DISABLE KEYS */;

INSERT INTO `AccountEntryType` (`id`, `accountEntryGroupId`, `name`, `observations`, `accountEntryTypeId`, `ownerId`, `departmentId`, `insertDate`, `updateDate`, `customizableId`)
VALUES
	(1,4,'Arranque inicial','Tipo de asiento que representa el arranque inicial de un año',NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `AccountEntryType` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla AccountType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AccountType`;

CREATE TABLE `AccountType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Account type descriptive name',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='lmacenan las cuentas';

LOCK TABLES `AccountType` WRITE;
/*!40000 ALTER TABLE `AccountType` DISABLE KEYS */;

INSERT INTO `AccountType` (`id`, `name`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Caja',NULL,NULL,NULL,NULL),
	(2,'Cuenta corriente',NULL,NULL,NULL,NULL),
	(3,'Cuenta de crédito',NULL,NULL,NULL,NULL),
	(4,'Depósito',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `AccountType` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Activity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Activity`;

CREATE TABLE `Activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `startDate` datetime DEFAULT '2000-01-01 00:00:00',
  `duration` int(11) NOT NULL COMMENT 'Duración en minutos',
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  `roleId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_activity_userId` (`userId`),
  KEY `fk_activity_roleId` (`roleId`),
  CONSTRAINT `fk_activity_roleId` FOREIGN KEY (`roleId`) REFERENCES `ProjectRole` (`id`),
  CONSTRAINT `fk_activity_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Activityes de los Users';

LOCK TABLES `Activity` WRITE;
/*!40000 ALTER TABLE `Activity` DISABLE KEYS */;

INSERT INTO `Activity` (`id`, `userId`, `startDate`, `duration`, `description`, `billable`, `roleId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,1,'2018-04-18 09:00:00',60,'Actividad de prueba 2',0,4,1,'2018-04-20 18:37:20','2018-04-20 18:37:44'),
	(2,1,'2018-04-17 09:00:00',60,'Actividad de prueba ',1,3,1,'2018-04-20 18:37:40','2018-04-20 18:37:57');

/*!40000 ALTER TABLE `Activity` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla ActivityFile
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ActivityFile`;

CREATE TABLE `ActivityFile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalActivityId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `insertDate` datetime NOT NULL,
  `updateDate` datetime DEFAULT NULL,
  `file` varchar(400) COLLATE utf8_spanish_ci NOT NULL,
  `fileMime` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_activityFile_externalActivity` (`externalActivityId`),
  KEY `fk_activityFile_user` (`userId`),
  CONSTRAINT `fk_activityFile_externalActivity` FOREIGN KEY (`externalActivityId`) REFERENCES `ExternalActivity` (`id`),
  CONSTRAINT `fk_activityFile_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Bill
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Bill`;

CREATE TABLE `Bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creationDate` date NOT NULL,
  `paymentMode` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `number` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(4096) COLLATE utf8_spanish_ci NOT NULL,
  `file` varchar(512) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fileMime` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `observations` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `projectId` int(11) NOT NULL DEFAULT '5' COMMENT 'project id',
  `startBillDate` date NOT NULL DEFAULT '1980-01-01',
  `endBillDate` date NOT NULL DEFAULT '1980-01-01',
  `billType` varchar(16) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'ISSUED',
  `orderNumber` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `contactId` int(11) DEFAULT NULL,
  `providerId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `bookNumber` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `accountId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_bill_projectId` (`projectId`),
  KEY `ndx_bill_contactId` (`contactId`),
  KEY `ndx_bill_providerId` (`providerId`),
  KEY `fk_bill_accountId` (`accountId`),
  CONSTRAINT `Bill_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`),
  CONSTRAINT `fk_bill_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_bill_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_bill_providerId` FOREIGN KEY (`providerId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Bill_AccountEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Bill_AccountEntry`;

CREATE TABLE `Bill_AccountEntry` (
  `billId` int(11) NOT NULL COMMENT 'bill id',
  `accountEntryId` int(11) NOT NULL COMMENT 'account entry id',
  `observations` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`billId`,`accountEntryId`),
  KEY `ndx_bill_AccountEntry_billId` (`billId`),
  KEY `ndx_bill_AccountEntry_accountEntryId` (`accountEntryId`),
  CONSTRAINT `fk_billAccountEntry_accountEntryId` FOREIGN KEY (`accountEntryId`) REFERENCES `AccountEntry` (`id`),
  CONSTRAINT `fk_billAccountEntry_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='relaciona n m Facturas y movimientos';



# Volcado de tabla BillBreakDown
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BillBreakDown`;

CREATE TABLE `BillBreakDown` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `billId` int(11) NOT NULL,
  `concept` varchar(4096) COLLATE utf8_spanish_ci NOT NULL,
  `units` decimal(10,2) NOT NULL DEFAULT '1.00',
  `amount` decimal(10,2) NOT NULL,
  `iva` decimal(4,2) NOT NULL DEFAULT '16.00',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `place` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_billBreakDown_bill` (`billId`),
  CONSTRAINT `fk_billBreakDown_bill` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla BillPayment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BillPayment`;

CREATE TABLE `BillPayment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billId` int(11) NOT NULL,
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `expirationDate` date NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_bill_billPayment` (`billId`),
  CONSTRAINT `fk_bill_billPayment` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Book`;

CREATE TABLE `Book` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `author` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ISBN` varchar(13) COLLATE utf8_spanish_ci DEFAULT NULL,
  `URL` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `purchaseDate` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Book_userId` (`userId`),
  CONSTRAINT `FK_Book_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla BulletinBoard
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BulletinBoard`;

CREATE TABLE `BulletinBoard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `message` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `documentPath` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `documentContentType` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_bulletinboard_categoryId` (`categoryId`),
  KEY `ndx_bulletinboard_userId` (`userId`),
  CONSTRAINT `fk_bulletinboard_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `BulletinBoardCategory` (`id`),
  CONSTRAINT `fk_bulletinboard_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='mensajes del tabln';



# Volcado de tabla BulletinBoardCategory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BulletinBoardCategory`;

CREATE TABLE `BulletinBoardCategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='almacenan las categoras';

LOCK TABLES `BulletinBoardCategory` WRITE;
/*!40000 ALTER TABLE `BulletinBoardCategory` DISABLE KEYS */;

INSERT INTO `BulletinBoardCategory` (`id`, `name`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Pública',NULL,NULL,NULL,NULL),
	(2,'General',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `BulletinBoardCategory` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Collaborator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Collaborator`;

CREATE TABLE `Collaborator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(10) DEFAULT NULL,
  `contactId` int(10) DEFAULT NULL,
  `organizationId` int(10) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_collaborator_user` (`userId`),
  KEY `fk_collaborator_contact` (`contactId`),
  KEY `fk_collaborator_organization` (`organizationId`),
  CONSTRAINT `fk_collaborator_contact` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_collaborator_organization` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_collaborator_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Commissioning
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Commissioning`;

CREATE TABLE `Commissioning` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `requestDate` datetime NOT NULL,
  `name` varchar(512) COLLATE utf8_spanish_ci NOT NULL,
  `scope` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `content` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `products` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `deliveryDate` datetime NOT NULL,
  `budget` decimal(10,2) DEFAULT NULL,
  `notes` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `authorSignature` tinyint(1) NOT NULL DEFAULT '0',
  `reviserSignature` tinyint(1) NOT NULL DEFAULT '0',
  `adminSignature` tinyint(1) NOT NULL DEFAULT '0',
  `justifyInformation` tinyint(1) NOT NULL DEFAULT '0',
  `developedActivities` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `difficultiesAppeared` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `results` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `conclusions` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `evaluation` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `projectId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioning_project` (`projectId`),
  CONSTRAINT `fk_commissioning_project` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Commissioning_User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Commissioning_User`;

CREATE TABLE `Commissioning_User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commissioningId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioning_user_commissioning` (`commissioningId`),
  KEY `fk_commissioning_user_user` (`userId`),
  CONSTRAINT `fk_commissioning_user_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `Commissioning` (`id`),
  CONSTRAINT `fk_commissioning_user_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla CommissioningChange
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CommissioningChange`;

CREATE TABLE `CommissioningChange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `oldValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `newValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `commissioningId` int(10) DEFAULT NULL,
  `userId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioningChange_commissioning` (`commissioningId`),
  KEY `fk_commissioningChange_user` (`userId`),
  CONSTRAINT `fk_commissioningChange_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `Commissioning` (`id`),
  CONSTRAINT `fk_commissioningChange_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla CommissioningDelay
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CommissioningDelay`;

CREATE TABLE `CommissioningDelay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `originalDate` datetime NOT NULL,
  `delayedToDate` datetime NOT NULL,
  `commissioningId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioningDelay_commissioning` (`commissioningId`),
  CONSTRAINT `fk_commissioningDelay_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `Commissioning` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla CommissioningFile
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CommissioningFile`;

CREATE TABLE `CommissioningFile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commissioningId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  `insertDate` datetime NOT NULL,
  `updateDate` datetime DEFAULT NULL,
  `file` varchar(400) COLLATE utf8_spanish_ci NOT NULL,
  `fileMime` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioningFile_commissioning` (`commissioningId`),
  KEY `fk_commissioningFile_user` (`userId`),
  CONSTRAINT `fk_commissioningFile_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `Commissioning` (`id`),
  CONSTRAINT `fk_commissioningFile_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla CommissioningPaymentData
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CommissioningPaymentData`;

CREATE TABLE `CommissioningPaymentData` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commissioningId` int(10) NOT NULL,
  `collaboratorId` int(11) NOT NULL,
  `paymentMode` varchar(32) COLLATE utf8_spanish_ci DEFAULT NULL,
  `bankAccount` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `billNumber` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commissioning_collaborator_commissioning` (`commissioningId`),
  KEY `fk_commissioning_collaborator_collaborator` (`collaboratorId`),
  CONSTRAINT `fk_commissioning_collaborator_collaborator` FOREIGN KEY (`collaboratorId`) REFERENCES `Collaborator` (`id`),
  CONSTRAINT `fk_commissioning_collaborator_commissioning` FOREIGN KEY (`commissioningId`) REFERENCES `Commissioning` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla CompanyState
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CompanyState`;

CREATE TABLE `CompanyState` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT 'aplicacin que manda el mail',
  `creationDate` datetime NOT NULL,
  `description` longtext COLLATE utf8_spanish_ci NOT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_companystate_userId` (`userId`),
  CONSTRAINT `fk_companystate_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='comentario del director de empresa';



# Volcado de tabla Contact
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Contact`;

CREATE TABLE `Contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `mobile` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `notified` tinyint(1) NOT NULL DEFAULT '0',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `email2` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone2` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `postalCode` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provinceId` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `ndx_contact_provinceId` (`provinceId`),
  CONSTRAINT `fk_contact_province` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='contactos de las Organizationes';



# Volcado de tabla Contact_Tag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Contact_Tag`;

CREATE TABLE `Contact_Tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) NOT NULL,
  `contactId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contact_tag_tag` (`tagId`),
  KEY `fk_contact_tag_contact` (`contactId`),
  CONSTRAINT `fk_contact_tag_contact` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_contact_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `Tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla ContactInfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ContactInfo`;

CREATE TABLE `ContactInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contactId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  `departmentId` int(10) unsigned NOT NULL,
  `organizationId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contactinfo_contact` (`contactId`),
  KEY `fk_contactinfo_position` (`positionId`),
  KEY `fk_contactinfo_department` (`departmentId`),
  KEY `fk_contactinfo_organization` (`organizationId`),
  CONSTRAINT `fk_contactinfo_contact` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_contactinfo_department` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_contactinfo_organization` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_contactinfo_position` FOREIGN KEY (`positionId`) REFERENCES `Position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla ContactOwner
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ContactOwner`;

CREATE TABLE `ContactOwner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contactId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contactowner_contactId` (`contactId`),
  KEY `fk_contactowner_userId` (`userId`),
  CONSTRAINT `fk_contactowner_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_contactowner_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Propietarios de contactos';



# Volcado de tabla ContractType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ContractType`;

CREATE TABLE `ContractType` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

LOCK TABLES `ContractType` WRITE;
/*!40000 ALTER TABLE `ContractType` DISABLE KEYS */;

INSERT INTO `ContractType` (`id`, `name`, `description`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Prácticas','Departamento de dirección.',NULL,NULL,NULL,NULL),
	(2,'Duración determinada','Departamento de dirección.',NULL,NULL,NULL,NULL),
	(3,'Indefinido','Departamento de dirección.',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `ContractType` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla CreditTitle
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CreditTitle`;

CREATE TABLE `CreditTitle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `concept` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `type` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `issueDate` date NOT NULL,
  `expirationDate` date DEFAULT NULL,
  `organizationId` int(11) NOT NULL,
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_credittitle_organizationId` (`organizationId`),
  CONSTRAINT `fk_credittitle_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla CreditTitle_Bill
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CreditTitle_Bill`;

CREATE TABLE `CreditTitle_Bill` (
  `billId` int(11) NOT NULL,
  `creditTitleId` int(11) NOT NULL,
  PRIMARY KEY (`billId`,`creditTitleId`),
  KEY `ndx_creditTitle_Bill_billId` (`billId`),
  KEY `ndx_creditTitle_Bill_creditTitleId` (`creditTitleId`),
  CONSTRAINT `fk_creditTitle_Bill_billId` FOREIGN KEY (`billId`) REFERENCES `Bill` (`id`),
  CONSTRAINT `fk_creditTitle_Bill_creditTitleId` FOREIGN KEY (`creditTitleId`) REFERENCES `CreditTitle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Department
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Department`;

CREATE TABLE `Department` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parentId` int(10) unsigned DEFAULT NULL,
  `name` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_department_department` (`parentId`),
  CONSTRAINT `fk_department_department` FOREIGN KEY (`parentId`) REFERENCES `Department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;

INSERT INTO `Department` (`id`, `parentId`, `name`, `description`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,NULL,'Dirección','Departamento de dirección.',NULL,NULL,NULL,NULL),
	(2,1,'I+D+I','Departamento de I+D+I.',NULL,NULL,NULL,NULL),
	(3,1,'Consultoría','Departamento de Consultoría.',NULL,NULL,NULL,NULL),
	(4,NULL,'Indefinido','Departamento sin definir',1,1,'2018-04-20 15:37:14','2018-04-20 15:37:14');

/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Department_Organization
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Department_Organization`;

CREATE TABLE `Department_Organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentId` int(10) unsigned NOT NULL,
  `organizationId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_department_organization_department` (`departmentId`),
  KEY `fk_department_organization_organization` (`organizationId`),
  CONSTRAINT `fk_department_organization_department` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_department_organization_organization` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

LOCK TABLES `Department_Organization` WRITE;
/*!40000 ALTER TABLE `Department_Organization` DISABLE KEYS */;

INSERT INTO `Department_Organization` (`id`, `departmentId`, `organizationId`)
VALUES
	(1,4,2),
	(2,4,1);

/*!40000 ALTER TABLE `Department_Organization` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Department_Tag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Department_Tag`;

CREATE TABLE `Department_Tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) NOT NULL,
  `departmentId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_department_tag_tag` (`tagId`),
  KEY `fk_department_tag_department` (`departmentId`),
  CONSTRAINT `fk_department_tag_department` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_department_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `Tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Document
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Document`;

CREATE TABLE `Document` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `name` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `description` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla DocumentCategory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DocumentCategory`;

CREATE TABLE `DocumentCategory` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `code` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `categoryid` int(10) unsigned DEFAULT NULL,
  `documentslastupdate` datetime DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

LOCK TABLES `DocumentCategory` WRITE;
/*!40000 ALTER TABLE `DocumentCategory` DISABLE KEYS */;

INSERT INTO `DocumentCategory` (`id`, `name`, `description`, `code`, `categoryid`, `documentslastupdate`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Documentos de Calidad','Documentos de calidad','CALIDAD',NULL,NULL,NULL,NULL,NULL,NULL),
	(2,'Otros Documentos','Otros documentos no clasificados',' ',NULL,NULL,NULL,NULL,NULL,NULL),
	(3,'Curriculum Vitae','','',NULL,NULL,NULL,NULL,NULL,NULL),
	(4,'Documentos de Usuarios','','',NULL,NULL,NULL,NULL,NULL,NULL),
	(10,'(I1-PC01) Lista de distribución de la documentación','','I1-PC01',1,NULL,NULL,NULL,NULL,NULL),
	(11,'(I1-PC02) Acta de reunión de revisión del sistema','','I1-PC02',1,NULL,NULL,NULL,NULL,NULL),
	(12,'(I1-PC08) Informe de auditoría interna','','I1-PC08',1,NULL,NULL,NULL,NULL,NULL),
	(13,'(I2-PC02) Planificación de objetivos de calidad','','I2-PC02',1,NULL,NULL,NULL,NULL,NULL),
	(14,'(I2-PC08) Informe de no conformidad/reclamación del cliente','','I2-PC08',1,NULL,NULL,NULL,NULL,NULL),
	(15,'(I3-PC08) Informe de acción correctiva/preventiva','','I3-PC08',1,NULL,NULL,NULL,NULL,NULL),
	(16,'(PC01) Sistema de Gestión de la Calidad','Documento inicial de descripción','PC01',1,NULL,NULL,NULL,NULL,NULL),
	(17,'(PC02) Responsabilidad de la Dirección','','PC02',1,NULL,NULL,NULL,NULL,NULL),
	(18,'(PC03) Gestión de los Recursos','','PC03',1,NULL,NULL,NULL,NULL,NULL),
	(19,'(PC04) Procesos relacionados con los clientes','','PC04',1,NULL,NULL,NULL,NULL,NULL),
	(20,'(PC05) Gestión de compras','','PC05',1,NULL,NULL,NULL,NULL,NULL),
	(21,'(PC06) Evaluación de proveedores y subcontratistas','','PC06',1,NULL,NULL,NULL,NULL,NULL),
	(22,'(PC07) Prestación del servicio','','PC07',1,NULL,NULL,NULL,NULL,NULL),
	(23,'(PC08) Medición análisis y mejora','','PC08',1,NULL,NULL,NULL,NULL,NULL),
	(24,'Control documentación entregada y recibida','','',1,NULL,NULL,NULL,NULL,NULL),
	(25,'Criterio de evaluación y seguimiento de procesos','','',1,NULL,NULL,NULL,NULL,NULL),
	(26,'Cuestionario de satisfacción del cliente','','',1,NULL,NULL,NULL,NULL,NULL),
	(27,'E-mail aprobación documentación','','',1,NULL,NULL,NULL,NULL,NULL),
	(28,'E-mail de comunicaciones','','',1,NULL,NULL,NULL,NULL,NULL),
	(29,'Ficha de mantenimiento de equipos','','',1,NULL,NULL,NULL,NULL,NULL),
	(30,'Índice de ediciones de documentos','','',1,NULL,NULL,NULL,NULL,NULL),
	(31,'Índice de no conformidades','','',1,NULL,NULL,NULL,NULL,NULL),
	(32,'Inventario de recursos','','',1,NULL,NULL,NULL,NULL,NULL),
	(33,'Listado de documentación externa en vigor','','',1,NULL,NULL,NULL,NULL,NULL),
	(34,'Listado de proveedores y subcontratistas evaluados','','',1,NULL,NULL,NULL,NULL,NULL),
	(35,'Listado de registros','','',1,NULL,NULL,NULL,NULL,NULL),
	(36,'Manual de Gestión de la Calidad (MGC)','','MGC',1,NULL,NULL,NULL,NULL,NULL),
	(37,'Perfil del empleado','','',1,NULL,NULL,NULL,NULL,NULL),
	(38,'Perfil puesto trabajo','','',1,NULL,NULL,NULL,NULL,NULL),
	(39,'Plan de auditoría anual','','',1,NULL,NULL,NULL,NULL,NULL),
	(40,'Plan de formación anual','','',1,NULL,NULL,NULL,NULL,NULL),
	(41,'Política de Calidad','','',1,NULL,NULL,NULL,NULL,NULL),
	(42,'(I5-PC03) Registro perfil del empleado','','I5-PC03',1,NULL,NULL,NULL,NULL,NULL),
	(43,'(I8-PC03) Cuestionario de satisfacción laboral','','I8-PC03',1,NULL,NULL,NULL,NULL,NULL),
	(44,'(I6-PC08) Evaluación de satisfacción del cliente','','I6-PC08',1,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `DocumentCategory` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla DocumentCategoryDoc
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DocumentCategoryDoc`;

CREATE TABLE `DocumentCategoryDoc` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `categoryid` int(10) unsigned NOT NULL,
  `documentid` int(10) unsigned NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DocumentCategorydoc_category` (`categoryid`),
  KEY `FK_DocumentCategorydoc_docu` (`documentid`),
  CONSTRAINT `FK_DocumentCategorydoc_category` FOREIGN KEY (`categoryid`) REFERENCES `DocumentCategory` (`id`),
  CONSTRAINT `FK_DocumentCategorydoc_docu` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Volcado de tabla DocumentVersion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DocumentVersion`;

CREATE TABLE `DocumentVersion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `documentPath` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `creationDate` datetime NOT NULL,
  `version` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `documentid` int(10) unsigned NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DocumentVersion_document_id` (`documentid`),
  CONSTRAINT `FK_DocumentVersion_document_id` FOREIGN KEY (`documentid`) REFERENCES `Document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla EntityChange
# ------------------------------------------------------------

DROP TABLE IF EXISTS `EntityChange`;

CREATE TABLE `EntityChange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `oldValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `newValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `userId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `entityId` int(11) NOT NULL,
  `entityName` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_entityChange_user` (`userId`),
  CONSTRAINT `fk_entityChange_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla ExternalActivity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ExternalActivity`;

CREATE TABLE `ExternalActivity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `category` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `location` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `organizer` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `comments` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `documentCategoryId` int(10) unsigned DEFAULT NULL,
  `ownerId` int(10) DEFAULT NULL,
  `departmentId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_externalactivity_documentcategory` (`documentCategoryId`),
  CONSTRAINT `fk_externalactivity_documentcategory` FOREIGN KEY (`documentCategoryId`) REFERENCES `DocumentCategory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla FinancialRatio
# ------------------------------------------------------------

DROP TABLE IF EXISTS `FinancialRatio`;

CREATE TABLE `FinancialRatio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `ratioDate` date NOT NULL,
  `banksAccounts` decimal(10,2) NOT NULL,
  `customers` decimal(10,2) NOT NULL,
  `stocks` decimal(10,2) NOT NULL,
  `amortizations` decimal(10,2) NOT NULL,
  `infrastructures` decimal(10,2) NOT NULL,
  `shortTermLiability` decimal(10,2) NOT NULL,
  `obligationBond` decimal(10,2) NOT NULL,
  `capital` decimal(10,2) NOT NULL,
  `reserves` decimal(10,2) NOT NULL,
  `incomes` decimal(10,2) NOT NULL,
  `expenses` decimal(10,2) NOT NULL,
  `otherExploitationExpenses` decimal(10,2) NOT NULL,
  `financialExpenses` decimal(10,2) NOT NULL,
  `taxes` decimal(10,2) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ratios financieros';



# Volcado de tabla Frequency
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Frequency`;

CREATE TABLE `Frequency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `months` int(10) unsigned DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Frecuencia de los asientos contables';

LOCK TABLES `Frequency` WRITE;
/*!40000 ALTER TABLE `Frequency` DISABLE KEYS */;

INSERT INTO `Frequency` (`id`, `name`, `months`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Mensual',1,NULL,NULL,NULL,NULL),
	(2,'Trimestral',3,NULL,NULL,NULL,NULL),
	(3,'Semestral',6,NULL,NULL,NULL,NULL),
	(4,'Anual',12,NULL,NULL,NULL,NULL),
	(5,'Bimensual',2,NULL,NULL,NULL,NULL),
	(6,'Ocasional',0,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `Frequency` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Holiday
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Holiday`;

CREATE TABLE `Holiday` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `date` datetime NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Idea
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Idea`;

CREATE TABLE `Idea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `cost` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `benefits` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `name` varchar(300) COLLATE utf8_spanish_ci NOT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_idea_userId` (`userId`),
  CONSTRAINT `fk_idea_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ideas de los empleados';



# Volcado de tabla Interaction
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Interaction`;

CREATE TABLE `Interaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL DEFAULT '5' COMMENT 'project id',
  `userId` int(11) NOT NULL DEFAULT '1' COMMENT 'user id',
  `interactionTypeId` int(11) NOT NULL DEFAULT '6' COMMENT 'type id',
  `creationDate` datetime NOT NULL,
  `interest` varchar(16) COLLATE utf8_spanish_ci NOT NULL COMMENT 'enum InteractionInterest',
  `description` varchar(2048) COLLATE utf8_spanish_ci NOT NULL,
  `file` varchar(400) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fileMime` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `offerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_interaction_projectId` (`projectId`),
  KEY `ndx_interaction_userId` (`userId`),
  KEY `ndx_interaction_interactionTypeId` (`interactionTypeId`),
  KEY `ndx_interaction_offerId` (`offerId`),
  CONSTRAINT `fk_interaction_interactionTypeId` FOREIGN KEY (`interactionTypeId`) REFERENCES `InteractionType` (`id`),
  CONSTRAINT `fk_interaction_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`),
  CONSTRAINT `fk_interaction_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_interaction_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='contactos que se mantienen con ';



# Volcado de tabla InteractionType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `InteractionType`;

CREATE TABLE `InteractionType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Interaction type descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Interaction type description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de interacciones';

LOCK TABLES `InteractionType` WRITE;
/*!40000 ALTER TABLE `InteractionType` DISABLE KEYS */;

INSERT INTO `InteractionType` (`id`, `name`, `description`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'No conformidad','',NULL,NULL,NULL,NULL),
	(2,'Accion comercial','',NULL,NULL,NULL,NULL),
	(3,'Envío de oferta','',NULL,NULL,NULL,NULL),
	(4,'Envío de factura','',NULL,NULL,NULL,NULL),
	(5,'Accion administrativa','',NULL,NULL,NULL,NULL),
	(6,'No definida','',NULL,NULL,NULL,NULL),
	(7,'Primer contacto','',NULL,NULL,'2018-04-20 15:37:12','2018-04-20 15:37:12'),
	(8,'Confirmación de oferta','',NULL,NULL,'2018-04-20 15:37:12','2018-04-20 15:37:12');

/*!40000 ALTER TABLE `InteractionType` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Inventory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Inventory`;

CREATE TABLE `Inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyDate` date DEFAULT NULL,
  `asignedToId` int(11) DEFAULT NULL,
  `renting` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'De renting (1) o no (0)',
  `cost` decimal(10,2) DEFAULT NULL,
  `amortizable` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Amortizable (1) o no (0)consumible',
  `serialNumber` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `type` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `provider` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `trademark` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `model` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `speed` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `storage` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ram` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `location` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `description` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_invetory_userId` (`asignedToId`),
  CONSTRAINT `fk_inventory_userId` FOREIGN KEY (`asignedToId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Inventory de mquinas';



# Volcado de tabla Magazine
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Magazine`;

CREATE TABLE `Magazine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Magazines a las que se envan';



# Volcado de tabla Objective
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Objective`;

CREATE TABLE `Objective` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `projectId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `name` varchar(512) COLLATE utf8_spanish_ci NOT NULL,
  `log` longtext COLLATE utf8_spanish_ci,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_objective_userId` (`userId`),
  KEY `ndx_objective_projectId` (`projectId`),
  CONSTRAINT `fk_objective_projectId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_objective_userId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Se almacenan los Objectives';



# Volcado de tabla Occupation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Occupation`;

CREATE TABLE `Occupation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `duration` int(11) NOT NULL COMMENT 'Duración en minutos',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_occupation_userId` (`userId`),
  KEY `ndx_occupation_projectId` (`projectId`),
  CONSTRAINT `fk_occupation_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`),
  CONSTRAINT `fk_occupation_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Future occupations of Users';



# Volcado de tabla Offer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Offer`;

CREATE TABLE `Offer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `organizationId` int(11) NOT NULL,
  `contactId` int(11) NOT NULL,
  `creationDate` date NOT NULL,
  `validityDate` date DEFAULT NULL,
  `maturityDate` date DEFAULT NULL,
  `offerPotential` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `offerState` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `offerRejectReasonId` int(11) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `number` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `observations` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `showIvaIntoReport` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `ndx_offer_organizationId` (`organizationId`),
  KEY `ndx_offer_contactId` (`contactId`),
  KEY `ndx_offer_offerRejectReasonId` (`offerRejectReasonId`),
  KEY `ndx_offer_userId` (`userId`),
  CONSTRAINT `fk_offer_contactId` FOREIGN KEY (`contactId`) REFERENCES `Contact` (`id`),
  CONSTRAINT `fk_offer_offerRejectReasonId` FOREIGN KEY (`offerRejectReasonId`) REFERENCES `OfferRejectReason` (`id`),
  CONSTRAINT `fk_offer_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_offer_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Ofertas';



# Volcado de tabla OfferCost
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OfferCost`;

CREATE TABLE `OfferCost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `offerId` int(11) NOT NULL,
  `name` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cost` decimal(10,2) NOT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  `iva` decimal(4,2) NOT NULL DEFAULT '16.00',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `units` decimal(10,2) NOT NULL DEFAULT '0.00',
  `place` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_offerCost_offerId` (`offerId`),
  CONSTRAINT `fk_offerCost_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla OfferRejectReason
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OfferRejectReason`;

CREATE TABLE `OfferRejectReason` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Causas rechazo de ofertas';

LOCK TABLES `OfferRejectReason` WRITE;
/*!40000 ALTER TABLE `OfferRejectReason` DISABLE KEYS */;

INSERT INTO `OfferRejectReason` (`id`, `title`, `description`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Sin respuesta','El cliente no responde a la oferta',1,1,'2018-04-20 15:37:12','2018-04-20 15:37:12'),
	(2,'Oferta cara','El cliente considera la oferta excesivamente cara',1,1,'2018-04-20 15:37:12','2018-04-20 15:37:12'),
	(3,'Tecnología inadecuada','Tecnología escogida en la oferta inadecuada',1,1,'2018-04-20 15:37:12','2018-04-20 15:37:12'),
	(4,'Proyecto retrasado','El proyecto para el cual se hizo la oferta ha sido retrasado',1,1,'2018-04-20 15:37:12','2018-04-20 15:37:12'),
	(5,'Proyecto cancelado','El proyecto para el cual se hizo la oferta ha sido cancelado',1,1,'2018-04-20 15:37:12','2018-04-20 15:37:12');

/*!40000 ALTER TABLE `OfferRejectReason` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla OfferRole
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OfferRole`;

CREATE TABLE `OfferRole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `offerId` int(11) NOT NULL,
  `name` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `costPerHour` decimal(10,2) NOT NULL,
  `expectedHours` int(11) NOT NULL,
  `iva` decimal(4,2) NOT NULL DEFAULT '16.00',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `place` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_offerRole_offerId` (`offerId`),
  CONSTRAINT `fk_offerRole_offerId` FOREIGN KEY (`offerId`) REFERENCES `Offer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Organization
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Organization`;

CREATE TABLE `Organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizationTypeId` int(11) NOT NULL DEFAULT '1' COMMENT 'organization type id',
  `organizationISOCategoryId` int(11) NOT NULL DEFAULT '1' COMMENT 'iso category id',
  `name` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cif` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `street` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `number` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Building number in street',
  `locator` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Location information inside building',
  `postalCode` varchar(32) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provinceId` int(11) NOT NULL,
  `state` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `country` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `website` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ftpsite` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `notes` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `evaluationCriteria` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_organization_organizationTypeId` (`organizationTypeId`),
  KEY `ndx_organization_isoOrganizationCategoryId` (`organizationISOCategoryId`),
  CONSTRAINT `fk_organization_isoOrganizationCategoryId` FOREIGN KEY (`organizationISOCategoryId`) REFERENCES `OrganizationISOCategory` (`id`),
  CONSTRAINT `fk_organization_organizationTypeId` FOREIGN KEY (`organizationTypeId`) REFERENCES `OrganizationType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='los clientes';

LOCK TABLES `Organization` WRITE;
/*!40000 ALTER TABLE `Organization` DISABLE KEYS */;

INSERT INTO `Organization` (`id`, `organizationTypeId`, `organizationISOCategoryId`, `name`, `cif`, `phone`, `street`, `number`, `locator`, `postalCode`, `city`, `provinceId`, `state`, `country`, `fax`, `email`, `website`, `ftpsite`, `notes`, `ownerId`, `departmentId`, `insertDate`, `updateDate`, `evaluationCriteria`)
VALUES
	(1,2,1,'Nuestra empresa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,28,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(2,1,1,'Indefinida',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'2018-04-20 15:37:14','2018-04-20 15:37:14',NULL);

/*!40000 ALTER TABLE `Organization` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Organization_Tag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Organization_Tag`;

CREATE TABLE `Organization_Tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) NOT NULL,
  `organizationId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_organization_tag_tag` (`tagId`),
  KEY `fk_organization_tag_organization` (`organizationId`),
  CONSTRAINT `fk_organization_tag_organization` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_organization_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `Tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla OrganizationISOCategory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OrganizationISOCategory`;

CREATE TABLE `OrganizationISOCategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'ISO Organization Category descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'ISO Organization Category description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de organizaciones segun ISO';

LOCK TABLES `OrganizationISOCategory` WRITE;
/*!40000 ALTER TABLE `OrganizationISOCategory` DISABLE KEYS */;

INSERT INTO `OrganizationISOCategory` (`id`, `name`, `description`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'A','Proveedor / Subcontratista habitual.',NULL,NULL,NULL,NULL),
	(2,'B','Proveedor / Subcontratista recomendado.',NULL,NULL,NULL,NULL),
	(3,'C','Proveedor / Subcontratista no habitual.',NULL,NULL,NULL,NULL),
	(4,'D','Proveedor / Subcontratista que haya tenido disconformidades.',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `OrganizationISOCategory` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla OrganizationType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OrganizationType`;

CREATE TABLE `OrganizationType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Organization type descriptive name',
  `description` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Organization type description',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tipos de organizaciones';

LOCK TABLES `OrganizationType` WRITE;
/*!40000 ALTER TABLE `OrganizationType` DISABLE KEYS */;

INSERT INTO `OrganizationType` (`id`, `name`, `description`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Cliente','',NULL,NULL,NULL,NULL),
	(2,'Proveedor','',NULL,NULL,NULL,NULL),
	(3,'Cliente y proveedor','',NULL,NULL,NULL,NULL),
	(4,'Prospecto','Posible cliente',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `OrganizationType` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla PeriodicalAccountEntry
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PeriodicalAccountEntry`;

CREATE TABLE `PeriodicalAccountEntry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT 'account where the entry is charged',
  `accountEntryTypeId` int(11) NOT NULL COMMENT 'entry type',
  `frequencyId` int(11) NOT NULL COMMENT 'entry frequency',
  `concept` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `entryDate` date NOT NULL COMMENT 'entry date',
  `amount` decimal(10,2) NOT NULL COMMENT 'entry amount',
  `rise` decimal(4,2) DEFAULT NULL COMMENT 'account entry rise',
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `organizationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_periodicalAccountEntry_accountId` (`accountId`),
  KEY `ndx_periodicalAccountEntry_accountEntryTypeId` (`accountEntryTypeId`),
  KEY `ndx_periodicalAccountEntry_frequencyId` (`frequencyId`),
  KEY `ndx_periodicalAccountEntry_organizationId` (`organizationId`),
  CONSTRAINT `FK_periodicalaccountentry_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`),
  CONSTRAINT `fk_periodicalAccountEntry_accountEntryTypeId` FOREIGN KEY (`accountEntryTypeId`) REFERENCES `AccountEntryType` (`id`),
  CONSTRAINT `fk_periodicalAccountEntry_accountId` FOREIGN KEY (`accountId`) REFERENCES `Account` (`id`),
  CONSTRAINT `fk_periodicalAccountEntry_frequencyId` FOREIGN KEY (`frequencyId`) REFERENCES `Frequency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='asientos contables periodicos';



# Volcado de tabla Position
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Position`;

CREATE TABLE `Position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `email` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fax` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `postalCode` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provinceId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_position_provinceId` (`provinceId`),
  CONSTRAINT `fk_position_province` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

LOCK TABLES `Position` WRITE;
/*!40000 ALTER TABLE `Position` DISABLE KEYS */;

INSERT INTO `Position` (`id`, `name`, `description`, `ownerId`, `departmentId`, `insertDate`, `updateDate`, `deleteDate`, `email`, `phone`, `fax`, `address`, `postalCode`, `city`, `country`, `provinceId`)
VALUES
	(1,'Indefinido','Puesto sin definir',1,1,'2018-04-20 15:37:14','2018-04-20 15:37:14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `Position` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Position_Department
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Position_Department`;

CREATE TABLE `Position_Department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `positionId` int(11) NOT NULL,
  `departmentId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_position_department_position` (`positionId`),
  KEY `fk_position_department_department` (`departmentId`),
  CONSTRAINT `fk_position_department_department` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_position_department_position` FOREIGN KEY (`positionId`) REFERENCES `Position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

LOCK TABLES `Position_Department` WRITE;
/*!40000 ALTER TABLE `Position_Department` DISABLE KEYS */;

INSERT INTO `Position_Department` (`id`, `positionId`, `departmentId`)
VALUES
	(1,1,1),
	(2,1,4),
	(3,1,2),
	(4,1,3);

/*!40000 ALTER TABLE `Position_Department` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Position_Tag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Position_Tag`;

CREATE TABLE `Position_Tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_position_tag_tag` (`tagId`),
  KEY `fk_position_tag_position` (`positionId`),
  CONSTRAINT `fk_position_tag_position` FOREIGN KEY (`positionId`) REFERENCES `Position` (`id`),
  CONSTRAINT `fk_position_tag_tag` FOREIGN KEY (`tagId`) REFERENCES `Tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla PositionChange
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PositionChange`;

CREATE TABLE `PositionChange` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `oldValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `newValue` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `positionId` int(10) DEFAULT NULL,
  `userId` int(10) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_positionChange_position` (`positionId`),
  KEY `fk_positionChange_user` (`userId`),
  CONSTRAINT `fk_positionChange_position` FOREIGN KEY (`positionId`) REFERENCES `Position` (`id`),
  CONSTRAINT `fk_positionChange_user` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Project
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Project`;

CREATE TABLE `Project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organizationId` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `open` tinyint(1) DEFAULT '0',
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `ndx_project_organizationId` (`organizationId`),
  CONSTRAINT `fk_project_organizationId` FOREIGN KEY (`organizationId`) REFERENCES `Organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Projects';

LOCK TABLES `Project` WRITE;
/*!40000 ALTER TABLE `Project` DISABLE KEYS */;

INSERT INTO `Project` (`id`, `organizationId`, `startDate`, `endDate`, `open`, `name`, `description`, `ownerId`, `departmentId`, `insertDate`, `updateDate`, `billable`)
VALUES
	(1,1,'2018-04-20',NULL,1,'Vacaciones',NULL,NULL,NULL,NULL,NULL,1),
	(2,1,'2018-04-20',NULL,1,'Permiso extraordinario',NULL,NULL,NULL,NULL,NULL,1),
	(3,1,'2018-04-20',NULL,1,'Baja por enfermedad',NULL,NULL,NULL,NULL,NULL,1),
	(4,1,'2018-04-20',NULL,1,'Auto-formación',NULL,NULL,NULL,NULL,NULL,1),
	(5,1,'2018-04-20',NULL,1,'Histórico',NULL,NULL,NULL,NULL,NULL,1);

/*!40000 ALTER TABLE `Project` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla ProjectCost
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ProjectCost`;

CREATE TABLE `ProjectCost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `billable` tinyint(1) NOT NULL DEFAULT '1',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_projectCost_projectId` (`projectId`),
  CONSTRAINT `fk_projectCost_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla ProjectRole
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ProjectRole`;

CREATE TABLE `ProjectRole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `costPerHour` decimal(10,2) NOT NULL,
  `expectedHours` int(11) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_projectRole_projectId` (`projectId`),
  CONSTRAINT `fk_projectRole_projectId` FOREIGN KEY (`projectId`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

LOCK TABLES `ProjectRole` WRITE;
/*!40000 ALTER TABLE `ProjectRole` DISABLE KEYS */;

INSERT INTO `ProjectRole` (`id`, `projectId`, `name`, `costPerHour`, `expectedHours`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,1,'Vacaciones',0.00,0,NULL,NULL,NULL,NULL),
	(2,2,'Permiso extraordinario',0.00,0,NULL,NULL,NULL,NULL),
	(3,3,'Baja por enfermedad',0.00,0,NULL,NULL,NULL,NULL),
	(4,4,'Auto-formación',0.00,0,NULL,NULL,NULL,NULL),
	(5,5,'Histórico',0.00,0,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `ProjectRole` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Province
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Province`;

CREATE TABLE `Province` (
  `id` int(11) NOT NULL COMMENT 'El id no es autoincremental porque ya tienen unos códigos fijos',
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla con las Provinces';

LOCK TABLES `Province` WRITE;
/*!40000 ALTER TABLE `Province` DISABLE KEYS */;

INSERT INTO `Province` (`id`, `name`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Alava',NULL,NULL,NULL,NULL),
	(2,'Albacete',NULL,NULL,NULL,NULL),
	(3,'Alicante',NULL,NULL,NULL,NULL),
	(4,'Almería',NULL,NULL,NULL,NULL),
	(5,'Avila',NULL,NULL,NULL,NULL),
	(6,'Badajoz',NULL,NULL,NULL,NULL),
	(7,'Balears, Illes',NULL,NULL,NULL,NULL),
	(8,'Barcelona',NULL,NULL,NULL,NULL),
	(9,'Burgos',NULL,NULL,NULL,NULL),
	(10,'Cáceres',NULL,NULL,NULL,NULL),
	(11,'Cádiz',NULL,NULL,NULL,NULL),
	(12,'Castellón',NULL,NULL,NULL,NULL),
	(13,'Ciudad Real',NULL,NULL,NULL,NULL),
	(14,'Córdoba',NULL,NULL,NULL,NULL),
	(15,'Coruña, A',NULL,NULL,NULL,NULL),
	(16,'Cuenca',NULL,NULL,NULL,NULL),
	(17,'Girona',NULL,NULL,NULL,NULL),
	(18,'Granada',NULL,NULL,NULL,NULL),
	(19,'Guadalajara',NULL,NULL,NULL,NULL),
	(20,'Guipúzcoa',NULL,NULL,NULL,NULL),
	(21,'Huelva',NULL,NULL,NULL,NULL),
	(22,'Huesca',NULL,NULL,NULL,NULL),
	(23,'Jaén',NULL,NULL,NULL,NULL),
	(24,'León',NULL,NULL,NULL,NULL),
	(25,'Lleida',NULL,NULL,NULL,NULL),
	(26,'Rioja, La',NULL,NULL,NULL,NULL),
	(27,'Lugo',NULL,NULL,NULL,NULL),
	(28,'Madrid',NULL,NULL,NULL,NULL),
	(29,'Málaga',NULL,NULL,NULL,NULL),
	(30,'Murcia',NULL,NULL,NULL,NULL),
	(31,'Navarra',NULL,NULL,NULL,NULL),
	(32,'Ourense',NULL,NULL,NULL,NULL),
	(33,'Asturias',NULL,NULL,NULL,NULL),
	(34,'Palencia',NULL,NULL,NULL,NULL),
	(35,'Palmas, Las',NULL,NULL,NULL,NULL),
	(36,'Pontevedra',NULL,NULL,NULL,NULL),
	(37,'Salamanca',NULL,NULL,NULL,NULL),
	(38,'Santa Cruz de Tenerife',NULL,NULL,NULL,NULL),
	(39,'Cantabria',NULL,NULL,NULL,NULL),
	(40,'Segovia',NULL,NULL,NULL,NULL),
	(41,'Sevilla',NULL,NULL,NULL,NULL),
	(42,'Soria',NULL,NULL,NULL,NULL),
	(43,'Tarragona',NULL,NULL,NULL,NULL),
	(44,'Teruel',NULL,NULL,NULL,NULL),
	(45,'Toledo',NULL,NULL,NULL,NULL),
	(46,'Valencia',NULL,NULL,NULL,NULL),
	(47,'Valladolid',NULL,NULL,NULL,NULL),
	(48,'Vizcaya',NULL,NULL,NULL,NULL),
	(49,'Zamora',NULL,NULL,NULL,NULL),
	(50,'Zaragoza',NULL,NULL,NULL,NULL),
	(51,'Ceuta',NULL,NULL,NULL,NULL),
	(52,'Melilla',NULL,NULL,NULL,NULL),
	(53,'Indefinido',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `Province` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Publication
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Publication`;

CREATE TABLE `Publication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `magazineId` int(11) NOT NULL,
  `magazineDeliveryDate` datetime DEFAULT NULL COMMENT 'Fecha de entrega a la Magazine',
  `magazinePublicationDate` date DEFAULT NULL,
  `ownPublicationDate` date DEFAULT NULL COMMENT 'Fecha de publicacin en Adictos',
  `accepted` tinyint(1) DEFAULT NULL COMMENT 'Indicador de si el Tutorial ha sido aceptado (1) o no (0)',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_publication_tutorialId` (`id`),
  KEY `fk_publication_magazineId` (`magazineId`),
  CONSTRAINT `fk_publication_magazineId` FOREIGN KEY (`magazineId`) REFERENCES `Magazine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='publicaciones de tutoriales';



# Volcado de tabla RequestHoliday
# ------------------------------------------------------------

DROP TABLE IF EXISTS `RequestHoliday`;

CREATE TABLE `RequestHoliday` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `beginDate` datetime NOT NULL,
  `finalDate` datetime NOT NULL,
  `state` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `userId` int(11) NOT NULL,
  `observations` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `userComment` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `chargeYear` date NOT NULL DEFAULT '2007-01-01',
  PRIMARY KEY (`id`),
  KEY `fk_requestHoliday_userId` (`userId`),
  CONSTRAINT `fk_requestHoliday_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Role`;

CREATE TABLE `Role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Roles de la aplicacin';

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;

INSERT INTO `Role` (`id`, `name`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Administrador',NULL,NULL,NULL,NULL),
	(2,'Supervisor',NULL,NULL,NULL,NULL),
	(3,'Usuario',NULL,NULL,NULL,NULL),
	(4,'Staff',NULL,NULL,NULL,NULL),
	(5,'Cliente',NULL,NULL,NULL,NULL),
	(6,'Project Manager',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Setting
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Setting`;

CREATE TABLE `Setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `value` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='User settings';

LOCK TABLES `Setting` WRITE;
/*!40000 ALTER TABLE `Setting` DISABLE KEYS */;

INSERT INTO `Setting` (`id`, `type`, `name`, `value`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'BOOLEAN','bitacore.last.billable','true',1,1,'2018-04-20 18:37:20','2018-04-20 18:37:57'),
	(2,'INT','bitacore.last.roleId','3',1,1,'2018-04-20 18:37:20','2018-04-20 18:37:57');

/*!40000 ALTER TABLE `Setting` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Tag
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Tag`;

CREATE TABLE `Tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



# Volcado de tabla Tutorial
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Tutorial`;

CREATE TABLE `Tutorial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `maxDeliveryDate` datetime NOT NULL,
  `endDate` datetime DEFAULT NULL COMMENT 'Fecha de finalizacin del Tutorial',
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ndx_tutorial_userId` (`userId`),
  CONSTRAINT `fk_tutorial_userId` FOREIGN KEY (`userId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tutoriales que son enviado a la';



# Volcado de tabla User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `roleId` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'User activo',
  `name` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `nif` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `academicQualification` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  `mobile` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  `street` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `postalCode` varchar(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provinceId` int(11) DEFAULT NULL,
  `married` tinyint(1) NOT NULL COMMENT 'Casado (1) o no (0)',
  `childrenNumber` tinyint(4) NOT NULL,
  `drivenLicenseType` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `vehicleType` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `licensePlate` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `startDate` date NOT NULL COMMENT 'fecha de alta en la empresa',
  `categoryId` int(11) NOT NULL,
  `socialSecurityNumber` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `bank` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `account` varchar(34) COLLATE utf8_spanish_ci DEFAULT NULL,
  `travelAvailability` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'Disponibilidad para viajar',
  `workingInClient` tinyint(1) NOT NULL COMMENT 'Si esta tabajando en el cliente',
  `email` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `genre` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `salaryExtras` decimal(10,2) DEFAULT NULL,
  `documentCategoryId` int(10) unsigned DEFAULT NULL,
  `securityCard` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `healthInsurance` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `notes` varchar(1024) COLLATE utf8_spanish_ci DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `endTestPeriodDate` date DEFAULT NULL,
  `endContractDate` date DEFAULT NULL,
  `departmentId` int(10) unsigned NOT NULL DEFAULT '1',
  `contractTypeId` int(10) unsigned DEFAULT NULL,
  `contractObservations` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `dayDuration` int(11) DEFAULT NULL,
  `agreementId` int(11) NOT NULL DEFAULT '1',
  `agreementYearDuration` int(11) DEFAULT NULL,
  `passwordExpireDate` date DEFAULT NULL COMMENT 'Password Expire Date',
  PRIMARY KEY (`id`),
  KEY `ndx_user_roleId` (`roleId`),
  KEY `ndx_user_provinceId` (`provinceId`),
  KEY `ndx_user_categoryId` (`categoryId`),
  KEY `ndx_user_documentCategoryId` (`documentCategoryId`),
  KEY `ndx_user_departmentId` (`departmentId`),
  KEY `ndx_user_contractTypeId` (`contractTypeId`),
  KEY `ndx_user_agreementId` (`agreementId`),
  CONSTRAINT `fk_user_agreementId` FOREIGN KEY (`agreementId`) REFERENCES `WorkingAgreement` (`id`),
  CONSTRAINT `fk_user_categoryId` FOREIGN KEY (`categoryId`) REFERENCES `UserCategory` (`id`),
  CONSTRAINT `fk_user_contractTypeId` FOREIGN KEY (`contractTypeId`) REFERENCES `ContractType` (`id`),
  CONSTRAINT `fk_user_departmentId` FOREIGN KEY (`departmentId`) REFERENCES `Department` (`id`),
  CONSTRAINT `fk_user_documentCategoryId` FOREIGN KEY (`documentCategoryId`) REFERENCES `DocumentCategory` (`id`),
  CONSTRAINT `fk_user_provinceId` FOREIGN KEY (`provinceId`) REFERENCES `Province` (`id`),
  CONSTRAINT `fk_user_roleId` FOREIGN KEY (`roleId`) REFERENCES `Role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Users de la aplicacin';

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;

INSERT INTO `User` (`id`, `login`, `password`, `roleId`, `active`, `name`, `nif`, `birthDate`, `academicQualification`, `phone`, `mobile`, `street`, `city`, `postalCode`, `provinceId`, `married`, `childrenNumber`, `drivenLicenseType`, `vehicleType`, `licensePlate`, `startDate`, `categoryId`, `socialSecurityNumber`, `bank`, `account`, `travelAvailability`, `workingInClient`, `email`, `genre`, `salary`, `salaryExtras`, `documentCategoryId`, `securityCard`, `healthInsurance`, `notes`, `photo`, `endTestPeriodDate`, `endContractDate`, `departmentId`, `contractTypeId`, `contractObservations`, `insertDate`, `updateDate`, `dayDuration`, `agreementId`, `agreementYearDuration`, `passwordExpireDate`)
VALUES
	(1,'admin','dd94709528bb1c83d08f3088d4043f4742891f4f',1,1,'Administrador',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,'2018-04-20',1,NULL,NULL,NULL,NULL,0,NULL,NULL,0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,480,1,NULL,NULL);

/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla UserCategory
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserCategory`;

CREATE TABLE `UserCategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Se almacenan las categorias';

LOCK TABLES `UserCategory` WRITE;
/*!40000 ALTER TABLE `UserCategory` DISABLE KEYS */;

INSERT INTO `UserCategory` (`id`, `name`, `ownerId`, `departmentId`, `insertDate`, `updateDate`)
VALUES
	(1,'Socio',NULL,NULL,NULL,NULL),
	(2,'Becario',NULL,NULL,NULL,NULL),
	(3,'Administrativo',NULL,NULL,NULL,NULL),
	(4,'Gerente',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `UserCategory` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Version
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Version`;

CREATE TABLE `Version` (
  `version` varchar(32) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='version de la base de datos';

LOCK TABLES `Version` WRITE;
/*!40000 ALTER TABLE `Version` DISABLE KEYS */;

INSERT INTO `Version` (`version`)
VALUES
	('0.29');

/*!40000 ALTER TABLE `Version` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla WorkingAgreement
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WorkingAgreement`;

CREATE TABLE `WorkingAgreement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(2048) COLLATE utf8_spanish_ci DEFAULT NULL,
  `holidays` int(11) NOT NULL DEFAULT '22',
  `ownerId` int(11) DEFAULT NULL,
  `departmentId` int(10) unsigned DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `yearDuration` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Convenios laborales';

LOCK TABLES `WorkingAgreement` WRITE;
/*!40000 ALTER TABLE `WorkingAgreement` DISABLE KEYS */;

INSERT INTO `WorkingAgreement` (`id`, `name`, `description`, `holidays`, `ownerId`, `departmentId`, `insertDate`, `updateDate`, `yearDuration`)
VALUES
	(1,'Convenio Nuestra Empresa','Este es el convenio de nuestra empresa',22,NULL,NULL,NULL,NULL,0);

/*!40000 ALTER TABLE `WorkingAgreement` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
