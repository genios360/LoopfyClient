package br.com.ezpet.nimbus21.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ezpet.nimbus21.domain.Produto;
import br.com.ezpet.nimbus21.service.UsuarioAdminService;

@Controller
public class UsuarioAdminController {
	
	@Autowired
	UsuarioAdminService usuarioAdminService;
	
	@GetMapping("/cadastrarProduto")
	public String cadastroProduto(Model model) {
		model.addAttribute("usuariosComercial", usuarioAdminService.findAllComercial());
		return "Cadastro.Produto";
	}
	
	@PostMapping("/cadastrarProduto")
	public String postCadastroProduto(Produto produto, RedirectAttributes redirectAttributes) {

		usuarioAdminService.postProduto(produto);
		redirectAttributes.addFlashAttribute("msg1", String.format("Produto \"%s\" cadastrado com sucesso!", produto.getNome()));
		return "redirect:/cadastrarProduto";
	}
}
