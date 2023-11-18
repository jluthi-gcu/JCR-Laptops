-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jcr_laptops_db
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (4,'William','Dafoe','wdafoe@gmail.com','7683671824','123 Main Street Apartment 4B Cityville, ST 54321'),(5,'Brad','Pitt','bpit@yahoo.com','8792165781','P.O. Box 789 Ruralville, ST 98765'),(6,'Leonardo','DiCaprio','LDiCaprio@hotmail.com','7658729816','222 Oceanview Drive Suite 3D Beachtown, FL 54321'),(7,'Jennifer','Lawrence','jlaw@gmail.com','4786547813','456 Elmwood Avenue Apt. 7C Springfield, CA 12345'),(8,'Scarlett','Johansson','sjohansson@marvelstudios.com','2758881732','33 Maplewood Road Woodsville, OR 67890'),(9,'Denzel','Washington','mr.dwashington@outlook.com','9651320123','777 Sunset Boulevard Riverside, WA 23456 USA'),(10,'Tom','Cruise','cruise@paramountpictures.com','5632874100','654 Birch Street Apartment 2B Mountainville, CO 34567 USA'),(11,'Gal','Gadot','ggadot@warnerbros.com','6231897456','888 Willow Lane Garden City, IL 45678 USA'),(12,'Harrison','Ford','hford@hotmail.com','8870315479','120 Rosewood Circle Townsville, AZ 89012 USA'),(13,'Keanu','Reeves','kreeves@fanmail.com','4329900172','555 Cherry Avenue Lakeside, GA 67890 USA');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  KEY `customer_id` (`customer_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (18,4,8,1,'2023-10-27 00:18:29'),(19,5,16,22,'2023-10-27 00:18:41'),(20,8,12,9,'2023-10-27 00:18:55'),(21,13,11,8,'2023-10-27 00:19:16'),(22,7,14,1,'2023-10-27 00:19:22'),(23,6,9,12,'2023-10-27 00:19:42'),(24,12,10,2,'2023-10-27 00:20:17'),(25,9,16,3,'2023-10-27 00:21:36'),(26,11,13,4,'2023-10-27 00:22:16'),(27,10,8,12,'2023-10-27 00:22:40'),(28,8,13,7,'2023-10-27 00:23:31'),(29,5,12,1,'2023-10-27 00:23:59');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (7,'ASUS TUF Gaming A17','Gaming Laptop, 17.3\" FHD 144Hz Display, GeForce RTX 4070, AMD Ryzen 9 7940HS, 16GB DDR5, 1TB PCIe 4.0 SSD',1500.12,23),(8,'Acer Nitro 5','144 Hz IPS - Intel Core i5 12th Gen 12500H (2.50GHz) - NVIDIA GeForce RTX 4050 Laptop GPU - 16 GB DDR5 - 512 GB PCIe SSD ',829.95,4),(9,'MSI GE Series','144 Hz IPS - Intel Core i7 12th Gen 12700H (2.30GHz) - NVIDIA GeForce RTX 3060 Laptop GPU - 16 GB DDR5 - 1 TB NVMe SSD',1049.00,11),(10,'HP OMEN','17.3\" 165 Hz IPS - Intel Core i7 13th Gen 13700HX (2.10GHz) - NVIDIA GeForce RTX 4070 Laptop GPU - 16 GB DDR5 - 1 TB PCIe SSD',1599.99,2),(11,'Lenovo Legion Pro 5 16IRX8','6.0\" 165 Hz IPS - Intel Core i7 13th Gen 13700HX (2.10GHz) - NVIDIA GeForce RTX 4060 Laptop GPU - 16 GB DDR5 - 1 TB PCIe SSD',1349.99,17),(12,'Dell XPS 15 9520 ','15.6\" Notebook - Full HD Plus - 1920 x 1200 - Intel Core i7 12th Gen i7-12700H Tetradeca-core (14 Core) - 32 GB Total RAM - 1 TB SSD',2381.00,8),(13,'GIGABYTE G6 KF ','16\" FHD 1920x1200 165Hz - NVIDIA GeForce RTX 4060 Laptop GPU - Intel Core i7-13620H - 16GB DDR5 RAM - 512GB SSD',1099.99,3),(14,'Apple MacBook Air','Apple M2/8-core GPU 8GB RAM 256GB SSD',899.99,21),(15,'MSI Pulse','15.6\" 60 Hz IPS - Intel Core i5 11th Gen 11400H (2.70GHz) - NVIDIA GeForce RTX 3050 Laptop GPU - 8 GB DDR4 - 512 GB NVMe SSD',599.99,5),(16,'Razer Blade 15','15.6\" 240 Hz - Intel Core i7 13th Gen 13800H (2.50GHz) - NVIDIA GeForce RTX 4060 Laptop GPU - 16 GB DDR5 - 1 TB PCIe SSD',2499.99,38);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Joel','Luthi','jluthi@example.com','111-222-3333','jluthi','$2a$10$qO./534n0w2wQzGGJ0ofyeFtrY4AO5whEEoLwzmLMUVIFllenMc3.'),(2,'Calvin','Clocuh','cclocuh@example.com','444-555-6666','cclocuh','$2a$10$qO./534n0w2wQzGGJ0ofyeFtrY4AO5whEEoLwzmLMUVIFllenMc3.'),(3,'Roland','Steinebrunner','rsteinebrunner@example.com','777-888-9999','rsteinebrunner','$2a$10$qO./534n0w2wQzGGJ0ofyeFtrY4AO5whEEoLwzmLMUVIFllenMc3.');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-26 17:26:41
