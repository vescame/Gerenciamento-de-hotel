package src.com.fatec.gerenciamentohotel.entity.enums;

public enum EFuncionario {
    ADMINISTRADOR("ADMINISTRADOR"),
    RECEPCIONISTA("RECEPCIONISTA");

    public String role;

    EFuncionario(String role) {
        this.role = role;
    }
}
