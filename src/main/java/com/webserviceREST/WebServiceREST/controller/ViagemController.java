package com.webserviceREST.WebServiceREST.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webserviceREST.WebServiceREST.dao.AviaoDAO;
import com.webserviceREST.WebServiceREST.dao.RotaDAO;
import com.webserviceREST.WebServiceREST.dao.ViagemDAO;
import com.webserviceREST.WebServiceREST.entity.Aviao;
import com.webserviceREST.WebServiceREST.entity.Rota;
import com.webserviceREST.WebServiceREST.entity.Viagem;

@Controller
public class ViagemController {
	
	@Autowired
	private AviaoDAO aviaoDAO;
	
	@Autowired
	private RotaDAO rotaDAO;
	
	@Autowired
	private ViagemDAO viagemDAO;
	
	@RequestMapping(value = "/formViagem", method = RequestMethod.GET)
	public String formViagem(Model model, Principal principal) {
		List<Rota> rotas = rotaDAO.getAllRotas();
		List<Aviao> avioes = aviaoDAO.getAllAvioes();
		model.addAttribute("rotas", rotas);
		model.addAttribute("avioes", avioes);
		return "formViagem";
	}
	
	@RequestMapping(value = "/addViagem", method = RequestMethod.POST)
	public String postViagem(@RequestParam("idRota") int idRota, @RequestParam("idAviao") int idAviao) {
		Rota rota = rotaDAO.getById(idRota);
		Aviao aviao = aviaoDAO.getAviaoById(idAviao);
		Viagem viagem = new Viagem();
		viagem.setRota(rota);
		viagem.setAviao(aviao);
		viagemDAO.addViagem(viagem);
		return "redirect:/formViagem";
	}
}
