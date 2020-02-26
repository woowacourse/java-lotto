package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {
	@DisplayName("45초과 생성자 테스트")
	@Test
	void IllegalArgumentExceptionWhenOverMaxValue() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new LottoNumber(46)
		).withMessage("로또 숫자는 45를 넘기면 안됩니다.");
	}

	@DisplayName("1미만 생성자 테스트")
	@Test
	void IllegalArgumentExceptionWhenUnderMinValue() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			new LottoNumber(0)
		).withMessage("로또 숫자는 0이하 일 수 없습니다.");
	}

}
