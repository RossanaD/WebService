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
import com.webserviceREST.WebServiceREST.entity.Viagem;

@Repository
@Transactional
public class ViagemDAO {

	@Autowired
    private EntityManager entityManager;
	
	public void addViagem(Viagem viagem) {
		entityManager.persist(viagem);	
	}
}
