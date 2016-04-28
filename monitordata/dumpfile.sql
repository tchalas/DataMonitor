-- MySQL dump 10.13  Distrib 5.1.66, for debian-linux-gnu (i486)
--
-- Host: 195.134.65.215    Database: sdi0700134
-- ------------------------------------------------------
-- Server version	5.5.28-0ubuntu0.12.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AP_RSS`
--

DROP TABLE IF EXISTS `AP_RSS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AP_RSS` (
  `Signal_Strength` int(11) DEFAULT NULL,
  `deviceName` varchar(50) NOT NULL,
  `AP_mac_add` varchar(20) NOT NULL,
  PRIMARY KEY (`deviceName`,`AP_mac_add`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AP_RSS`
--

LOCK TABLES `AP_RSS` WRITE;
/*!40000 ALTER TABLE `AP_RSS` DISABLE KEYS */;
INSERT INTO `AP_RSS` VALUES (-89,'workstation','00:05:59:30:C1:7C'),(-89,'workstation','00:05:59:3B:C1:FA'),(-85,'workstation','00:05:59:55:4D:47'),(-79,'workstation','00:14:7F:71:B0:AD'),(-85,'workstation','00:1C:A2:B3:B0:25'),(-63,'workstation','00:26:44:A3:EB:63'),(-35,'workstation','00:26:44:CC:B9:F0');
/*!40000 ALTER TABLE `AP_RSS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_A`
--

DROP TABLE IF EXISTS `list_A`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list_A` (
  `NAME` varchar(10) NOT NULL,
  `mac_add` varchar(20) NOT NULL DEFAULT '',
  `ip_add` varchar(20) DEFAULT NULL,
  `default_gateway` varchar(20) DEFAULT NULL,
  `net_add` varchar(20) DEFAULT NULL,
  `mask` varchar(20) DEFAULT NULL,
  `broad_add` varchar(20) DEFAULT NULL,
  `max_transfer_rate` int(11) DEFAULT NULL,
  `curr_transfer_rate` double DEFAULT NULL,
  `curr_used_bandwidth` double DEFAULT NULL,
  `packet_error_rate` double DEFAULT NULL,
  `deviceName` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`NAME`,`deviceName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_A`
--

LOCK TABLES `list_A` WRITE;
/*!40000 ALTER TABLE `list_A` DISABLE KEYS */;
INSERT INTO `list_A` VALUES ('lo','No Mac Address','127.0.0.1','192.168.1.254','127.0.0.0.','255.0.0.0','No Broadcast Address',0,0,0,0,'workstation'),('eth1','b8:a3:86:7c:12:c5','192.168.1.69','192.168.1.254','192.168.1.0.','255.255.255.0','192.168.1.255',100000,0.27285592431761785,0.000011956226736972703,0,'workstation'),('ra0','00:26:44:CC:B9:F0','192.168.1.71','192.168.1.254','192.168.1.0.','255.255.255.0','192.168.1.255',0,3.0130053323412698,0,0,'workstation');
/*!40000 ALTER TABLE `list_A` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_B`
--

DROP TABLE IF EXISTS `list_B`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list_B` (
  `ESSID` varchar(40) DEFAULT NULL,
  `mac_base` varchar(20) DEFAULT NULL,
  `channel` int(11) DEFAULT NULL,
  `access_point_status` varchar(20) DEFAULT NULL,
  `connection_quality` varchar(10) DEFAULT NULL,
  `received_signal_strength` int(11) DEFAULT NULL,
  `transmitted_signal` varchar(40) DEFAULT NULL,
  `noise` int(11) DEFAULT NULL,
  `rejected_packets` int(11) DEFAULT NULL,
  `deviceName` varchar(50) NOT NULL DEFAULT '',
  `interfaceName` varchar(20) NOT NULL,
  PRIMARY KEY (`interfaceName`,`deviceName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_B`
--

LOCK TABLES `list_B` WRITE;
/*!40000 ALTER TABLE `list_B` DISABLE KEYS */;
INSERT INTO `list_B` VALUES ('\"ThomsonCCB9F0\"',NULL,1,'Managed','100/100',-36,'Not-Supported',-36,0,'workstation','ra0');
/*!40000 ALTER TABLE `list_B` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_C`
--

DROP TABLE IF EXISTS `list_C`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list_C` (
  `AP_mac_address` varchar(20) NOT NULL,
  `AP_ESSID` varchar(50) DEFAULT NULL,
  `AP_Channel` int(11) DEFAULT NULL,
  `AP_Status` varchar(20) DEFAULT NULL,
  `deviceName` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`AP_mac_address`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_C`
--

LOCK TABLES `list_C` WRITE;
/*!40000 ALTER TABLE `list_C` DISABLE KEYS */;
INSERT INTO `list_C` VALUES ('00:05:59:30:C1:7C','\"NA Home\"',6,'Managed','workstation'),('00:05:59:3B:C1:FA','\"Kpanagiotou\"',3,'Managed','workstation'),('00:05:59:55:4D:47','\"hol - NetFasteR WLAN 3\"',11,'Managed','workstation'),('00:14:7F:71:B0:AD','\"SpeedTouch953D12\"',6,'Managed','workstation'),('00:1C:A2:B3:B0:25','\"ONTelecoms1\"',9,'Managed','workstation'),('00:26:44:A3:EB:63','\"CYTADE9E36\"',6,'Managed','workstation'),('00:26:44:CC:B9:F0','\"ThomsonCCB9F0\"',1,'Managed','workstation');
/*!40000 ALTER TABLE `list_C` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-01-13 21:03:22
