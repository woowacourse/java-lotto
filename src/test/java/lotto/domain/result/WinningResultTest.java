package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import lotto.domain.lottoMoney.LottoMoney;
import lotto.domain.lottoRank.LottoRank;

class WinningResultTest {
	@Test
	void WinningResult_MapOfLottoRankAndLottoRankCount_GenerateInstance() {
		Map<LottoRank, Long> value = new TreeMap<>(Collections.reverseOrder());
		value.put(LottoRank.SECOND, 1L);
		value.put(LottoRank.THIRD, 1L);
		value.put(LottoRank.FOURTH, 2L);

		WinningResult actual = new WinningResult(value);

		Map<LottoRank, Long> expected = new TreeMap<>(Collections.reverseOrder());
		expected.put(LottoRank.FIRST, 0L);
		expected.put(LottoRank.SECOND, 1L);
		expected.put(LottoRank.THIRD, 1L);
		expected.put(LottoRank.FOURTH, 2L);
		expected.put(LottoRank.FIFTH, 0L);

		assertThat(actual.getWinningResult()).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validate_NullOrEmptyWinningResult_InvalidWinningResultExceptionThrown(Map<LottoRank, Long> value) {
		assertThatThrownBy(() -> new WinningResult(value))
			.isInstanceOf(InvalidWinningResultException.class)
			.hasMessage(InvalidWinningResultException.NULL_OR_EMPTY);
	}

	@Test
	void calculateWinningRate_PurchaseLottoMoney_WinningRateOfWinningLottoMoneyByPurchaseLottoMoney() {
		Map<LottoRank, Long> value = new TreeMap<>(Collections.reverseOrder());
		value.put(LottoRank.THIRD, 1L);
		WinningResult winningResult = new WinningResult(value);
		LottoMoney lottoMoney = new LottoMoney(10_000L);

		long actual = winningResult.calculateWinningRate(lottoMoney);

		long expected = 15_000L;
		assertThat(actual).isEqualTo(expected);
	}
}
