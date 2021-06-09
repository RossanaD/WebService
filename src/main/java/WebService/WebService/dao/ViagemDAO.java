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

import WebService.WebService.entity.Viagem;


@Repository
@Transactional
public class ViagemDAO {

	@Autowired
    private EntityManager entityManager;
	
	public Viagem getViagemById(int id) {
		try {
			String sql = "Select v from "+Viagem.class.getName()+" v Where v.id = :Id";
			Query query = entityManager.createQuery(sql,Viagem.class);
			query.setParameter("Id", id);
			return (Viagem) query.getSingleResult();
		}catch (NoResultException n) {
			return null;
		}
	}
	
	public List<Viagem> getAllViagem(){
		String sql = "from "+Viagem.class.getName();
		Query query = entityManager.createQuery(sql,Viagem.class);
		return query.getResultList();
	}
	
	public void addViagem(Viagem viagem) {
		entityManager.persist(viagem);	
	}
	
	public void removeViagem(int id) {
		Viagem viagem = getViagemById(id);
		entityManager.remove(viagem);
	}
}
