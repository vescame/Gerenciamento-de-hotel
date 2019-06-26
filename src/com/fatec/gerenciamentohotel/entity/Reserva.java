package com.fatec.gerenciamentohotel.entity;

import java.util.Date;

public class Reserva {
	/*
	 * id id_funcionario id_hospede id_num_quarto dat_checkin dat_checkout
	 * status
	 *
	 */
	private long id;
	private Date checkIn;
	private Date checkOut;
	private Hospede hospede;
	private Funcionario funcionario;
	private Quarto quarto;
	private char status;
	private float total;
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getStatus() {
		return this.status;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + "\ncheckIn=" + checkIn + "\ncheckOut="
				+ checkOut + "\nhospede=" + hospede + "\nfuncionario="
				+ funcionario + "\nquarto=" + quarto + "\nstatus=" + status
				+ "]";
	}

}
