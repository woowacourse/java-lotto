package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

/**
 * 클래스 이름 : MoneyTest.java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class MoneyTest {
	@Test
	void Money_정상_입력에_대한_생성자_동작_확인() {
		assertThat(new Money(5000)).isInstanceOf(Money.class);
	}

	@ParameterizedTest
	@NullSource
	void Money_null_입력시_예외처리(Integer nullInput) {
		assertThatThrownBy(() -> {
			new Money(nullInput);
		}).isInstanceOf(NullPointerException.class);
	}
}
