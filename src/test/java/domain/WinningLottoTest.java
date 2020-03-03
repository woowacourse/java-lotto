package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningLottoTest {
	private static final WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);

	@Test
	void createException() {
		List<Integer> sixNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int bonus = 6;

		assertThatThrownBy(() -> {
			new WinningLotto(sixNumbers, bonus);
		})
			.isInstanceOf(Exception.class)
			.hasMessage("로또 번호가 중복됩니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"1,true", "2,true", "3,true", "4,true", "5,true", "6,true", "7,false", "8,false"})
	void isNumberMatch(int number, boolean expected) {
		LottoNumber lottoNumber = LottoNumber.createNumber(number);
		assertThat(winningLotto.isNumberMatch(lottoNumber)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,false", "2,false", "3,false", "4,false", "5,false", "7,true",})
	void isBonusMatch(int bonus, boolean expected) {
		LottoNumber bonusNumber = LottoNumber.createNumber(bonus);
		assertThat(winningLotto.isBonusMatch(bonusNumber)).isEqualTo(expected);
	}
}