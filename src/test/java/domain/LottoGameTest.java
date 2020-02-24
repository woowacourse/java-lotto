package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exception.LottoInputException;

class LottoGameTest {

	private static LottoGame lottoGame;

	@BeforeAll
	static void beforeAll() {
		lottoGame = new LottoGame(new Money(5000));
	}

	@Test
	void bonusNumberDuplicateException() {
		assertThatThrownBy(() -> {
			lottoGame.play("1, 2, 3, 4, 5, 6", "1");
		}).isInstanceOf(LottoInputException.class)
			.hasMessage("로또번호와 보너스번호가 중복됩니다.");
	}
}