package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CountOfManualLottoTicketTest {
	@DisplayName("생성자에 정상적인 수동 로또 갯수와 전체 로또 갯수가 들어오면 객체 생성")
	@Test
	void constructor_ValidRawCountAndAllLottoCount_CreateCountOfManualLottoTickets() {
		assertThat(new CountOfManualLottoTicket("3", 7)).isInstanceOf(CountOfManualLottoTicket.class);
	}

	@DisplayName("생성자에 문자 타입의 수동 로또 갯수와 정상적인 전체 로또 갯수가 들어오면 InvalidCountOfManualLottoTicketException 발생")
	@Test
	void constructor_InvalidRawCountAndValidAllLottoCount_ThrownException() {
		assertThatThrownBy(() -> new CountOfManualLottoTicket("s", 7))
			.isInstanceOf(InvalidCountOfManualLottoTicketException.class)
			.hasMessage(InvalidCountOfManualLottoTicketException.WRONG_TYPE);
	}

	@DisplayName("생성자에 정상적인 수동 로또 갯수와 정상 범위를 벗어나는 전체 로또 갯수가 들어오면 InvalidCountOfManualLottoTicketException 발생")
	@ParameterizedTest
	@CsvSource(value = {"3:-1", "3:1"}, delimiter = ':')
	void constructor_ValidRawCountAndInvalidAllLottoCount_ThrownException(String rawCount, int countOfAllLotto) {
		assertThatThrownBy(() -> new CountOfManualLottoTicket(rawCount, countOfAllLotto))
			.isInstanceOf(InvalidCountOfManualLottoTicketException.class)
			.hasMessage(InvalidCountOfManualLottoTicketException.WRONG_BOUND);
	}
}
