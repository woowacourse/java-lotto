import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.LottoTickets;
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

	@Test
	void ticketsSizeEqualToPriceDividedByUnitPrice() {
		assertThat(new LottoTickets(3000).getLottoTicketsSize()).isEqualTo(3);
	}
}
