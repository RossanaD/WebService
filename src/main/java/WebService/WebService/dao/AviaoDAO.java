package WebService.WebService.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import WebService.WebService.entity.Aviao;
import WebService.WebService.entity.Rota;
import WebService.WebService.entity.Viagem;



@Repository
@Transactional
public class AviaoDAO {

	@Autowired
    private EntityManager entityManager;
	
	public List<Aviao> getAllAvioes(){
		try {
			String sql = "from " + Aviao.class.getName();
			Query query = entityManager.createQuery(sql, Aviao.class);
			List<Aviao> avioes = query.getResultList();
			for (Aviao aviao : avioes) {
				String sql1 = "Select ur.rota.partida, ur.rota.destino, ur.rota.duracao from "
			+Viagem.class.getName() +" ur where ur.aviao.aviaoId = :aviaoId";
				Query query1 = entityManager.createQuery(sql1);
				query1.setParameter("aviaoId", aviao.getAviaoId());
				List<Object> rotas = query1.getResultList();
				if(rotas.isEmpty())
					continue;
				aviao.setRotas(rotas);
			}
			return avioes;
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public Aviao getAviaoById(int id) {
		try {
			String sql = "Select a from "+ Aviao.class.getName()+" a Where a.aviaoId = :Aviao_Id ";
			Query query = entityManager.createQuery(sql, Aviao.class);
			query.setParameter("Aviao_Id", id);
			Aviao aviao = (Aviao) query.getSingleResult();
			String sql1 = "Select ur.rota.partida, ur.rota.destino, ur.rota.duracao from "
					+Viagem.class.getName() +" ur where ur.aviao.aviaoId = :aviaoId";
			Query query1 = entityManager.createQuery(sql1);
			query1.setParameter("aviaoId", aviao.getAviaoId());
			List<Object> rotas = query1.getResultList();
			if(rotas.isEmpty())
				return aviao;
			
			aviao.setRotas(rotas);			
			return aviao;
		}catch (NoResultException n) {
			return null;
		}
	}
	
	public List<Aviao> getAllAvioesWithModelo(String modelo){
		try {
			String sql = "Select a from "+ Aviao.class.getName()+" a Where a.modelo = :Modelo ";
			Query query = entityManager.createQuery(sql, Aviao.class);
			query.setParameter("Modelo", modelo);
			List<Aviao> avioes = query.getResultList();
			for (Aviao aviao : avioes) {
				String sql1 = "Select ur.rota.partida, ur.rota.destino, ur.rota.duracao from "
			+Viagem.class.getName() +" ur where ur.aviao.aviaoId = :aviaoId";
				Query query1 = entityManager.createQuery(sql1);
				query1.setParameter("aviaoId", aviao.getAviaoId());
				List<Object> rotas = query1.getResultList();
				if(rotas.isEmpty())
					continue;
				aviao.setRotas(rotas);
			}
			return avioes;
		}catch (NoResultException n) {
			return null;
		}
	}
	
	public List<Aviao> getAllAvioesWithCompanhia(String companhia){
		try {
			String sql = "Select c from "+Aviao.class.getName()+" c Where c.companhia = :Companhia ";
			Query query = entityManager.createQuery(sql, Aviao.class);
			query.setParameter("Companhia", companhia);
			List<Aviao> avioes = query.getResultList();
			for (Aviao aviao : avioes) {
				String sql1 = "Select ur.rota.partida, ur.rota.destino, ur.rota.duracao from "
			+Viagem.class.getName() +" ur where ur.aviao.aviaoId = :aviaoId";
				Query query1 = entityManager.createQuery(sql1);
				query1.setParameter("aviaoId", aviao.getAviaoId());
				List<Object> rotas = query1.getResultList();
				if(rotas.isEmpty())
					continue;
				aviao.setRotas(rotas);
			}
			return avioes;
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public Aviao addAviao(Aviao aviao) {
		entityManager.persist(aviao);	
		return aviao;
	}
	
	public void updateAviao(Aviao aviao, int id) {
		Aviao avi = getAviaoById(id);
		avi.setNome(avi.getNome());
		avi.setModelo(aviao.getModelo());
		avi.setCompanhia(aviao.getCompanhia());
		avi.setQtdPassageiros(aviao.getQtdPassageiros());
		avi.setQtdStaff(aviao.getQtdStaff());
		avi.setDtFabricacao(aviao.getDtFabricacao());
		avi.setQtdViagensFeita(aviao.getQtdViagensFeita());
		entityManager.merge(avi);
	}
	
	public void deleteAviao(int id) {
		Aviao aviao = getAviaoById(id);
		entityManager.remove(aviao);
	}
}
