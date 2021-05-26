package com.webserviceREST.WebServiceREST.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webserviceREST.WebServiceREST.entity.Aviao;

@Repository
@Transactional
public class AviaoDAO {

	@Autowired
    private EntityManager entityManager;
	
	public List<Aviao> getAllAvioes(){
		try {
			String sql = "from " + Aviao.class.getName();
			Query query = entityManager.createQuery(sql, Aviao.class);
			return query.getResultList();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public Aviao getAviaoById(int id) {
		try {
			String sql = "Select a from "+ Aviao.class.getName()+" a Where a.aviaoId = :Aviao_Id ";
			Query query = entityManager.createQuery(sql, Aviao.class);
			query.setParameter("Aviao_Id", id);
			return (Aviao) query.getSingleResult();
		}catch (NoResultException n) {
			return null;
		}
	}
	
	public List<Aviao> getAllAvioesWithModelo(String modelo){
		try {
			String sql = "Select a from "+ Aviao.class.getName()+" a Where a.modelo = :Modelo ";
			Query query = entityManager.createQuery(sql, Aviao.class);
			query.setParameter("Modelo", modelo);
			return query.getResultList();
		}catch (NoResultException n) {
			return null;
		}
	}
	
	public List<Aviao> getAllAvioesWithCompanhia(String companhia){
		try {
			String sql = "Select c from "+Aviao.class.getName()+" c Where c.companhia = :Companhia ";
			Query query = entityManager.createQuery(sql, Aviao.class);
			query.setParameter("Companhia", companhia);
			return query.getResultList();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public void addAviao(Aviao aviao) {
		entityManager.persist(aviao);	
	}
	
	public void deleteAviao(int id) {
		Aviao aviao = getAviaoById(id);
		entityManager.remove(aviao);
	}
}
