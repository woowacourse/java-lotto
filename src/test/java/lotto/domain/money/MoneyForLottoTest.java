package lotto.domain.money;

import lotto.domain.money.InvalidMoneyForLottoException;
import lotto.domain.money.MoneyForLotto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.*;

/**
 * MoneyForLotto 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class MoneyForLottoTest {
	@Test
	void MoneyForLotto_생성자_올바른_동작_확인() {
		assertThat(new MoneyForLotto("5000")).isInstanceOf(MoneyForLotto.class);
	}

	@ParameterizedTest
	@NullSource
	void MoneyForLotto_null_입력시_예외처리(String nullInput) {
		assertThatThrownBy(() -> {
			new MoneyForLotto(nullInput);
		}).isInstanceOf(NullPointerException.class);
	}

	@Test
	void MoneyForLotto_1000원_미만_입력시_예외처리() {
		assertThatThrownBy(() -> {
			String inputMoney = "500";
			new MoneyForLotto(inputMoney);
		}).isInstanceOf(InvalidMoneyForLottoException.class)
				.hasMessage("1000원 이상 입력해주세요.");
	}

	@Test
	void MoneyForLotto_정수_이외의_값_입력시_예외처리() {
		assertThatThrownBy(() -> {
			String inputMoney = "로또";
			new MoneyForLotto(inputMoney);
		}).isInstanceOf(InvalidMoneyForLottoException.class)
				.hasMessage("입력금액이 정수가 아닙니다.");
	}

	@Test
	void calculateAmountOfLottos_올바른_동작_확인() {
		assertThat(new MoneyForLotto("5253").calculateAmountOfLottos()).isEqualTo(5);
	}
}
