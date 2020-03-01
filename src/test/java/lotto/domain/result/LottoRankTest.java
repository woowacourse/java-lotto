package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.purchase.LottoMoney;

class LottoRankTest {
	@Test
	void of_WithinRankMatchCountAndHasBonusNumber_ReturnInstance() {
		MatchCount matchCount = new MatchCount(5);
		boolean hasBonusLottoNumber = true;

		LottoRank actual = LottoRank.of(matchCount, hasBonusLottoNumber);

		assertThat(actual).isEqualTo(LottoRank.SECOND);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2})
	void of_OutOfRankMatchCountAndHasBonusNumber_ReturnMissInstance(int value) {
		MatchCount matchCount = new MatchCount(value);
		boolean hasBonusLottoNumber = false;

		LottoRank actual = LottoRank.of(matchCount, hasBonusLottoNumber);

		assertThat(actual).isEqualTo(LottoRank.MISS);
	}

	@ParameterizedTest
	@EnumSource(value = LottoRank.class, names = {"SECOND", "THIRD", "FIFTH", "MISS"})
	void calculateWinningLottoMoneyBy_lottoRankCount_multiplyWinningLottoMoneyByLottoRankCount(LottoRank value) {
		int lottoRankCount = 3;
		long winningLottoMoney = value.getWinningLottoMoney();

		LottoMoney actual = value.calculateWinningLottoMoneyBy(lottoRankCount);

		LottoMoney expected = new LottoMoney(winningLottoMoney * lottoRankCount);
		assertThat(actual).isEqualTo(expected);
	}
}
