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

	@Test
	void addMoreThanCapacity() {
		LottoTickets lottoTickets = new LottoTickets(2000);
		lottoTickets.add(new Lotto(new AutoGenerator().generateLottoNumbers()));
		lottoTickets.add(new Lotto(new AutoGenerator().generateLottoNumbers()));
		assertThatThrownBy(
			() -> lottoTickets.add(new Lotto(new AutoGenerator().generateLottoNumbers()))).isInstanceOf(
			IllegalArgumentException.class);

	}
}
