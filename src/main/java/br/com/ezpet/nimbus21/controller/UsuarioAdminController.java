package br.com.ezpet.nimbus21.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ezpet.nimbus21.domain.Produto;
import br.com.ezpet.nimbus21.domain.UsuarioColaborador;
import br.com.ezpet.nimbus21.domain.UsuarioComercial;
import br.com.ezpet.nimbus21.service.UsuarioAdminService;

@Controller
@RequestMapping("/admin")
public class UsuarioAdminController {
	
//	private final String UPLOAD_DIR = "./uploads/";
	
	@Autowired
	UsuarioAdminService usuarioAdminService;
	
	@GetMapping("/cadastrarProduto")
	public String cadastroProduto(Model model) {
		model.addAttribute("usuariosComercial", usuarioAdminService.findAllComercial());
		return "Cadastro.Produto";
	}
	
	@PostMapping("/cadastrarProduto")
	public String postCadastroProduto(@RequestParam("file") MultipartFile file, Produto produto, RedirectAttributes redirectAttributes) throws IOException {

		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg1", "Selecione um arquivo");
			return "redirect:/admin/cadastrarProduto";
		}
		
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		
//		try {
//			Path path = Paths.get(UPLOAD_DIR + fileName);
//			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//		} catch (IOException e) {
//			
//		}
		System.out.println("nome >> " + file.getOriginalFilename());
		
		
		String link = usuarioAdminService.postFoto(file);
		
		produto.setFoto(link);
		
		System.out.println(link);
		
		usuarioAdminService.postProduto(produto);
		
		
		redirectAttributes.addFlashAttribute("msg1", String.format("Produto \"%s\" cadastrado com sucesso!", produto.getNome()));
		return "redirect:/admin/cadastrarProduto";
	}
	
	@GetMapping("/cadastrarComercial")
	public String cadastroComercial(Model model) {
		
		return "Cadastro.Comercial";
	}
	
	@PostMapping("/cadastrarComercial")
	public String postCadastroComercial(@RequestParam("file") MultipartFile file, UsuarioComercial usuarioComercial, RedirectAttributes redirectAttributes) throws IOException {
						
		System.out.println("acionado");
		
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("msg1", "Selecione um arquivo");
			return "redirect:/admin/cadastrarComercial";
		}
		
		System.out.println("nome >> " + file.getOriginalFilename());
		
		String link = usuarioAdminService.postFoto(file);
		
		usuarioComercial.setFoto(link);
		
		System.out.println(link);
		
		usuarioAdminService.postComercial(usuarioComercial);
		redirectAttributes.addFlashAttribute("msg1", String.format("Usuário comercial \"%s\" cadastrado com sucesso!", usuarioComercial.getNome()));
		return "redirect:/admin/cadastrarComercial";
	}
	
	@GetMapping("/cadastrarColab")
	public String cadastroColab(Model model) {
		return "Cadastro.Colab";
	}
	
	@PostMapping("/cadastrarColab")
	public String postCadastroColab(UsuarioColaborador usuarioColab, RedirectAttributes redirectAttributes) {
		usuarioAdminService.postColab(usuarioColab);
		redirectAttributes.addFlashAttribute("msg1", String.format("Usuário colaborador \"%s\" cadastrado com sucesso!", usuarioColab.getNome()));
		return "redirect:/cadastrarColab";
	}
	
	@PostMapping("/excluirComercial")
	public String deleteComercial(long codigo, RedirectAttributes redirectAttributes) {
		usuarioAdminService.deleteComercial(codigo);
		redirectAttributes.addFlashAttribute("msg1", "Comércio excluído com sucesso!");
		return "redirect:/admin/listar/1";
	}
	
	@GetMapping("/listar/{page}")
	public String listar(Model model, @PathVariable("page") Long page) {
//		System.out.println("page eh " + page);
		List<?> lista = usuarioAdminService.findAllComercialPage(page - 1);
		model.addAttribute("usuariosComercial", lista);
		
//		System.out.println(lista.get(0).toString());
//		System.out.println(usuarioAdminService.findAllComercialPage(page - 1).size());
		
		return "Layouts.Images";
	}
	
	@GetMapping("/awesome")
	public String awesome(Model model) {
		return "Layouts.Images";
	}
}
