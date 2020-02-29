package lotto.domain.result;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumberFactory;
import lotto.util.ListBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class LottoTicketsTest {
	private List<SerialLottoNumber> serialLottoNumbers = new ArrayList<>();

	@BeforeEach
	void setUp() {
		serialLottoNumbers.add(SerialLottoNumberFactory.of("1,2,3,4,5,6"));
		serialLottoNumbers.add(SerialLottoNumberFactory.of("2,3,4,5,6,7"));
		serialLottoNumbers.add(SerialLottoNumberFactory.of("3,4,5,6,7,8"));
		serialLottoNumbers.add(SerialLottoNumberFactory.of("10,11,12,13,14,15"));
		serialLottoNumbers.add(SerialLottoNumberFactory.of("16,17,18,19,20,21"));
		serialLottoNumbers.add(SerialLottoNumberFactory.of("21,22,23,24,25,26"));
	}

	@Test
	void LottoTickets() {
		// when
		LottoTickets lottoTickets = new LottoTickets(serialLottoNumbers);

		// then
		Assertions.assertThat(lottoTickets).isEqualTo(new LottoTickets(serialLottoNumbers));
	}

	@ParameterizedTest
	@MethodSource("generateWinningInput")
	void findResults(String winningNumbersInput, int bonusNumberInput, List<Rank> expected) {
		// given
		LottoTickets lottoTickets = new LottoTickets(serialLottoNumbers);

		SerialLottoNumber winningNumbers = SerialLottoNumberFactory.of(winningNumbersInput);
		LottoNumber bonusNumber = LottoNumber.of(bonusNumberInput);
		Winning winning = new Winning(winningNumbers, bonusNumber);

		// when
		List<Rank> result = lottoTickets.findResult(winning);

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> generateWinningInput() {
		return Stream.of(
				Arguments.of("1,2,3,4,5,6", 7, Arrays.asList(Rank.FIRST_PLACE, Rank.SECOND_PLACE,
						Rank.FOURTH_PLACE, Rank.NONE, Rank.NONE, Rank.NONE)),
				Arguments.of("2,3,4,5,6,7", 8, Arrays.asList(Rank.THIRD_PLACE, Rank.FIRST_PLACE,
						Rank.SECOND_PLACE, Rank.NONE, Rank.NONE, Rank.NONE)),
				Arguments.of("7,8,9,10,11,12", 13, Arrays.asList(Rank.NONE, Rank.NONE, Rank.NONE,
						Rank.FIFTH_PLACE, Rank.NONE, Rank.NONE))
		);
	}

	@Test
	void merge() {
		// given
		List<String> given1 = Arrays.asList("1,2,3,4,5,6",
				"2,3,4,5,6,7",
				"5,6,7,8,9,10",
				"11,12,13,14,15,16");
		LottoTickets input1 = LottoTicketsFactory.of(given1);
		List<String> given2 = Arrays.asList("4,5,6,7,8,9",
				"11,12,15,16,17,18",
				"45,44,43,42,41,40");
		LottoTickets input2 = LottoTicketsFactory.of(given2);

		// when
		LottoTickets result = LottoTickets.merge(input1, input2);

		// then
		LottoTickets expected = LottoTicketsFactory.of(ListBuilder.merge(given1, given2));
		Assertions.assertThat(result).isEqualTo(expected);
	}
}