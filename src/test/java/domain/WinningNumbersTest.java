package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class WinningNumbersTest {

	@Test
	void compareLottos() {
		String sixNumbers = "1, 2, 3, 4, 5, 6";
		String bonusNumber = "7";
		WinningNumbers winningNumbers = new WinningNumbers(sixNumbers, bonusNumber);

		List<Lotto> lottos = Arrays.asList(
			LottoFactory.createSelfNumberLotto(1, 2, 3, 4, 5, 6),
			LottoFactory.createSelfNumberLotto(1, 2, 3, 4, 5, 12),
			LottoFactory.createSelfNumberLotto(1, 2, 3, 4, 11, 12),
			LottoFactory.createSelfNumberLotto(1, 2, 3, 10, 11, 12),
			LottoFactory.createSelfNumberLotto(1, 2, 9, 10, 11, 12)
		);

		List<Rank> givenRanks = winningNumbers.compareLottos(lottos);
		List<Rank> expectedRanks = Arrays.asList(Rank.FIRST, Rank.THIRD, Rank.FOURTH, Rank.FIFTH);

		assertThat(givenRanks).isEqualTo(expectedRanks);
	}
}