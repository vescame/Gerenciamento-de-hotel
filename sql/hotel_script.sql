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
  `id` INT NOT NULL AUTO_INCREMENT,
  `cep` CHAR(8) NOT NULL,
  `cpf` VARCHAR(11) NULL,
  `nome` VARCHAR(35) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `celular` VARCHAR(15) NULL,
  `email` VARCHAR(35) NULL,
  `dat_nascimento` DATE NOT NULL,
  `status` CHAR(1) NOT NULL,
  `num_residencia` INT NOT NULL,
  PRIMARY KEY (`id`),
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `cep` CHAR(8) NOT NULL,
  `cpf` VARCHAR(11) NULL,
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
  PRIMARY KEY (`id`),
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
  `id_funcionario` INT NOT NULL,
  `id_hospede` INT NOT NULL,
  `id_num_quarto` INT NOT NULL,
  `dat_checkin` DATE NOT NULL,
  `dat_checkout` DATE NULL,
  `status` CHAR(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hospede_idx` (`id_hospede` ASC),
  INDEX `fk_funcionario_idx` (`id_funcionario` ASC),
  INDEX `fk_quarto_idx` (`id_num_quarto` ASC),
  CONSTRAINT `fk_hospede`
    FOREIGN KEY (`id_hospede`)
    REFERENCES `admin_hotel`.`hospede` (`id`),
  CONSTRAINT `fk_funcionario`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `admin_hotel`.`funcionario` (`id`),
  CONSTRAINT `fk_quarto`
    FOREIGN KEY (`id_num_quarto`)
    REFERENCES `admin_hotel`.`quarto` (`num_quarto`))
COMMENT = 'Tabela gerenciadora de reservas';


-- -----------------------------------------------------
-- Table `admin_hotel`.`item_servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admin_hotel`.`item_servico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`))
COMMENT = 'Tabela gerenciadora de serviços e items';


-- -----------------------------------------------------
-- Table `admin_hotel`.`servico_de_quarto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admin_hotel`.`servico_de_quarto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_hospede` INT NOT NULL,
  `id_funcionario` INT NOT NULL,
  `id_item_servico` INT NOT NULL,
  `qtd_solicitado` INT NULL,
  `dat_solicitacao` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hospede_serv_idx` (`id_hospede` ASC),
  INDEX `fk_funcionario_serv_idx` (`id_funcionario` ASC),
  INDEX `fk_item_servico_id` (`id_item_servico` ASC),
  CONSTRAINT `fk_servico_hospede`
    FOREIGN KEY (`id_hospede`)
    REFERENCES `admin_hotel`.`hospede` (`id`),
  CONSTRAINT `fk_servico_funcionario`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `admin_hotel`.`funcionario` (`id`),
  CONSTRAINT `fk_item`
    FOREIGN KEY (`id_item_servico`)
    REFERENCES `admin_hotel`.`item_servico` (`id`))
COMMENT = 'Tabela gerenciadora de serviços e items solicitados pelo hóspede';
