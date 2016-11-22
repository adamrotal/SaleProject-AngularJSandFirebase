-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 13, 2016 at 03:57 PM
-- Server version: 10.0.27-MariaDB-0ubuntu0.16.04.1
-- PHP Version: 7.0.8-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `marketplace`
--

-- --------------------------------------------------------

--
-- Table structure for table `liked`
--

CREATE TABLE `liked` (
  `id` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `idProduk` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id` int(11) NOT NULL,
  `idPenjual` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `gambar` varchar(100) DEFAULT NULL,
  `tanggalDiTambah` date DEFAULT NULL,
  `namaPenjual` varchar(50) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id`, `idPenjual`, `name`, `description`, `price`, `gambar`, `tanggalDiTambah`, `namaPenjual`, `deleted`) VALUES
(1, 4, 'Macbook Pro Retina Display', 'Dijual macbook pro retina display 2015.\r\n\r\nSpec :\r\nIntel core i5\r\nRAM 8GB\r\n256 SSD\r\nCC 6\r\n\r\nFull set!', 15000000, 'macbook_pro.jpg', '2016-09-26', 'deapamungkas', 0),
(3, 4, 'Iphone 5s Gold 32GB', 'Jual Iphone 5s warna Gold pemakaian 1 tahun\r\n\r\nSpec :\r\nRAM 1GB\r\nMemori 32GB\r\nwarna Gold\r\nno lecet, mulus.\r\nspek lengkap cek gsmarena gan\r\n\r\nfull set.', 3500000, 'iphone_5s.jpg', '2016-09-26', 'deapamungkas', 0),
(4, 4, 'Batik Turoquoise ', 'jual batik handmade turquoie.\r\n\r\nSize bisa menyesuaikan badan anda. jahitan mulus. ', 400000, 'batik_turquoise.jpg', '2016-09-26', 'deapamungkas', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `id` int(11) NOT NULL,
  `idProduk` int(11) DEFAULT NULL,
  `namaProduk` varchar(100) NOT NULL,
  `gambarProduk` varchar(100) NOT NULL,
  `idPembeli` int(11) DEFAULT NULL,
  `namaPembeli` varchar(50) DEFAULT NULL,
  `usernamePembeli` varchar(100) NOT NULL,
  `fullAddress` varchar(250) DEFAULT NULL,
  `postalCode` varchar(10) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `creditCard` varchar(30) DEFAULT NULL,
  `codeVerification` varchar(10) DEFAULT NULL,
  `tanggalDiBeli` date DEFAULT NULL,
  `kuantitas` int(11) DEFAULT NULL,
  `hargaSatuan` int(11) NOT NULL,
  `idPenjual` int(11) DEFAULT NULL,
  `usernamePenjual` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`id`, `idProduk`, `namaProduk`, `gambarProduk`, `idPembeli`, `namaPembeli`, `usernamePembeli`, `fullAddress`, `postalCode`, `phoneNumber`, `creditCard`, `codeVerification`, `tanggalDiBeli`, `kuantitas`, `hargaSatuan`, `idPenjual`, `usernamePenjual`) VALUES
(1, 1, 'Macbook Pro Retina Display', 'macbook_pro.jpg', 1, 'Ahmad Fajar Prasetiyo', 'fajar', 'Jl. Argowayang no 31 Pare Kediri Jawa Timur', '43211', '085312843102', '123456789021', '123', '2016-09-27', 1, 15000000, 4, 'deapamungkas'),
(2, 4, 'Batik Turoquoise', 'batik_turquoise.jpg', 1, 'Ahmad Fajar Prasetiyo', 'fajar', 'Jl. Argowayang no 31 Pare Kediri Jawa Timur', '43211', '085312843102', '123456789021', '123', '2016-09-27', 2, 400000, 4, 'deapamungkas'),
(3, 4, 'Batik Turoquoise', 'batik_turquoise.jpg', 1, 'Ahmad Fajar Prasetiyo', 'fajar', 'Jl. Argowayang no 31 Pare Kediri Jawa Timur', '43211', '085312843102', '123456789021', '123', '2016-10-08', 2, 0, 4, 'deapamungkas');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `liked`
--
ALTER TABLE `liked`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `liked`
--
ALTER TABLE `liked`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
