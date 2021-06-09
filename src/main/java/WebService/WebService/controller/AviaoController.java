package WebService.WebService.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import WebService.WebService.dao.AviaoDAO;
import WebService.WebService.entity.Aviao;


@RestController
public class AviaoController {
	
	@Autowired
	private AviaoDAO aviaoDAO;
	
	/*
	 * @RequestMapping(value = "/pesquisaAviao", method = RequestMethod.GET) public
	 * String pesquisaAviao(Model model) { return "pesquisaAviao"; }
	 */
	
	
	@GetMapping("/getAvioes")
	public List<Aviao> showAvioesList() {
	    return aviaoDAO.getAllAvioes();
	}
	
	@GetMapping("/getAviaoById/{id}")
	public Aviao showRotaCod(@PathVariable int id) {
		Aviao aviao = aviaoDAO.getAviaoById(id);
		if(aviao == null)
			return null;
		return aviao;
	}
	
	@GetMapping("/getAvioesWithModelo/{modelo}")
	public List<Aviao> showWithModelo(@PathVariable String modelo) {
		List<Aviao> avi = aviaoDAO.getAllAvioesWithModelo(modelo);
		if(avi.isEmpty())
			return null;
		return avi;
	}
	
	@GetMapping("/getAvioesWithCompanhia/{companhia}")
	public List<Aviao> showWithCompanhia(@PathVariable String companhia) {
		List<Aviao> avi = aviaoDAO.getAllAvioesWithCompanhia(companhia);
		if(avi.isEmpty())
			return null;
		return avi;
	}
	
	/*
	 * @RequestMapping(value = "/postAviao", method = RequestMethod.GET) public
	 * String postAviao(Model model, Principal principal) { return "postAviao"; }
	 */
	
	@PostMapping("/addAviao")
	public Aviao addAviao(@RequestBody Aviao aviao, Principal principal) {
		return aviaoDAO.addAviao(aviao);	
	}
	
	@PutMapping("/updateAviao/{id}")
	public void UpdateAviao(@RequestBody Aviao aviao, @PathVariable int id, Principal principal) {		
		aviaoDAO.updateAviao(aviao, id);
	}
	
	@DeleteMapping("/deleteAviao/{id}")
	public void deleteRota(@PathVariable int id) {
		aviaoDAO.deleteAviao(id);		
	}
}
