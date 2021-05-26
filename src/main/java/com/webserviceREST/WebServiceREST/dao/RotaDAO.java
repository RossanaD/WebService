package com.webserviceREST.WebServiceREST.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.webserviceREST.WebServiceREST.entity.Rota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RotaDAO {

	@Autowired
    private EntityManager entityManager;
	
	public List<Rota> getAllRotas(){
		try {
			String sql = "from " + Rota.class.getName();
			Query query = entityManager.createQuery(sql, Rota.class);
			return query.getResultList();
		}catch (NoResultException e) {
            return null;
        }				
	}
	
	public Rota getById(int id) {
		try {
			String sql = "Select r from "+ Rota.class.getName() + " r Where r.rotaId = :Rota_Id ";
			Query query = entityManager.createQuery(sql, Rota.class);
			query.setParameter("Rota_Id", id);
			return (Rota) query.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Rota> getPorPartida(String partida){
		try {
			String sql = "Select p from "+ Rota.class.getName() + " p Where p.partida= :Partida ";
			Query query = entityManager.createQuery(sql, Rota.class);
			query.setParameter("Partida", partida);
			return query.getResultList();
		}catch (NoResultException e) {
            return null;
        }				
	}
		
	public List<Rota> getPorDuracao(long duracao){
		try {
			String sql = "Select p from "+ Rota.class.getName() + " p Where p.duracao= :Duracao ";
			Query query = entityManager.createQuery(sql, Rota.class);
			query.setParameter("Duracao", duracao);
			return query.getResultList();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public void addRota(Rota rota) throws SQLException{
		try {
			entityManager.persist(rota);  
		} catch (EntityExistsException e) {
			// TODO: handle exception
		}		      
	}
	
	public void deleteRota(int id) {
		Rota rotaDele = getById(id);
		entityManager.remove(rotaDele);
	}
}
