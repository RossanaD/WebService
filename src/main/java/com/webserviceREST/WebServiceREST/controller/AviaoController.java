package com.webserviceREST.WebServiceREST.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webserviceREST.WebServiceREST.dao.AviaoDAO;
import com.webserviceREST.WebServiceREST.entity.Aviao;

@Controller
public class AviaoController {
	
	@Autowired
	private AviaoDAO aviaoDAO;
	
	@RequestMapping(value = "/pesquisaAviao", method = RequestMethod.GET)
	public String pesquisaAviao(Model model) {
		return "pesquisaAviao";
	}
	
	
	@RequestMapping(value = "/getAvioes", method = RequestMethod.GET)
	public String showAvioesList(Model model) {
	    model.addAttribute("avioes", aviaoDAO.getAllAvioes());
	    return "getavioes";
	}
	
	@RequestMapping(value = "/getAviaoById", method = RequestMethod.GET)
	public String showRotaCod(Model model, @RequestParam("idAviao") int id) {
		Aviao aviao = aviaoDAO.getAviaoById(id);
		if(aviao == null)
			return "NotFoundAviao";
		model.addAttribute("aviao", aviao);
		return "getAviaoId";
	}
	
	@RequestMapping(value = "/getAvioesWithModelo", method = RequestMethod.GET)
	public String showWithModelo(Model model, @RequestParam("modAviao") String modelo) {
		List<Aviao> avi = aviaoDAO.getAllAvioesWithModelo(modelo);
		if(avi.isEmpty())
			return "NotFoundAviao";
		model.addAttribute("avioes", avi);
		model.addAttribute("modelo", modelo);
		return "getAviaoModel";
	}
	
	@RequestMapping(value = "/getAvioesWithCompanhia", method = RequestMethod.GET)
	public String showWithCompanhia(Model model, @RequestParam("CompanhiaAviao") String companhia) {
		List<Aviao> avi = aviaoDAO.getAllAvioesWithCompanhia(companhia);
		if(avi.isEmpty())
			return "NotFoundAviao";
		model.addAttribute("avioes", avi);
		model.addAttribute("companhia", companhia);
		return "getAviaoCompanhia";
	}
	
	@RequestMapping(value = "/postAviao", method = RequestMethod.GET)
	public String postAviao(Model model, Principal principal) {		
		return "postAviao";
	}
	
	@RequestMapping(value = "/addAviao", method = RequestMethod.POST)
	public String formRota(@ModelAttribute Aviao aviao) {
		aviaoDAO.addAviao(aviao);
		return "redirect:/getAvioes";
	}
	
	//
	@RequestMapping(value = "/deleteAviao")
	public String deleteRota(@RequestParam("delIdAviao") int id) {
		aviaoDAO.deleteAviao(id);
		return "redirect:/getAvioes";
	}
}
