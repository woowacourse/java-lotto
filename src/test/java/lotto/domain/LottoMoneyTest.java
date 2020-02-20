package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMoneyTest {
	@DisplayName("money 생성자에 null이나 빈 스트링 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void validateNullOrEmpty_NullOrBlankInput_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new LottoMoney(input))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NULL_OR_EMPTY);
	}

	@DisplayName("money 생성자에 양수 입력이 들어올 때 객체 생성")
	@Test
	void constructor_InputNumber_CreatedMoney() {
		assertThat(new LottoMoney("14000"))
			.isInstanceOf(LottoMoney.class);
	}

	@DisplayName("money 생성자에 정수가 아닌 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"abc", "124.1"})
	void parseToInteger_NotInteger_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new LottoMoney(input))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NOT_INTEGER);
	}

	@DisplayName("money 생성자에 0이하인 정수 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"-1", "0"})
	void validatePositive_NotPositiveInteger_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new LottoMoney(input))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NOT_POSITIVE);
	}

	@DisplayName("money 생성자에 1000단위가 아닌 정수 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@Test
	void validateUnit_NotThousandUnitInteger_ExceptionThrown() {
		assertThatThrownBy(() -> new LottoMoney("1001"))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.INVALID_UNIT);
	}

	@DisplayName("money 생성자에 최대금액 이상의 정수 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@Test
	void validateMaxBound_OutOfMaxBoundInteger_ExceptionThrown() {
		assertThatThrownBy(() -> new LottoMoney("200000"))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.OUT_OF_BOUND);
	}

	@Test
	void purchaseLotto_Money_NumberOfLotto() {
		LottoMoney lottoMoney = new LottoMoney("2000");
		assertThat(lottoMoney.purchaseLotto()).isEqualTo(2);
	}
}
