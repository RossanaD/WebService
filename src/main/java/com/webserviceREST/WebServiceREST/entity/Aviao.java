package com.webserviceREST.WebServiceREST.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Aviao", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "AVIAO_UK", columnNames = "Nome") })
public class Aviao {

	@Id
    @GeneratedValue
    @Column(name = "Aviao_Id", nullable = false)
    private int aviaoId;
	
	@Column(name = "Nome", nullable = false)
	private String nome;
	
	@Column(name = "Modelo", nullable = false)
	private String modelo;
	
	@Column(name = "Companhia", nullable = false)
	private String companhia;
	
	@Column(name = "Qtd_Passageiros", nullable = false)
	private Long qtdPassageiros;
	
	@Column(name = "Qtd_Staff", nullable = false)
	private Long qtdStaff;
	
	@Column(name = "Dt_Fabricacao", nullable = false)
	private String dtFabricacao;
	
	@Column(name = "Qtd_Viagens_Feita", nullable = false)
	private Long qtdViagensFeita;

	public int getAviaoId() {
		return aviaoId;
	}

	public void setAviaoId(int aviaoId) {
		this.aviaoId = aviaoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCompanhia() {
		return companhia;
	}

	public void setCompanhia(String companhia) {
		this.companhia = companhia;
	}

	public Long getQtdPassageiros() {
		return qtdPassageiros;
	}

	public void setQtdPassageiros(Long qtdPassageiros) {
		this.qtdPassageiros = qtdPassageiros;
	}

	public Long getQtdStaff() {
		return qtdStaff;
	}

	public void setQtdStaff(Long qtdStaff) {
		this.qtdStaff = qtdStaff;
	}

	public String getDtFabricacao() {
		return dtFabricacao;
	}

	public void setDtFabricacao(String dtFabricacao) {
		this.dtFabricacao = dtFabricacao;
	}

	public Long getQtdViagensFeita() {
		return qtdViagensFeita;
	}

	public void setQtdViagensFeita(Long qtdViagensFeita) {
		this.qtdViagensFeita = qtdViagensFeita;
	}
		
}
