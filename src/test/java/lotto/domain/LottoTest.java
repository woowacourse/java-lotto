package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

	@DisplayName("로또 중복 생성 불가 테스트")
	@Test
	void makeLotto() {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(5));
		assertThatThrownBy(() -> new Lotto(balls)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

}
