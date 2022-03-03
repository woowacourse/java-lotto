package model.bonusball;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import model.lottonumber.LottoNumber;

public class BonusBallTest {

	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	@DisplayName("보너스 볼 입력 숫자 범위 검증")
	void validateBonusBallOutOfRange(int number) {
		assertThatThrownBy(() -> new BonusBall(LottoNumber.valueOf(number)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 로또 번호는 1~45 숫자여야 합니다.");
	}
}
