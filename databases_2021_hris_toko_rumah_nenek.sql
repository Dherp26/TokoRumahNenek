-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 09, 2021 at 06:30 AM
-- Server version: 5.7.30
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `databases_2021_hris_toko_rumah_nenek`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_absensi`
--

CREATE TABLE `data_absensi` (
  `id_absensi` varchar(50) NOT NULL,
  `nip` varchar(50) NOT NULL,
  `bulan` varchar(50) NOT NULL,
  `tahun` varchar(50) NOT NULL,
  `jumlah_hadir` varchar(10) NOT NULL,
  `jumlah_sakit` varchar(100) NOT NULL,
  `jumlah_izin` varchar(100) NOT NULL,
  `jumlah_alfa` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_absensi`
--

INSERT INTO `data_absensi` (`id_absensi`, `nip`, `bulan`, `tahun`, `jumlah_hadir`, `jumlah_sakit`, `jumlah_izin`, `jumlah_alfa`) VALUES
('ABS2021199011914', '1234567890', 'januari', '2018', '25', '1', '2', '2');

-- --------------------------------------------------------

--
-- Table structure for table `data_admin`
--

CREATE TABLE `data_admin` (
  `id_admin` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_admin`
--

INSERT INTO `data_admin` (`id_admin`, `nama`, `username`, `password`) VALUES
('ADM2021142081449', 'andi', 'andi', 'andi'),
('ADM2021142081457', 'bayu', 'bayu', 'bayu'),
('ADM2021152081505', 'rido', 'rido', 'rido'),
('ADM2021162081653', 'test', 'test', 'test'),
('ADM2021320803281', 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `data_document_request`
--

CREATE TABLE `data_document_request` (
  `id_document_request` varchar(100) NOT NULL,
  `nip` varchar(100) NOT NULL,
  `tanggal` varchar(100) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_document_request`
--

INSERT INTO `data_document_request` (`id_document_request`, `nip`, `tanggal`, `keterangan`, `status`) VALUES
('DCR202124901244', '1234567890', '2021-05-09', 'KK Asli, Ijazah Asli', 'approve');

-- --------------------------------------------------------

--
-- Table structure for table `data_gaji`
--

CREATE TABLE `data_gaji` (
  `id_gaji` varchar(100) NOT NULL,
  `nip` varchar(100) NOT NULL,
  `bulan` varchar(100) NOT NULL,
  `tahun` varchar(100) NOT NULL,
  `total_gaji_pokok` varchar(100) NOT NULL,
  `total_gaji_overtime` varchar(100) NOT NULL,
  `total_potongan_gaji` varchar(100) NOT NULL,
  `total_gaji` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_gaji`
--

INSERT INTO `data_gaji` (`id_gaji`, `nip`, `bulan`, `tahun`, `total_gaji_pokok`, `total_gaji_overtime`, `total_potongan_gaji`, `total_gaji`) VALUES
('GJI2021219012124', '1234567890', 'januari', '2018', '1000000', '210000', '60000', '1150000');

-- --------------------------------------------------------

--
-- Table structure for table `data_leave`
--

CREATE TABLE `data_leave` (
  `id_leave` varchar(100) NOT NULL,
  `nip` varchar(100) NOT NULL,
  `tanggal` varchar(100) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_leave`
--

INSERT INTO `data_leave` (`id_leave`, `nip`, `tanggal`, `keterangan`, `status`) VALUES
('LVE2021239012342', '1234567890', '2021-05-09', 'Pindah Kantor, Tidak Betah', 'reject');

-- --------------------------------------------------------

--
-- Table structure for table `data_overtime`
--

CREATE TABLE `data_overtime` (
  `id_overtime` varchar(100) NOT NULL,
  `nip` varchar(100) NOT NULL,
  `bulan` varchar(100) NOT NULL,
  `tahun` varchar(100) NOT NULL,
  `jumlah_hari` varchar(100) NOT NULL,
  `biaya_overtime` varchar(100) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_overtime`
--

INSERT INTO `data_overtime` (`id_overtime`, `nip`, `bulan`, `tahun`, `jumlah_hari`, `biaya_overtime`, `keterangan`, `status`) VALUES
('OVR2021199011958', '1234567890', 'januari', '2018', '7', '30000', 'Lembur Event', 'approve');

-- --------------------------------------------------------

--
-- Table structure for table `data_pegawai`
--

CREATE TABLE `data_pegawai` (
  `id_pegawai` varchar(100) NOT NULL,
  `nip` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_telepon` varchar(100) NOT NULL,
  `jabatan` varchar(100) NOT NULL,
  `total_gaji_pokok` varchar(100) NOT NULL,
  `potongan_gaji_alfa` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_pegawai`
--

INSERT INTO `data_pegawai` (`id_pegawai`, `nip`, `nama`, `alamat`, `no_telepon`, `jabatan`, `total_gaji_pokok`, `potongan_gaji_alfa`, `username`, `password`) VALUES
('PGW2021179011715', '1234567890', 'Fajarudin Sidik', 'jambi', '085369237896', 'OB', '1000000', '30000', 'fajar', 'fajar');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_admin`
--
ALTER TABLE `data_admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `data_gaji`
--
ALTER TABLE `data_gaji`
  ADD PRIMARY KEY (`id_gaji`);

--
-- Indexes for table `data_overtime`
--
ALTER TABLE `data_overtime`
  ADD PRIMARY KEY (`id_overtime`);
