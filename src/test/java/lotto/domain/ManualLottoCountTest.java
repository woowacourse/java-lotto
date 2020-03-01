package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.exception.InvalidLottoCountException;
import lotto.exception.NotIntegerException;

public class ManualLottoCountTest {

	@DisplayName("숫자가 아닌 경우")
	@Test
	void createTest() {
		assertThatThrownBy(() -> new ManualLottoCount("v"))
			.isInstanceOf(NotIntegerException.class);
	}

	@DisplayName("0보다 작을 경우")
	@Test
	void createTest2() {
		assertThatThrownBy(() -> new ManualLottoCount("-1"))
			.isInstanceOf(InvalidLottoCountException.class);
	}

	@Test
	void hasNextTest() {
		ManualLottoCount manualLottoCount = new ManualLottoCount("1");
		assertThat(manualLottoCount.hasNext()).isTrue();
		manualLottoCount = new ManualLottoCount("0");
		assertThat(manualLottoCount.hasNext()).isFalse();
	}
}
