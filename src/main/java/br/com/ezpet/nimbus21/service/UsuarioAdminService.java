package br.com.ezpet.nimbus21.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.com.ezpet.nimbus21.client.RestService;
import br.com.ezpet.nimbus21.domain.Produto;
import br.com.ezpet.nimbus21.domain.UsuarioColaborador;
import br.com.ezpet.nimbus21.domain.UsuarioComercial;
import br.com.ezpet.nimbus21.domain.enums.Role;
import br.com.ezpet.nimbus21.domain.tipos.TipoProduto;

@Service
public class UsuarioAdminService {

	@Autowired
	RestService restService;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	public List<?> findAllComercial() {
		RestTemplate restTemplate = restService.getObject();
		String url1 = "https://ezpet-api.herokuapp.com/usuarioComercial/";
		
		List<?> usuariosComercial = restTemplate.getForObject(url1, List.class);
		
		return usuariosComercial;
	}
	
	public List<?> findAllComercialPage(Long page) {
		RestTemplate restTemplate = restService.getObject();
		String url1 = "https://ezpet-api.herokuapp.com/usuarioComercial/all?page=" + page;
		
		List<?> usuariosComercial = restTemplate.getForObject(url1, List.class);
		
		return usuariosComercial;
	}
	
	public void postProduto(Produto produto) {
		RestTemplate restTemplate = restService.getObject();
		String url1 = "https://ezpet-api.herokuapp.com/usuarioComercial/" + produto.getCodigoUsuarioComercial();
		String url2 = "https://ezpet-api.herokuapp.com/produto/";
		
		UsuarioComercial usuarioComercial = restTemplate.getForObject(url1, UsuarioComercial.class);

		produto.setTipoProduto(TipoProduto.FISICO);
		produto.setUsuarioComercial(usuarioComercial);
		
		HttpEntity<Produto> postProduto = new HttpEntity<Produto>(produto, getHeaders());
		restTemplate.postForObject(url2, postProduto, Produto.class);

	}
	
	public void postComercial(UsuarioComercial usuarioComercial) {
		
		usuarioComercial.setSenha(passwordEncoder.encode(usuarioComercial.getSenha()));
		usuarioComercial.setRole(Role.ROLE_COMERCIAL);
				
		RestTemplate restTemplate = restService.getObject();
		String url1 = "https://ezpet-api.herokuapp.com/usuarioComercial/";
		
		HttpEntity<UsuarioComercial> postComercial = new HttpEntity<UsuarioComercial>(usuarioComercial, getHeaders());
		restTemplate.postForObject(url1, postComercial, UsuarioComercial.class);
	}
	
	public void deleteComercial(long codigo) {
		String url1 = "https://ezpet-api.herokuapp.com/usuarioComercial/" + codigo;
		RestTemplate restTemplate = restService.getObject();
		
//		Map<String, String> params = new HashMap<>();
//        params.put("id", String.valueOf(codigo));
        
//        restTemplate.delete(url1, params);
        HttpEntity<?> request = new HttpEntity<Object>(getHeaders());
        restTemplate.exchange(url1, HttpMethod.DELETE, request, String.class);
	}
	
	public void postColab(UsuarioColaborador usuarioColaborador) {
		
	}
	
	public String postFoto(MultipartFile file) throws IOException {
		RestTemplate restTemplate = restService.getObject();
		String url1 = "https://ezpet-api.herokuapp.com/foto/upload";
		
		HttpHeaders headers = getHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		LinkedMultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
		ContentDisposition contentDisposition = ContentDisposition.builder("form-data").name("imageFile").filename(file.getOriginalFilename())
				.build();
		
		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
		
		HttpEntity<byte[]> postFoto = new HttpEntity<>(file.getBytes(), fileMap);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("imageFile", postFoto);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		
		String link = restTemplate.postForObject(url1, requestEntity, String.class);
		return link;
	}
	
	private String getCookie() {
		RestTemplate api = restService.getObjectNoCookie();
		
		String url1 = "https://ezpet-api.herokuapp.com/cookie/";
		
		ResponseEntity<String> entityCookie = api.getForEntity(url1, String.class);
		String cookie = entityCookie.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
		return cookie;
	}
	
	private HttpHeaders getHeaders() {
		String freshCookie = getCookie();
		String freshCookieSubs = freshCookie.substring(11, 47);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-XSRF-TOKEN", freshCookieSubs);
		headers.add("Cookie", "XSRF-TOKEN=" + freshCookieSubs);
		
		return headers;
	}
}
