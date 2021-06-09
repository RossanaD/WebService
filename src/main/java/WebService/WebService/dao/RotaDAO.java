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

import WebService.WebService.entity.Rota;
import WebService.WebService.entity.Viagem;

@Repository
@Transactional
public class RotaDAO {

	@Autowired
    private EntityManager entityManager;
	
	public List<Rota> getAllRotas(){
		try {
			String sql = "from " + Rota.class.getName();
			Query query = entityManager.createQuery(sql, Rota.class);
			 List<Rota> rotas = query.getResultList();
			 for (Rota rota : rotas) {
				 String sql1 = "Select ur.aviao.nome, ur.aviao.modelo, ur.aviao.companhia from "
						 +Viagem.class.getName() +" ur where ur.rota.rotaId = :rotaId";
				Query query1 = entityManager.createQuery(sql1);
				query1.setParameter("rotaId", rota.getRotaId());
				List<Object> avioes = query1.getResultList();
				if(avioes.isEmpty())
					continue;
				rota.setAvioes(avioes);
			}
			 return rotas;
		}catch (NoResultException e) {
            return null;
        }				
	}
	
	public Rota getById(int id) {
		try {
			String sql = "Select r from "+ Rota.class.getName() + " r Where r.rotaId = :Rota_Id ";
			Query query = entityManager.createQuery(sql, Rota.class);
			query.setParameter("Rota_Id", id);
			Rota rota = (Rota) query.getSingleResult();
			String sql1 = "Select ur.aviao.nome, ur.aviao.modelo, ur.aviao.companhia from "
					 +Viagem.class.getName() +" ur where ur.rota.rotaId = :rotaId";
			Query query1 = entityManager.createQuery(sql1);
			query1.setParameter("rotaId", rota.getRotaId());
			List<Object> avioes = query1.getResultList();
			if(avioes.isEmpty())
				return rota;
			rota.setAvioes(avioes);
			return rota;
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Rota> getPorPartida(String partida){
		try {
			String sql = "Select p from "+ Rota.class.getName() + " p Where p.partida= :Partida ";
			Query query = entityManager.createQuery(sql, Rota.class);
			query.setParameter("Partida", partida);
			List<Rota> rotas = query.getResultList();
			 for (Rota rota : rotas) {
				 String sql1 = "Select ur.aviao.nome, ur.aviao.modelo, ur.aviao.companhia from "
						 +Viagem.class.getName() +" ur where ur.rota.rotaId = :rotaId";
				Query query1 = entityManager.createQuery(sql1);
				query1.setParameter("rotaId", rota.getRotaId());
				List<Object> avioes = query1.getResultList();
				if(avioes.isEmpty())
					continue;
				rota.setAvioes(avioes);
			}
			 return rotas;
		}catch (NoResultException e) {
            return null;
        }				
	}
		
	public List<Rota> getPorDuracao(long duracao){
		try {
			String sql = "Select p from "+ Rota.class.getName() + " p Where p.duracao= :Duracao ";
			Query query = entityManager.createQuery(sql, Rota.class);
			query.setParameter("Duracao", duracao);
			List<Rota> rotas = query.getResultList();
			 for (Rota rota : rotas) {
				 String sql1 = "Select ur.aviao.nome, ur.aviao.modelo, ur.aviao.companhia from "
						 +Viagem.class.getName() +" ur where ur.rota.rotaId = :rotaId";
				Query query1 = entityManager.createQuery(sql1);
				query1.setParameter("rotaId", rota.getRotaId());
				List<Object> avioes = query1.getResultList();
				if(avioes.isEmpty())
					continue;
				rota.setAvioes(avioes);
			}
			 return rotas;
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public Rota addRota(Rota rota) {
		entityManager.persist(rota); 
		return rota;	      
	}
	
	public void updateRota(Rota rota, int id) {
		Rota rot = getById(id);
		rot.setPartida(rota.getPartida());
		rot.setDuracao(rota.getDuracao());
		rot.setDestino(rota.getDestino());
		rot.setCodigo(rota.getCodigo());
		entityManager.merge(rot);
	}
	
	public void deleteRota(int id) {
		Rota rotaDele = getById(id);
		entityManager.remove(rotaDele);
	}
}
