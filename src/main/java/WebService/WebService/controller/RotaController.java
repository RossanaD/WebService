package WebService.WebService.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import WebService.WebService.dao.RotaDAO;
import WebService.WebService.entity.Aviao;
import WebService.WebService.entity.Rota;
import WebService.WebService.utils.WebUtils;
import sun.net.ftp.FtpProtocolException;


@RestController
public class RotaController {

	@Autowired
	private RotaDAO rotaDAO;
	
	@GetMapping("/getRotas")
	public List<Rota> RotasList() {
	    return rotaDAO.getAllRotas();
	}
	
	@GetMapping("/getRota/{id}")
	public Rota RotaCod(@PathVariable int id) {
		return rotaDAO.getById(id);
	}
	
	@GetMapping("/getRotaPartida/{partida}")
	public List<Rota> RotaByPartida(@PathVariable String partida) {
		List<Rota> rota = rotaDAO.getPorPartida(partida);
		if(rota.isEmpty())
			return null;
		return rota;
	}
	
	@GetMapping("/getRotaDuracao/{duracao}")
	public List<Rota> RotaByDuracao(@PathVariable long duracao) {
		List<Rota> rota = rotaDAO.getPorDuracao(duracao);
		if(rota.isEmpty())
			return null;
		return rota;
	}
	
	@PostMapping("/postRota")
	public Rota postRota(@RequestBody Rota rota, Principal principal) {			
			return rotaDAO.addRota(rota);
	}
	
	@PutMapping("/updateRota/{id}")
	public void updateRota(@RequestBody Rota rota, @PathVariable int id, Principal principal) {
		rotaDAO.updateRota(rota, id);
	}
	
	@DeleteMapping("/deleteRota/{id}")
	public void deleteRota(@PathVariable int id) {
		rotaDAO.deleteRota(id);
	}
	
}
