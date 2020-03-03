package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoGameTest {
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
	}

	private static Stream<Arguments> createRankAndCount() {
		return Stream.of(
			Arguments.of(
				Rank.FIRST, 1
			),
			Arguments.of(
				Rank.SECOND, 1
			),
			Arguments.of(
				Rank.THIRD, 1
			),
			Arguments.of(
				Rank.NO_RANK, 0
			)
		);
	}

	@ParameterizedTest
	@MethodSource("createRankAndCount")
	void addRanks(Rank rank, int expected) {
		Map<Rank, Integer> ranks = new HashMap<>();
		lottoGame.addRanks(ranks);
		assertThat(ranks.getOrDefault(rank, 0)).isEqualTo(expected);
	}
}