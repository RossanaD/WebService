package com.webserviceREST.WebServiceREST.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webserviceREST.WebServiceREST.dao.RotaDAO;
import com.webserviceREST.WebServiceREST.entity.Rota;


@Controller
public class RotaController {

	@Autowired
	private RotaDAO rotaDAO;
	
	@RequestMapping(value = "/getRotas", method = RequestMethod.GET)
	public String showRotasList(Model model) {
	    model.addAttribute("rotas", rotaDAO.getAllRotas());
	    return "getrotas";
	}
	
	@RequestMapping(value = "/getRotaId", method = RequestMethod.GET)
	public String showRotaCod(Model model, @RequestParam("codRota") String codigo) {
		model.addAttribute("Codigo", rotaDAO.getByCodigo(codigo));
		return "getRotaId";
	}
	
	@RequestMapping(value = "/postRota", method = RequestMethod.GET)
	public String postRota(Model model, Principal principal) {		
		return "postRota";
	}
	
	@RequestMapping(value = "/addrota", method = RequestMethod.POST)
	public String formRota(@ModelAttribute Rota rota) {
		rotaDAO.addRota(rota);
		return "redirect:/getRotas";
	}
	
	@RequestMapping(value = "/deleteRotaId")
	public String deleteRota(@RequestParam("delCodRota") String codigo) {
		rotaDAO.deleteRota(codigo);
		return "redirect:/getRotas";
	}
	
	@RequestMapping(value = "/updateRotaId", method = RequestMethod.GET)
	public String updateRota(Model model, @RequestParam("codRota") String codigo) {
		
	}
}
