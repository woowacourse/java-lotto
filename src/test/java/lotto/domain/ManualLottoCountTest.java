package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.exception.InvalidLottoCountException;
import lotto.exception.NotIntegerException;

public class ManualLottoCountTest {
	private Money money;

	@BeforeEach
	void setUp() {
		money = new Money("1000");
	}

	@DisplayName("숫자가 아닌 경우")
	@Test
	void createTest() {
		assertThatThrownBy(() -> new ManualLottoCount("v", money))
			.isInstanceOf(NotIntegerException.class);
	}

	@DisplayName("숫자가 아닌 경우")
	@Test
	void createTest2() {
		assertThatThrownBy(() -> new ManualLottoCount("0", money))
			.isInstanceOf(InvalidLottoCountException.class);
	}

	@DisplayName("돈이 부족할 경우")
	@Test
	void createTest3() {
		assertThatThrownBy(() -> new ManualLottoCount("2", money))
			.isInstanceOf(InvalidLottoCountException.class);
	}

	@Test
	void hasNextTest() {
		ManualLottoCount manualLottoCount = new ManualLottoCount("1", money);
		assertThat(manualLottoCount.hasNext()).isTrue();
		assertThat(manualLottoCount.hasNext()).isFalse();
	}
}
