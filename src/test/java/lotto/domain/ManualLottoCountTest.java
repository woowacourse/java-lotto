package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ManualLottoCountTest {
	private LottoMoney lottoMoney;

	@BeforeEach
	void setUp() {
		lottoMoney = new LottoMoney("5000");
	}

	@DisplayName("ManualLottoCount 생성자에 범위 내의 정수 입력이 들어올 때 객체 생성")
	@ParameterizedTest
	@ValueSource(strings = {"5"})
	void constructor_WithinBoundInteger_CreateInstance(String input) {
		assertThat(new ManualLottoCount(input, lottoMoney))
			.isInstanceOf(ManualLottoCount.class);
	}

	@DisplayName("ManualLottoCount 생성자에 정수가 아닌 입력이 들어올 때 InvalidManualLottoException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"a", "1.1"})
	void constructor_NotInteger_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new ManualLottoCount(input, lottoMoney))
			.isInstanceOf(InvalidManualLottoException.class)
			.hasMessage(InvalidManualLottoException.NOT_INTEGER);
	}

	@DisplayName("ManualLottoCount 생성자에 음수인 정수 입력이 들어올 때 InvalidManualLottoException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"-1"})
	void constructor_NegativeInteger_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new ManualLottoCount(input, lottoMoney))
			.isInstanceOf(InvalidManualLottoException.class)
			.hasMessage(InvalidManualLottoException.NOT_POSITIVE);
	}

	@DisplayName("ManualLottoCount 생성자에 구매한 로또 수보다 큰 정수 입력이 들어올 때 InvalidManualLottoException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"6"})
	void constructor_BiggerThanPurchasedLottoCount_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new ManualLottoCount(input, lottoMoney))
			.isInstanceOf(InvalidManualLottoException.class)
			.hasMessage(InvalidManualLottoException.OUT_OF_BOUND);
	}
}
