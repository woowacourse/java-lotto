package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exception.LottoNumberDuplicateException;

class LottoGameTest {

	private static LottoGame lottoGame;

	@BeforeAll
	static void beforeAll() {
		lottoGame = new LottoGame(new Money(5000));
	}

	@Test
	void playException() {
		String[] numbers = IntStream.of(1, 2, 3, 4, 5, 6)
			.mapToObj(Integer::toString)
			.toArray(String[]::new);

		assertThatThrownBy(() -> {
			lottoGame.play(numbers, "1");
		}).isInstanceOf(LottoNumberDuplicateException.class)
			.hasMessage("로또 번호가 중복됩니다.");
	}
}