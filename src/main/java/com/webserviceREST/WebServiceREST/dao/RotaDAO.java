package com.webserviceREST.WebServiceREST.dao;

import java.util.List;

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
	
	public Rota getByCodigo(String codigo) {
		try {
			String sql = "Select r from "+ Rota.class.getName() + " r Where r.codigo = :Codigo ";
			Query query = entityManager.createQuery(sql, Rota.class);
			query.setParameter("Codigo", codigo);
			return (Rota) query.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
		
	public void addRota(Rota rota) {
		entityManager.persist(rota);        
	}
	
	public void deleteRota(String codigo) {
		Rota rotaDele = getByCodigo(codigo);
		entityManager.remove(rotaDele);
	}
}
