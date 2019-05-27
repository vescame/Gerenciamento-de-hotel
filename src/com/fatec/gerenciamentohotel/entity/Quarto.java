package src.com.fatec.gerenciamentohotel.entity;

public class Quarto {
    private long id;
    private TipoDeQuarto tipoDeQuarto;
    private short andar;
    private boolean status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TipoDeQuarto getTipoDeQuarto() {
        return tipoDeQuarto;
    }

    public void setTipoDeQuarto(TipoDeQuarto tipoDeQuarto) {
        this.tipoDeQuarto = tipoDeQuarto;
    }

    public short getAndar() {
        return andar;
    }

    public void setAndar(short andar) {
        this.andar = andar;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStaus(boolean status) {
        this.status = status;
    }

}
