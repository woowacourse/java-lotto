package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

	@Test
	void notDivideByUnitPrice() {
		assertThatThrownBy(() -> new LottoTickets(11)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void zeroPrice() {
		assertThatThrownBy(() -> new LottoTickets(0)).isInstanceOf(IllegalArgumentException.class);
	}
}
