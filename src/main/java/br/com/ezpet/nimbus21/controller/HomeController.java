package br.com.ezpet.nimbus21.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.ezpet.nimbus21.service.AuthService;

@Controller
public class HomeController {

	@Autowired
	AuthService authService;
	
	@GetMapping("/")
	public String index(Model model) {
		return "Dashboard.Default";
	}
	
	@GetMapping("/login")
    public String login(Model model){
		
		return "Layouts.Login";
    }
	
	
	@GetMapping("/colab")
	public String colab(Model model) {
		System.out.println("colab here");
		return "";
	}
	
}
