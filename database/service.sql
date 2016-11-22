-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 13, 2016 at 03:31 AM
-- Server version: 5.7.15-log
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `service`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `fullName` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `fullAddress` varchar(250) DEFAULT NULL,
  `postalCode` varchar(10) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `token` varchar(30) DEFAULT NULL,
  `tanggalExp` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `fullName`, `username`, `email`, `password`, `fullAddress`, `postalCode`, `phoneNumber`, `token`, `tanggalExp`) VALUES
(1, 'Ahmad Fajar Prasetiyo', 'fajar', 'ahmad.fajar@gmail.com', '12345678', 'Jl. Argowayang no 31 Pare Kediri Jawa Timur', '43211', '085312843102', 'tbHcEQWO8o8BnwH', 0),
(2, 'Adam Rotal', 'adamrotal', 'adamrotal@gmail.com', '12345678', 'Jl. Raya No 14 Ngadiluwih Kediri', '64171', '085815072108', NULL, NULL),
(3, 'Dandu Satyanuraga', 'dandu', 'dandu@gmail.com', '12345678', 'Jl. MT. Haryono No 10 Ungaran', '50511', '081390979890', NULL, NULL),
(4, 'Dea Putri Pamungkas', 'deapamungkas', 'deapamungkas@gmail.com', '12345678', 'Jl. Raya No.14 Ngadiluwih Kab. Kediri', '64171', '085815072108', 'lS48CTiIhY6dNwv', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
