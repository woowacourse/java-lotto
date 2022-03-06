package model.lottonumber;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.winningnumber.WinningLottoNumberDTO;
import strategy.TestLottoNumberGenerationStrategy;

public class LottoNumbersTest {
	@Test
	@DisplayName("로또 넘버와 당첨 번호의 일치 개수 확인")
	void countLottoNumbersMatchingWinningNumbers() {
		LottoNumbers lottoNumbers = LottoNumbers.from(new TestLottoNumberGenerationStrategy(
			Arrays.asList(1, 2, 3, 4, 5, 6)));
		WinningLottoNumberDTO winningLottoNumberDTO = new WinningLottoNumberDTO(
			LottoNumbers.from(new TestLottoNumberGenerationStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))),
			LottoNumber.valueOf(7));
		assertThat(lottoNumbers.countMatchedNumbers(winningLottoNumberDTO.getWinningNumbers()))
			.isEqualTo(6);
	}

	@Test
	@DisplayName("로또 넘버와 보너스 번호 일치 여부 확인")
	void checkLottoNumbersMatchingBonus() {
		LottoNumbers lottoNumbers = LottoNumbers.from(
			new TestLottoNumberGenerationStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));
		LottoNumber bonus = LottoNumber.valueOf(1);
		assertThat(lottoNumbers.containLottoNumber(bonus)).isTrue();
	}
}
