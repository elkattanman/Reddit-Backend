package com.elkattanman.reddit.security.jwt;

import com.elkattanman.reddit.repository.UserRepository;
import com.elkattanman.reddit.security.jwt.payload.request.LoginRequest;
import com.elkattanman.reddit.security.jwt.payload.request.SignupRequest;
import com.elkattanman.reddit.security.jwt.payload.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {
	private final UserRepository userRepository;
	private final AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(authService.login(loginRequest));
	}

	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		authService.signup(signUpRequest);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token) {
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Activated Successully", HttpStatus.OK);
	}


}
