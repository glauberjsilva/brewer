package com.glauber.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.glauber.brewer.model.Usuario;

@Controller
public class UsuariosController {
	
	@RequestMapping("/usuarios/novo")
	public String novo(Usuario usuario) {
		return "usuario/CadastroUsuario";
	}

}
