package com.webserviceREST.WebServiceREST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webserviceREST.WebServiceREST.dao.RotaDAO;


@Controller
public class RotaController {

	@Autowired
	private RotaDAO rotaDAO;
	
	@RequestMapping(value = "/getRotas", method = RequestMethod.GET)
	public String showRotasList(Model model) {
	    model.addAttribute("rotas", rotaDAO.getAllRotas());
	    return "getrotas";
	}
}
