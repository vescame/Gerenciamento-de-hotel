package com.fatec.gerenciamentohotel.entity;

public enum EFuncionario {
    ADMIN("ADM"),
    RECEPCIONISTA("Recep"),
    SERV_DE_QUARTO("ServQuarto");

    public String role;

    EFuncionario(String role) {
        this.role = role;
    }
}
