package WebService.WebService.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Rota", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "ROTA_UK", columnNames = "Codigo") })
public class Rota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Rota_Id", nullable = false)
	private int rotaId;
	
	@Column(name = "Partida", nullable = false)
	private String partida;
	
	@Column(name = "Destino", nullable = false)
	private String destino;
	
	@Column(name = "Duracao", nullable = false)
	private Long duracao;
	
	@Column(name = "Codigo", nullable = false)
	private String codigo;
	
	@Transient
	private List<Object> avioes = new ArrayList<>();

	public int getRotaId() {
		return rotaId;
	}

	public void setRotaId(int rotaId) {
		this.rotaId = rotaId;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Long getDuracao() {
		return duracao;
	}

	public void setDuracao(Long duracao) {
		this.duracao = duracao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Object> getAvioes() {
		return avioes;
	}

	public void setAvioes(List<Object> avioes) {
		this.avioes = avioes;
	}
		
}
