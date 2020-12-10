package com.elkattanman.reddit.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint, Serializable {

  private static final long serialVersionUID = -8970718410437077606L;
  public static final String YOU_WOULD_NEED_TO_PROVIDE_THE_JWT_TOKEN_TO_ACCESS_THIS_RESOURCE =
      "You would need to provide the Jwt Token to Access This resource";
  public static final String UNAUTHORIZED_ERROR = "Unauthorized error: {}";

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
		log.error(UNAUTHORIZED_ERROR, authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				YOU_WOULD_NEED_TO_PROVIDE_THE_JWT_TOKEN_TO_ACCESS_THIS_RESOURCE);
	}
}