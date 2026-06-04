package in.sp.main.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {
	
	private final RestTemplate restTemplate;
	
	public EmailService(RestTemplate restTemplate){
		this.restTemplate=restTemplate;
	}

	public void sendEmailNotification(String to, String body, String subject) {
		String url = "http://localhost:8081/email/send";

		Map<String, String> request = new HashMap<>();

		request.put("to", to);

		request.put("subject", subject);

		request.put("body", body);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

		System.out.println("Email Response: " + response.getBody());

	}

}
