package br.com.ezpet.nimbus21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ezpet.nimbus21.client.RestService;
import br.com.ezpet.nimbus21.domain.Produto;
import br.com.ezpet.nimbus21.domain.UsuarioComercial;
import br.com.ezpet.nimbus21.domain.tipos.TipoProduto;

@Service
public class UsuarioAdminService {

	@Autowired
	RestService restService;
	
	public List<?> findAllComercial() {
		RestTemplate restTemplate = restService.getObject();
		String url1 = "https://ezpet-api.herokuapp.com/usuarioComercial/";
		
		List<?> usuariosComercial = restTemplate.getForObject(url1, List.class);
		
		return usuariosComercial;
	}
	
	public void postProduto(Produto produto) {
		RestTemplate restTemplate = restService.getObject();
		String url1 = "https://ezpet-api.herokuapp.com/usuarioComercial/" + produto.getCodigoUsuarioComercial();
		String url2 = "https://ezpet-api.herokuapp.com/produto/";
		
		UsuarioComercial usuarioComercial = restTemplate.getForObject(url1, UsuarioComercial.class);
		
		produto.setFoto("https://i.imgur.com/cV7o5lD.png");
		produto.setTipoProduto(TipoProduto.FISICO);
		produto.setUsuarioComercial(usuarioComercial);
		
		String freshCookie = getCookie();
		String freshCookieSubs = freshCookie.substring(11, 47);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-XSRF-TOKEN", freshCookieSubs);
		headers.add("Cookie", "XSRF-TOKEN=" + freshCookieSubs);
		
		HttpEntity<Produto> postProduto = new HttpEntity<Produto>(produto, headers);
		restTemplate.postForObject(url2, postProduto, Produto.class);

	}
	
	private String getCookie() {
		RestTemplate api = restService.getObjectNoCookie();
		
		String url1 = "https://ezpet-api.herokuapp.com/cookie/";
		
		ResponseEntity<String> entityCookie = api.getForEntity(url1, String.class);
		String cookie = entityCookie.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
		return cookie;
	}
}
