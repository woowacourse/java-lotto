package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

/**
 * 클래스 이름 : MoneyForLottoTest.java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class MoneyForLottoTest {
	@Test
	void MoneyForLotto_생성자_올바른_동작_확인() {
		assertThat(new MoneyForLotto(5000)).isInstanceOf(MoneyForLotto.class);
	}

	@ParameterizedTest
	@NullSource
	void MoneyForLotto_null_입력시_예외처리(Integer nullInput) {
		assertThatThrownBy(() -> {
			new MoneyForLotto(nullInput);
		}).isInstanceOf(NullPointerException.class);
	}

	@Test
	void MoneyForLotto_1000원_미만_입력시_예외처리() {
		assertThatThrownBy(() -> {
			Integer inputMoney = 500;
			new MoneyForLotto(inputMoney);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("1000원 이상 입력해주세요.");
	}

	@Test
	void calculateAmountOfLottos_올바른_동작_확인() {
		assertThat(new MoneyForLotto(5253).calculateAmountOfLottos()).isEqualTo(5);
	}
}
