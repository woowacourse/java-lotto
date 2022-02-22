package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

	@DisplayName("로또목록 생성 테스트")
	@Test
	void initTest() {
		assertDoesNotThrow(() -> new Lottos(14));
	}

}
