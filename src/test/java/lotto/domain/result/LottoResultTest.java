package lotto.domain.result;

import lotto.domain.money.Money;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumberFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.stream.Collectors;

class LottoResultTest {
	private Map<Rank, Integer> rankCount = new HashMap<>();

	@BeforeEach
	void setUp() {
		rankCount = Arrays.stream(Rank.values())
				.collect(Collectors.toMap(rank -> rank, rank -> 0));
		rankCount.replace(Rank.FIRST_PLACE, 1);
		rankCount.replace(Rank.SECOND_PLACE, 1);
		rankCount.replace(Rank.THIRD_PLACE, 1);
		rankCount.replace(Rank.NONE, 3);
	}

	@Test
	void Results() {
		// when
		LottoResult lottoResult = new LottoResult(rankCount);

		// then
		Assertions.assertThat(lottoResult).isEqualTo(new LottoResult(rankCount));
	}

	@Test
	void of() {
		// given
		SerialLottoNumber winningNumbers = SerialLottoNumberFactory.of("2,3,4,5,6,7");
		LottoNumber bonusNumber = LottoNumber.of(8);
		Winning winning = new Winning(winningNumbers, bonusNumber);

		List<SerialLottoNumber> serialLottoNumbers = new ArrayList<>();
		serialLottoNumbers.add(SerialLottoNumberFactory.of("1,2,3,4,5,6"));
		serialLottoNumbers.add(SerialLottoNumberFactory.of("2,3,4,5,6,7"));
		serialLottoNumbers.add(SerialLottoNumberFactory.of("3,4,5,6,7,8"));
		serialLottoNumbers.add(SerialLottoNumberFactory.of("10,11,12,13,14,15"));
		serialLottoNumbers.add(SerialLottoNumberFactory.of("16,17,18,19,20,21"));
		serialLottoNumbers.add(SerialLottoNumberFactory.of("21,22,23,24,25,26"));
		LottoTickets lottoTickets = new LottoTickets(serialLottoNumbers);

		// when
		LottoResult lottoResult = LottoResult.of(winning, lottoTickets);

		// then
		LottoResult expected = new LottoResult(rankCount);
		Assertions.assertThat(lottoResult).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1157500000, 200"})
	void calculateProfitRate(int moneyInput, double expected) {
		// given
		Money money = new Money(moneyInput);
		LottoResult lottoResult = new LottoResult(rankCount);

		// when
		double result = lottoResult.calculateProfitRate(money);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@Test
	void getWinningRankCount() {
		// given
		LottoResult lottoResult = new LottoResult(rankCount);

		// when
		Map<Rank, Integer> result = lottoResult.getWinningRankCount();

		// then
		rankCount.remove(Rank.NONE);
		Assertions.assertThat(result).isEqualTo(rankCount);
	}
}