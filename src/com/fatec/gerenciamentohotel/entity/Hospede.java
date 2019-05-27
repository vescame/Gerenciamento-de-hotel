package src.com.fatec.gerenciamentohotel.entity;

import java.util.ArrayList;
import java.util.List;

public class Hospede extends Pessoa {
	/*
	 * id
	 * cep
	 * cpf
	 * nome
	 * telefone
	 * celular
	 * email
	 * dat_nascimento
	 * status
	 * num_residencia 
	 */
    private Reserva reserva;
    private List<ItemServico> itemsServicos  = new ArrayList<>();
    private List<Reserva> historicoReserva = new ArrayList<>();

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<ItemServico> getItemsServicos() {
        return itemsServicos;
    }

    public void setItemsServicos(List<ItemServico> itemsServicos) {
        this.itemsServicos = itemsServicos;
    }

	public List<Reserva> getHistoricoReserva() {
		return historicoReserva;
	}

	public void setHistoricoReserva(List<Reserva> historicoQuartos) {
		this.historicoReserva = historicoQuartos;
	}

	@Override
	public String toString() {
		return "Hospede [reserva=" + reserva + "\nitemsServicos=" + itemsServicos + "\nhistoricoReserva="
				+ historicoReserva + "\ngetId()=" + getId() + "\ngetEndereco()=" + getEndereco() + "\ngetCpf()="
				+ getCpf() + "\ngetNome()=" + getNome() + "\ngetTelefone()=" + getTelefone() + "\ngetCelular()="
				+ getCelular() + "\ngetEmail()=" + getEmail() + "\ngetDataNascimento()=" + getDataNascimento()
				+ "\ngetStatus()=" + getStatus() + "\ngetClass()=" + getClass() + "\nhashCode()=" + hashCode()
				+ "\ntoString()=" + super.toString() + "]";
	}

}
