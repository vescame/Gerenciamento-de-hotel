-- Create database
CREATE SCHEMA IF NOT EXISTS `admin_hotel` DEFAULT CHARACTER SET utf8 ;
USE `admin_hotel` ;

-- -----------------------------------------------------
-- Table `admin_hotel`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admin_hotel`.`endereco` (
  `cep` CHAR(8) NOT NULL,
  `rua` VARCHAR(50) NOT NULL,
  `bairro` VARCHAR(50) NOT NULL,
  `cidade` VARCHAR(50) NOT NULL,
  `uf` CHAR(2) NOT NULL,
  PRIMARY KEY (`cep`))
COMMENT = 'Tabela gerenciadora de endereços';


-- -----------------------------------------------------
-- Table `admin_hotel`.`hospede`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admin_hotel`.`hospede` (
  `cpf` VARCHAR(11) NOT NULL,
  `cep` CHAR(8) NOT NULL,
  `nome` VARCHAR(35) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `celular` VARCHAR(15) NULL,
  `email` VARCHAR(35) NULL,
  `dat_nascimento` DATE NOT NULL,
  `status` CHAR(1) NOT NULL,
  `num_residencia` INT NOT NULL,
  PRIMARY KEY (`cpf`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  INDEX `fk_endereco_idh` (`cep` ASC),
  CONSTRAINT `fk_endereco_hospede`
    FOREIGN KEY (`cep`)
    REFERENCES `admin_hotel`.`endereco` (`cep`))
COMMENT = 'Tabela gerenciadora de hóspedes';


-- -----------------------------------------------------
-- Table `admin_hotel`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admin_hotel`.`funcionario` (
  `cpf` VARCHAR(11) NOT NULL,
  `cep` CHAR(8) NOT NULL,
  `nome` VARCHAR(35) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `celular` VARCHAR(15) NULL,
  `email` VARCHAR(35) NULL,
  `dat_nascimento` DATE NOT NULL,
  `status` CHAR(1) NOT NULL,
  `login` VARCHAR(15) NOT NULL,
  `senha` VARCHAR(15) NOT NULL,
  `tipo_funcionario` VARCHAR(25) NOT NULL,
  `num_residencia` INT NOT NULL,
  PRIMARY KEY (`cpf`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  INDEX `fk_endereco_idf` (`cep` ASC),
  CONSTRAINT `fk_endereco_funcionario`
    FOREIGN KEY (`cep`)
    REFERENCES `admin_hotel`.`endereco` (`cep`))
COMMENT = 'Tabela gerenciadora de funcionários';


-- -----------------------------------------------------
-- Table `admin_hotel`.`tipo_quarto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admin_hotel`.`tipo_quarto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_quarto` VARCHAR(25) NOT NULL,
  `preco` DECIMAL(6,2) NOT NULL,
  `qtd_adultos` INT NOT NULL,
  `qtd_criancas` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tipo_quarto_UNIQUE` (`tipo_quarto` ASC))
COMMENT = 'Tabela gerenciadora de tipos de quartos, valores, quantidade de hóspedes';


-- -----------------------------------------------------
-- Table `admin_hotel`.`quarto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admin_hotel`.`quarto` (
  `num_quarto` INT NOT NULL,
  `id_tipo_quarto` INT NOT NULL,
  `andar` INT NOT NULL,
  PRIMARY KEY (`num_quarto`),
  INDEX `fk_tipo_quarto_idx` (`id_tipo_quarto` ASC),
  CONSTRAINT `fk_tipo_quarto`
    FOREIGN KEY (`id_tipo_quarto`)
    REFERENCES `admin_hotel`.`tipo_quarto` (`id`))
COMMENT = 'Tabela gerenciadora de quartos';


-- -----------------------------------------------------
-- Table `admin_hotel`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admin_hotel`.`reserva` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cpf_funcionario` VARCHAR(11) NOT NULL,
  `cpf_hospede` VARCHAR(11) NOT NULL,
  `num_quarto` INT NOT NULL,
  `dat_checkin` DATE NOT NULL,
  `dat_checkout` DATE NULL,
  `status` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hospede_idx` (`cpf_hospede` ASC),
  INDEX `fk_funcionario_idx` (`cpf_funcionario` ASC),
  INDEX `fk_quarto_idx` (`num_quarto` ASC),
  CONSTRAINT `fk_hospede`
    FOREIGN KEY (`cpf_hospede`)
    REFERENCES `admin_hotel`.`hospede` (`cpf`),
  CONSTRAINT `fk_funcionario`
    FOREIGN KEY (`cpf_funcionario`)
    REFERENCES `admin_hotel`.`funcionario` (`cpf`),
  CONSTRAINT `fk_quarto`
    FOREIGN KEY (`num_quarto`)
    REFERENCES `admin_hotel`.`quarto` (`num_quarto`))
COMMENT = 'Tabela gerenciadora de reservas';

insert into `endereco` values (
"03030300",
"Rua Mock",
"Mockadinho",
"Mock City",
"MK"
);

insert into `funcionario` values (
"41131121111",
"03030300",
"Funcionario Administrador",
"45450363",
null,
"admin@debian",
"1996-01-11",
"A",
"admin",
"admin",
"ADMINISTRADOR",
92
);
