package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {
	@Test
	@DisplayName("로또 숫자가 45를 초과할 경우 익셉션을 발생한다")
	void IllegalArgumentExceptionWhenOverMaxValue() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new LottoNumber(46);
		}).withMessage("로또 숫자는 45를 넘기면 안됩니다.");
	}

	@Test
	@DisplayName("로또 숫자가 0이하 일경우 익셉션을 발생한다")
	void IllegalArgumentExceptionWhenUnderMinValue() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new LottoNumber(0);
		}).withMessage("로또 숫자는 0이하 일 수 없습니다.");
	}

}
