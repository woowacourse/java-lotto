package model.bonusball;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusBallTest {

	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	@DisplayName("보너스 볼 입력 숫자 범위 검증")
	void validateBonusBallOutOfRange(int number) {
		assertThatThrownBy(() -> new BonusBall(number))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 보너스 볼은 1~45의 숫자만 입력해주세요.");
	}
}
