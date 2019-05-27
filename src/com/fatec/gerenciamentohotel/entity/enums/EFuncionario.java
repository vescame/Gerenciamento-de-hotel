package src.com.fatec.gerenciamentohotel.entity.enums;

public enum EFuncionario {
    ADMINISTRADOR("ADMINISTRADOR"),
    RECEPCIONISTA("RECEPCIONISTA"),
    SERVICO_DE_QUARTO("SERVICO_DE_QUARTO");

    public String role;

    EFuncionario(String role) {
        this.role = role;
    }
}
