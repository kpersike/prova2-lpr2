-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 06/12/2025 às 21:08
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `dbp2`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `tb_user`
--

CREATE TABLE `tb_user` (
  `cd_id` smallint(6) NOT NULL,
  `nm_nome` varchar(100) NOT NULL,
  `nr_idade` int(11) NOT NULL,
  `nr_peso` float NOT NULL,
  `nr_altura` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tb_user`
--

INSERT INTO `tb_user` (`cd_id`, `nm_nome`, `nr_idade`, `nr_peso`, `nr_altura`) VALUES
(1, 'Kaik', 22, 75, 1.8),
(2, 'Matheus', 20, 60, 1.75),
(3, 'Maria', 20, 70, 1.69),
(4, 'Joao', 30, 60, 1.9),
(5, 'José', 85, 90, 1.87);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`cd_id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `cd_id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
