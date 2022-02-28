package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

	@Test
	void notDivideByUnitPrice() {
		assertThatThrownBy(() -> new LottoTickets(11, 0)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void zeroPrice() {
		assertThatThrownBy(() -> new LottoTickets(0, 0)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void ticketsSizeEqualToPriceDividedByUnitPrice() {
		assertThat(new LottoTickets(3000, 0).getLottoTicketsSize()).isEqualTo(3);
	}

	@Test
	void manualLottoSizeMoreThanTotalLottoSize() {
		assertThatThrownBy(() -> new LottoTickets(4000, 10)).isInstanceOf(IllegalArgumentException.class);
	}
}
