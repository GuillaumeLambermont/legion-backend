package be.corsac.legion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class LegionApplicationTests {

	@MockitoBean // Use @MockBean if you are on an older Spring Boot version (< 3.4)
	private JwtDecoder jwtDecoder;

	@Test
	void contextLoads() {
	}

}
