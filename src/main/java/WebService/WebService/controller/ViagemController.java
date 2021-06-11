package WebService.WebService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import WebService.WebService.dao.AviaoDAO;
import WebService.WebService.dao.RotaDAO;
import WebService.WebService.dao.ViagemDAO;
import WebService.WebService.entity.Aviao;
import WebService.WebService.entity.Rota;
import WebService.WebService.entity.Viagem;

@RestController
public class ViagemController {

	@Autowired
	private AviaoDAO aviaoDAO;
	
	@Autowired
	private RotaDAO rotaDAO;
	
	@Autowired
	private ViagemDAO viagemDAO;
	
	
	  @PostMapping(value = "/addViagem", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}) 
	  public String postViagem(@RequestParam("idAviao") int idAviao, @RequestParam("idRota") int idRota) { 
		  Rota rota = rotaDAO.getById(idRota); 
		  Aviao aviao = aviaoDAO.getAviaoById(idAviao); 
		  Viagem viagem = new Viagem(); 
		  viagem.setRota(rota); 
		  viagem.setAviao(aviao);
		  viagemDAO.addViagem(viagem); 
	  return "Inserido com sucesso"; }
	 
	
	@GetMapping("/getViagem")
	public List<Viagem> getViagem() {
		return viagemDAO.getAllViagem();
	}
	
	@DeleteMapping("/deleteViagem/{id}")
	public String deleteViagem(@PathVariable int id) {
		viagemDAO.removeViagem(id);
		return "Viagem deletada";
	}
}
