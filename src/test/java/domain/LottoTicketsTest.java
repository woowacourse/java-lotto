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
	void ticketsCapacityEqualToPriceDividedByUnitPrice() {
		assertThat(new LottoTickets(3000, 0).getLottoTicketsCapacity()).isEqualTo(3);
	}

	@Test
	void addOneLottoInLottoTickets() {
		LottoTickets lottoTickets = new LottoTickets(2000, 0);
		lottoTickets.add(new Lotto(LottoGenerator.generateRandomLottoNumbers()));
		assertThat(lottoTickets.getLottoTicketsSize()).isEqualTo(1);
	}

	@Test
	void addMoreLottoInFullLottoTickets() {
		LottoTickets lottoTickets = new LottoTickets(2000, 0);
		lottoTickets.add(new Lotto(LottoGenerator.generateRandomLottoNumbers()));
		lottoTickets.add(new Lotto(LottoGenerator.generateRandomLottoNumbers()));
		assertThatThrownBy(() -> lottoTickets.add(new Lotto(LottoGenerator.generateRandomLottoNumbers()))).isInstanceOf(
			IllegalArgumentException.class);
	}
}
