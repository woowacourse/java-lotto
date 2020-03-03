package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GameResultTest {

	private static GameResult gameResult;
	private static LottoGame lottoGame;

	private static Lotto createLotto(int... numbers) {
		return new Lotto(Arrays.stream(numbers)
			.mapToObj(LottoNumber::createNumber)
			.collect(Collectors.toList()));
	}

	private static List<Lotto> createLottos() {
		return Arrays.asList(
			createLotto(1, 2, 3, 4, 5, 6),
			createLotto(1, 2, 3, 4, 5, 7),
			createLotto(1, 2, 3, 4, 5, 10));
	}

	@BeforeAll
	static void beforeAll() {
		lottoGame = new LottoGame(
			new Lottos(createLottos(), new ArrayList<>()),
			new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7)
		);
		gameResult = GameResult.create(lottoGame);
	}

	@Test
	void calculateProfit() {
		Money purchaseMoney = new Money(50000);
		double result = gameResult.calculateProfit(purchaseMoney);
		assertThat(result).isEqualTo(4063000);
	}
}