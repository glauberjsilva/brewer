package com.glauber.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.glauber.brewer.model.Cerveja;
import com.glauber.brewer.model.Origem;
import com.glauber.brewer.model.Sabor;
import com.glauber.brewer.repository.Estilos;
import com.glauber.brewer.service.CadastroCervejaService;

@Controller
public class CervejasController {

	@Autowired
	private Estilos estilos;

	@Autowired
	private CadastroCervejaService cadastroCervejaService;

	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		System.out.println(">>>>> Passou aqui NOVO");
		return mv;
	}

	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return novo(cerveja);
		}

		cadastroCervejaService.salvar(cerveja);
		redirectAttributes.addFlashAttribute("mensagem", "Cerveja salvo com sucesso");
		return new ModelAndView("redirect:/cervejas/novo");
	}
}