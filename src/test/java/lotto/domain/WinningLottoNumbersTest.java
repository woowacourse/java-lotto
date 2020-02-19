package lotto.domain;

import lotto.exceptions.WinningLottoNumbersIllegalArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinningLottoNumbersTest {
	@Test
	void WinningLottoNumbers() {
		// given
		List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
				.map(LottoNumber::new)
				.collect(Collectors.toUnmodifiableList());
		SerialLottoNumber winningLottoNumbers = new SerialLottoNumber(lottoNumbers);
		LottoNumber bonus = new LottoNumber(7);

		// when
		WinningLottoNumbers result = new WinningLottoNumbers(winningLottoNumbers, bonus);

		// then
		Assertions.assertThat(result.getWinningLottoNumbers())
				.isEqualTo(winningLottoNumbers);
		Assertions.assertThat(result.getBonus())
				.isEqualTo(bonus);
	}


	@Test
	void WinningLottoNumbers_WinningNumbersContainsBonusNumber_ShouldThrowException() {
		// given
		List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
				.map(LottoNumber::new)
				.collect(Collectors.toUnmodifiableList());
		SerialLottoNumber winningLottoNumbers = new SerialLottoNumber(lottoNumbers);
		LottoNumber bonus = new LottoNumber(6);

		// then
		Assertions.assertThatThrownBy(() -> {

			// when
			new WinningLottoNumbers(winningLottoNumbers, bonus);
		}).isInstanceOf(WinningLottoNumbersIllegalArgumentException.class)
				.hasMessageMatching(WinningLottoNumbersIllegalArgumentException.MESSAGE);
	}
}
;