package model.lottonumber;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.strategy.TestLottoNumberGenerationStrategy;
import model.winningnumber.WinningLottoNumberDTO;

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

	@Test
	@DisplayName("로또 번호 숫자 사이즈가 6개가 아닌 경우")
	void validateInputLottoWinningNumberSize() {
		assertThatThrownBy(
			() -> LottoNumbers.from(new TestLottoNumberGenerationStrategy(Arrays.asList(1, 2, 3, 4, 5))))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 로또는 6개의 숫자여야 합니다.");
	}

	@Test
	@DisplayName("로또 번호에 중복이 있는지 검증")
	void validateWinningNumberReduplication() {
		assertThatThrownBy(
			() -> LottoNumbers.from(new TestLottoNumberGenerationStrategy(Arrays.asList(1, 2, 3, 4, 5, 5))))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 로또 번호는 중복이 있으면 안됩니다");
	}
}
