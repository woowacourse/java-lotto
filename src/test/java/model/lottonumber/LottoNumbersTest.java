package model.lottonumber;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {
	@Test
	@DisplayName("로또 넘버와 당첨 번호의 일치 개수 확인")
	void countLottoNumbersMatchingWinningNumbers() {
		LottoNumbers lottoNumbers = LottoNumbers.changeFrom(Arrays.asList(1, 2, 3, 4, 5, 6)
			.stream()
			.map(number -> LottoNumber.valueOf(number))
			.collect(Collectors.toList()));
		List<LottoNumber> winningNumber = Arrays.asList(1, 2, 3, 4, 5, 6)
			.stream()
			.map(number -> LottoNumber.valueOf(number))
			.collect(Collectors.toList());
		assertThat(lottoNumbers.countMatchedNumbers(winningNumber)).isEqualTo(6);
	}

	@Test
	@DisplayName("로또 넘버와 보너스 번호 일치 여부 확인")
	void checkLottoNumbersMatchingBonus() {
		LottoNumbers lottoNumbers = LottoNumbers.changeFrom(Arrays.asList(1, 2, 3, 4, 5, 6)
			.stream()
			.map(number -> LottoNumber.valueOf(number))
			.collect(Collectors.toList()));
		LottoNumber bonus = LottoNumber.valueOf(1);
		assertThat(lottoNumbers.checkMatchWithBonus(bonus)).isTrue();
	}
}
