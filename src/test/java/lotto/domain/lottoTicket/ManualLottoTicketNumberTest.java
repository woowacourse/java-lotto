package lotto.domain.lottoTicket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.lottoMoney.LottoMoney;

class ManualLottoTicketNumberTest {
	private LottoMoney lottoMoney;

	@BeforeEach
	void setUp() {
		lottoMoney = new LottoMoney(15000);
	}

	@ParameterizedTest
	@ValueSource(strings = {"0", "5"})
	void ManualLottoTicketNumber_ValidNumber_GenerateInstance(String value) {
		assertThat(new ManualLottoTicketNumber(value, lottoMoney)).isInstanceOf(ManualLottoTicketNumber.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"abc", "123.4"})
	void parseToInt_NotIntegerNumber_InvalidManualLottoTicketNumberExceptionThrown(String value) {
		assertThatThrownBy(() -> new ManualLottoTicketNumber(value, lottoMoney))
			.isInstanceOf(InvalidManualLottoTicketException.class)
			.hasMessage(InvalidManualLottoTicketException.NOT_INTEGER);
	}

	@Test
	void validateNegative_NegativeNumber_InvalidManualLottoTicketNumberExceptionThrown() {
		String value = "-1";
		assertThatThrownBy(() -> new ManualLottoTicketNumber(value, lottoMoney))
			.isInstanceOf(InvalidManualLottoTicketException.class)
			.hasMessage(InvalidManualLottoTicketException.NEGATIVE);
	}

	@ParameterizedTest
	@ValueSource(strings = {"16", "17", "18"})
	void validatePurchasable_OverThanPurchasableCount_InvalidManualLottoTicketNumberExceptionThrown(String value) {
		assertThatThrownBy(() -> new ManualLottoTicketNumber(value, lottoMoney))
			.isInstanceOf(InvalidManualLottoTicketException.class)
			.hasMessage(InvalidManualLottoTicketException.NOT_PURCHASABLE);
	}
}
