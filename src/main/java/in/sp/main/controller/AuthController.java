package in.sp.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;

import in.sp.main.dto.LoginRequest;
import in.sp.main.entity.User;
import in.sp.main.repository.UserRepository;
import in.sp.main.security.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginRequest request) {

		authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));

		String token = jwtUtil.generateToken(user.getUsername());

		Map<String, Object> response = new HashMap<>();

		response.put("token", token);
		response.put("username", user.getUsername());
		response.put("role", user.getRole());

		return response;
	}
}