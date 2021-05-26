package com.webserviceREST.WebServiceREST.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Viagem", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "VIAGEM_UK", columnNames = { "Rota_Id", "Aviao_Id" }) })
public class Viagem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Rota_Id", nullable = false)
    private Rota rota;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Aviao_Id", nullable = false)
    private Aviao aviao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public Aviao getAviao() {
		return aviao;
	}

	public void setAviao(Aviao aviao) {
		this.aviao = aviao;
	}
      
}
