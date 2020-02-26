package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lotto.exception.InvalidLottoCountException;
import lotto.exception.NotIntegerException;

public class ManualLottoCountTest {
	private Money money;

	@BeforeEach
	void setUp() {
		money = new Money("1000");
	}

	@Test
	void createTest_숫자가_아닌_경우() {
		assertThatThrownBy(() -> new ManualLottoCount("v", money))
			.isInstanceOf(NotIntegerException.class);
	}

	@Test
	void createTest_양수가_아닌_경우() {
		assertThatThrownBy(() -> new ManualLottoCount("0", money))
			.isInstanceOf(InvalidLottoCountException.class);
	}

	@Test
	void createTest_돈이_부족할_경우() {
		assertThatThrownBy(() -> new ManualLottoCount("2", money))
			.isInstanceOf(InvalidLottoCountException.class);
	}

	@Test
	void hasNextTest() {
		ManualLottoCount manualLottoCount = new ManualLottoCount("1", money);
		assertThat(manualLottoCount.hasNext()).isTrue();
		manualLottoCount.next();
		assertThat(manualLottoCount.hasNext()).isFalse();
	}

	@Test
	void nextTest() {
		ManualLottoCount manualLottoCount = new ManualLottoCount("1", money);
		assertThat(manualLottoCount.next()).isEqualTo(1);
		assertThat(manualLottoCount.next()).isEqualTo(0);
	}
}
