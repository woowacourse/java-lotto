package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

	@Test
	@DisplayName("랜덤 방식의 로또를 생성하는 경우")
	void createRandomLotto() {
		LottoGame lottoGame = new LottoGame();

		Lotto lotto = lottoGame.createLotto();

		assertThat(lotto).isNotNull();
	}
}
