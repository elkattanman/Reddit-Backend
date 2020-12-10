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

	public static final String ERROR_USERNAME_IS_ALREADY_TAKEN = "Error: Username is already taken!";
	public static final String ERROR_EMAIL_IS_ALREADY_IN_USE = "Error: Email is already in use!";
	public static final String USER_REGISTERED_SUCCESSFULLY = "User registered successfully!";
	public static final String ACCOUNT_ACTIVATED_SUCCESSULLY = "Account Activated Successully";
	private final UserRepository userRepository;
	private final AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(authService.login(loginRequest));
	}

	@PostMapping("/signup")
	@Transactional
	public ResponseEntity registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(ERROR_USERNAME_IS_ALREADY_TAKEN));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(ERROR_EMAIL_IS_ALREADY_IN_USE));
		}

		authService.signup(signUpRequest);

		return ResponseEntity.ok(new MessageResponse(USER_REGISTERED_SUCCESSFULLY));
	}

	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token) {
		authService.verifyAccount(token);
		return new ResponseEntity<>(ACCOUNT_ACTIVATED_SUCCESSULLY, HttpStatus.OK);
	}


}
