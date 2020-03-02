package lotto.domain.result;

import lotto.domain.money.Money;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumberFactory;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.LottoTicketsFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.stream.Collectors;

class LottoResultTest {
	private Map<Rank, Integer> rankToCount = new HashMap<>();

	@BeforeEach
	void setUp() {
		rankToCount = Arrays.stream(Rank.values())
				.collect(Collectors.toMap(rank -> rank, rank -> 0));
		rankToCount.replace(Rank.FIRST_PLACE, 1);
		rankToCount.replace(Rank.SECOND_PLACE, 1);
		rankToCount.replace(Rank.THIRD_PLACE, 1);
		rankToCount.replace(Rank.NONE, 3);
	}

	@Test
	void Results() {
		// when
		LottoResult lottoResult = new LottoResult(rankToCount);

		// then
		Assertions.assertThat(lottoResult).isEqualTo(new LottoResult(rankToCount));
	}

	@Test
	void of() {
		// given
		SerialLottoNumber winningNumbers = SerialLottoNumberFactory.of("2,3,4,5,6,7");
		LottoNumber bonusNumber = LottoNumber.of(8);
		Winning winning = new Winning(winningNumbers, bonusNumber);

		List<String> serialLottoNumbers = new ArrayList<>();
		serialLottoNumbers.add("1,2,3,4,5,6");
		serialLottoNumbers.add("2,3,4,5,6,7");
		serialLottoNumbers.add("3,4,5,6,7,8");
		serialLottoNumbers.add("10,11,12,13,14,15");
		serialLottoNumbers.add("16,17,18,19,20,21");
		serialLottoNumbers.add("21,22,23,24,25,26");
		LottoTickets lottoTickets = LottoTicketsFactory.of(serialLottoNumbers);

		// when
		LottoResult lottoResult = LottoResult.of(winning, lottoTickets);

		// then
		LottoResult expected = new LottoResult(rankToCount);
		Assertions.assertThat(lottoResult).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1157500000, 200"})
	void calculateProfitRate(int moneyInput, double expected) {
		// given
		Money money = new Money(moneyInput);
		LottoResult lottoResult = new LottoResult(rankToCount);

		// when
		double result = lottoResult.calculateProfitRate(money);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	@Test
	void getWinningRankCount() {
		// given
		LottoResult lottoResult = new LottoResult(rankToCount);

		// when
		Map<Rank, Integer> result = lottoResult.getWinningRankCount();

		// then
		rankToCount.remove(Rank.NONE);
		Assertions.assertThat(result).isEqualTo(rankToCount);
	}
}