package lotto.domain.result;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.SerialLottoNumber;
import lotto.domain.number.SerialLottoNumber;
import lotto.exceptions.DuplicateWinningNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class WinningTest {
	@Test
	void Winning() {
		// given
		SerialLottoNumber winningNumbers = SerialLottoNumber.of("1,2,3,4,5,6");
		LottoNumber bonusNumber = LottoNumber.of(7);

		// when
		Winning winning = Winning.of(winningNumbers, bonusNumber);

		// then
		Assertions.assertThat(winning).isEqualTo(Winning.of(winningNumbers, bonusNumber));
	}

	@ParameterizedTest
	@CsvSource(value = {"1,2,3,4,5,6:6", "3,4,5,6,7,8:3", "10,15,20,25,30,35:20"}, delimiter = ':')
	void Winning_WinningNumbersContainBonusNumber_ThrowException(String inputWinningNumbers, int inputBonusNumber) {
		// given
		SerialLottoNumber winningNumbers = SerialLottoNumber.of(inputWinningNumbers);
		LottoNumber bonusNumber = LottoNumber.of(inputBonusNumber);

		// then
		Assertions.assertThatThrownBy(() -> {
			// when
			Winning.of(winningNumbers, bonusNumber);
		}).isInstanceOf(DuplicateWinningNumberException.class)
				.hasMessage("당첨 번호와 보너스 번호가 중복됩니다. 다시 입력해주세요.");
	}

	@ParameterizedTest
	@MethodSource("generateMatchingRankInput")
	void findMatchingRank(String ticketInput, Rank expected) {
		// given
		SerialLottoNumber winningNumbers = SerialLottoNumber.of("1,2,3,4,5,6");
		LottoNumber bonusNumber = LottoNumber.of(7);
		Winning winning = Winning.of(winningNumbers, bonusNumber);

		// when
		Rank result = winning.findMatchingRank(SerialLottoNumber.of(ticketInput));

		// then
		Assertions.assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> generateMatchingRankInput() {
		return Stream.of(Arguments.of("1,2,3,4,5,6", Rank.FIRST_PLACE),
				Arguments.of("2,3,4,5,6,7", Rank.SECOND_PLACE),
				Arguments.of("2,3,4,5,6,8", Rank.THIRD_PLACE),
				Arguments.of("3,4,5,6,7,8", Rank.FOURTH_PLACE),
				Arguments.of("4,5,6,7,8,9", Rank.FIFTH_PLACE),
				Arguments.of("5,6,7,8,9,10", Rank.NONE),
				Arguments.of("6,7,8,9,10,11", Rank.NONE),
				Arguments.of("7,8,9,10,11,12", Rank.NONE),
				Arguments.of("8,9,10,11,12,13", Rank.NONE));
	}
}