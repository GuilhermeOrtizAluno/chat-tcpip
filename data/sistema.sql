SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;

CREATE TABLE `candidato` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Senha` int(8) NOT NULL,
   PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `empresa` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(50) NOT NULL,
  `Senha` int(8) NOT NULL,
  `Descricao` varchar(50) NOT NULL,
  `CNPJ` varchar(14) NOT NULL,
  `Razao_Social` varchar(30) NOT NULL,
  `Ramo` varchar(30) NOT NULL,
   PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `competencia` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(50) NOT NULL,
   PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `vaga` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Faixa_salarial` int NOT NULL,
  `Descricao` varchar(50) NOT NULL,
  `Estado` varchar(30) NOT NULL,
  `EmpresaID` int(11) NOT NULL,
   PRIMARY KEY (`ID`),
   FOREIGN KEY (`EmpresaID`) REFERENCES `empresa`(`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `vaga_x_competencia` (
  `VagaID` int(11) NOT NULL,
  `CompetenciaID` int(11) NOT NULL,
  FOREIGN KEY (VagaID) REFERENCES vaga(ID),
  FOREIGN KEY (CompetenciaID) REFERENCES competencia(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `candidato_x_competencia` (
  `CandidatoID` int(11) NOT NULL,
  `CompetenciaID` int(11) NOT NULL,
  `Experiencia` int(11) NOT NULL,
  FOREIGN KEY (CandidatoID) REFERENCES candidato(ID),
  FOREIGN KEY (CompetenciaID) REFERENCES competencia(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `token` (
  `Token` varchar(50) NOT NULL,
  `CandidatoID` int(11),
  `EmpresaID` int(11),
   FOREIGN KEY (CandidatoID) REFERENCES candidato(ID),
   FOREIGN KEY (EmpresaID) REFERENCES empresa(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `competencia` (`Titulo`) VALUES
('Python'),('C#'),('C++'),('JS'),('PHP'),('Swift'),('Java'),('Go'),
('SQL'),('Ruby'),('HTML'),('CSS'),('NOSQL'),
('Flutter'), ('TypeScript'), ('Perl'), ('Cobol'), ('dotNet'), ('Kotlin'), ('Dart');

COMMIT;

