-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 31, 2020 at 01:31 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lit_art`
--

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `course_id` varchar(11) NOT NULL,
  `course_name` varchar(20) NOT NULL,
  `course_level` set('Level 6','Level 7','Level 8','Level 9','Level 10') NOT NULL,
  `course_length` set('1 Year','2 Years','3 Years','4 Years','5 Years') NOT NULL,
  `course_description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `project_id` int(11) NOT NULL,
  `project_name` varchar(100) NOT NULL,
  `project_description` varchar(255) NOT NULL,
  `project_body` varchar(3000) NOT NULL,
  `project_image` varchar(50) NOT NULL,
  `video_link` varchar(255) DEFAULT NULL,
  `student_id` int(11) NOT NULL,
  `show_id` int(11) DEFAULT NULL,
  `project_price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`project_id`, `project_name`, `project_description`, `project_body`, `project_image`, `video_link`, `student_id`, `show_id`, `project_price`) VALUES
(1, 'Techfinity', 'A website done in CodeIgniter in second year', 'Full description of project', 'city.jpg', NULL, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `show`
--

CREATE TABLE `show` (
  `show_id` int(11) NOT NULL,
  `show_name` int(11) NOT NULL,
  `start_date` int(11) NOT NULL,
  `end_date` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `account_type` int(1) NOT NULL,
  `full_name` text NOT NULL,
  `email` varchar(40) NOT NULL,
  `k_number` varchar(9) NOT NULL,
  `contact_number` int(10) NOT NULL,
  `profile_image` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `course_code` varchar(11) NOT NULL,
  `course_year` varchar(5) NOT NULL,
  `profile_description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `account_type`, `full_name`, `email`, `k_number`, `contact_number`, `profile_image`, `password`, `course_code`, `course_year`, `profile_description`) VALUES
(1, 1, 'Marek Czerniejewski', 'mczerniejewski@o2.pl', 'k00231569', 851212123, 'profilePlaceholder.png', 'Marek1995', 'LM234', '3rd', 'Some description'),
(2, 1, 'Joe Bloggs', 'joe@bloggs.com', 'k00123456', 851212123, 'profile.jpg', 'joe123', 'LM234', '3rd', ''),
(3, 1, 'Full Name', 'fullname@gmail.com', 'k00112233', 851234567, 'profile.jpg', 'full123', 'LM235', '3rd', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`);

--
-- Indexes for table `show`
--
ALTER TABLE `show`
  ADD PRIMARY KEY (`show_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `show`
--
ALTER TABLE `show`
  MODIFY `show_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
