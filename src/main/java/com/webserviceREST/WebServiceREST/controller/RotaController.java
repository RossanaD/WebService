package com.webserviceREST.WebServiceREST.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

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
	
	@RequestMapping(value = "/buscarRota", method = RequestMethod.GET)
	public String buscaRota(Model model) {
		return "buscaRota";
	}
	
	@RequestMapping(value = "/getRotas", method = RequestMethod.GET)
	public String showRotasList(Model model) {
	    model.addAttribute("rotas", rotaDAO.getAllRotas());
	    return "getrotas";
	}
	
	@RequestMapping(value = "/getRotaId", method = RequestMethod.GET)
	public String showRotaCod(Model model, @RequestParam("idRota") int id) {
		model.addAttribute("Codigo", rotaDAO.getById(id));
		return "getRotaId";
	}
	
	@RequestMapping(value = "/getRotaByPartida", method = RequestMethod.GET)
	public String showRotaByPartida(Model model, @RequestParam("partida") String partida) {
		List<Rota> rota = rotaDAO.getPorPartida(partida);
		if(rota.isEmpty())
			throw new IllegalArgumentException("Não existe rota com essa partida");
		model.addAttribute("rotas", rota);
		model.addAttribute("partida", partida);
		return "getRotaPartida";
	}
	
	@RequestMapping(value = "/getRotaByDuracao", method = RequestMethod.GET)
	public String showRotaByDuracao(Model model, @RequestParam("duracaoRota") long duracao) {
		List<Rota> rota = rotaDAO.getPorDuracao(duracao);
		if(rota.isEmpty())
			throw new IllegalArgumentException("Não existe rota com essa duração");
		model.addAttribute("rotas", rota);
		model.addAttribute("duracao", duracao);
		return "getRotaDuracao";
	}
	
	//Aqui chama o form, pra inserir os valores
	@RequestMapping(value = "/postRota", method = RequestMethod.GET)
	public String postRota(Model model, Principal principal) {		
		return "postRota";
	}
	
	//Quando chama esse request, pega os dados do form, é joga
	//no objeto
	@RequestMapping(value = "/addrota", method = RequestMethod.POST)
	public String formRota(@ModelAttribute Rota rota) {
		try {
			rotaDAO.addRota(rota);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		return "redirect:/getRotas";
	}
	
	//
	@RequestMapping(value = "/deleteRotaId")
	public String deleteRota(@RequestParam("delIdRota") int id) {
		rotaDAO.deleteRota(id);
		return "redirect:/getRotas";
	}
	
	
	//Atualizar
	@RequestMapping(value = "/updateRotaId", method = RequestMethod.GET)
	public String updateRota(Model model, @RequestParam("updCodRota") int id) {
		model.addAttribute("ROTA", rotaDAO.getById(id));
		return "updateRota";
	}
}
