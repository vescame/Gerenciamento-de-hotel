-- Create database
CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8 ;
USE `hotel` ;

-- -----------------------------------------------------
-- Table `hotel`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`endereco` (
  `cep` INT(8) NOT NULL,
  `rua` VARCHAR(45) NOT NULL,
  `numero` INT NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `uf` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cep`))
COMMENT = 'Tabela gerenciadora de endereços';


-- -----------------------------------------------------
-- Table `hotel`.`hospede`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`hospede` (
  `id` INT NOT NULL,
  `cep` INT(8) NOT NULL,
  `cpf` VARCHAR(55) NULL,
  `nome` VARCHAR(25) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `celular` VARCHAR(15) NULL,
  `email` VARCHAR(45) NULL,
  `dat_nascimento` DATE NOT NULL,
  `status` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  INDEX `fk_endereco_idh` (`cep` ASC),
  CONSTRAINT `fk_endereco_hospede`
    FOREIGN KEY (`cep`)
    REFERENCES `hotel`.`endereco` (`cep`))
COMMENT = 'Tabela gerenciadora de hóspedes';


-- -----------------------------------------------------
-- Table `hotel`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`funcionario` (
  `id` INT NOT NULL,
  `cep` INT(8) NOT NULL,
  `cpf` VARCHAR(55) NULL,
  `nome` VARCHAR(25) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `celular` VARCHAR(15) NULL,
  `email` VARCHAR(45) NULL,
  `dat_nascimento` DATE NOT NULL,
  `status` CHAR(1) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `tipo_funcionario` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  INDEX `fk_endereco_idf` (`cep` ASC),
  CONSTRAINT `fk_endereco_funcionario`
    FOREIGN KEY (`cep`)
    REFERENCES `hotel`.`endereco` (`cep`))
COMMENT = 'Tabela gerenciadora de funcionários';


-- -----------------------------------------------------
-- Table `hotel`.`tipo_quarto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`tipo_quarto` (
  `id` INT NOT NULL,
  `tipo_quarto` VARCHAR(45) NOT NULL,
  `preco` DECIMAL(6,2) NOT NULL,
  `qtd_adultos` INT NOT NULL,
  `qtd_criancas` INT NOT NULL,
  PRIMARY KEY (`id`))
COMMENT = 'Tabela gerenciadora de tipos de quartos, valores, quantidade de hóspedes';


-- -----------------------------------------------------
-- Table `hotel`.`quarto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`quarto` (
  `id` INT NOT NULL,
  `id_tipo_quarto` INT NOT NULL,
  `andar` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tipo_quarto_idx` (`id_tipo_quarto` ASC),
  CONSTRAINT `fk_tipo_quarto`
    FOREIGN KEY (`id_tipo_quarto`)
    REFERENCES `hotel`.`tipo_quarto` (`id`))
COMMENT = 'Tabela gerenciadora de quartos';


-- -----------------------------------------------------
-- Table `hotel`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`reserva` (
  `id` INT NOT NULL,
  `id_funcionario` INT NOT NULL,
  `id_hospede` INT NOT NULL,
  `id_quarto` INT NOT NULL,
  `dat_checkin` DATE NOT NULL,
  `dat_checkout` DATE NULL,
  `status` CHAR(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hospede_idx` (`id_hospede` ASC),
  INDEX `fk_funcionario_idx` (`id_funcionario` ASC),
  INDEX `fk_quarto_idx` (`id_quarto` ASC),
  CONSTRAINT `fk_hospede`
    FOREIGN KEY (`id_hospede`)
    REFERENCES `hotel`.`hospede` (`id`),
  CONSTRAINT `fk_funcionario`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `hotel`.`funcionario` (`id`),
  CONSTRAINT `fk_quarto`
    FOREIGN KEY (`id_quarto`)
    REFERENCES `hotel`.`quarto` (`id`))
COMMENT = 'Tabela gerenciadora de reservas';


-- -----------------------------------------------------
-- Table `hotel`.`item_servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`item_servico` (
  `id` INT NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `quantidade` INT NOT NULL,
  PRIMARY KEY (`id`))
COMMENT = 'Tabela gerenciadora de serviços e items';


-- -----------------------------------------------------
-- Table `hotel`.`servico_de_quarto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`servico_de_quarto` (
  `id` INT NOT NULL,
  `id_hospede` INT NOT NULL,
  `id_item_servico` INT NOT NULL,
  `dat_solicitacao` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hospede_idx` (`id_hospede` ASC),
  INDEX `fk_item_servico_id` (`id_item_servico` ASC),
  CONSTRAINT `fk_servico_hospede`
    FOREIGN KEY (`id_hospede`)
    REFERENCES `hotel`.`hospede` (`id`),
  CONSTRAINT `fk_item`
    FOREIGN KEY (`id_item_servico`)
    REFERENCES `hotel`.`item_servico` (`id`))
COMMENT = 'Tabela gerenciadora de serviços e items solicitados pelo hóspede';

