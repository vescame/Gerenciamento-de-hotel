package com.fatec.gerenciamentohotel.entity;

public enum EFuncionario {
    ADMIN("Administrador"),
    RECEPCIONISTA("Recepcionista"),
    SERV_DE_QUARTO("Servico de Quarto");
    
    public String role;
    
    EFuncionario(String role) {
        this.role = role;
    }
}
