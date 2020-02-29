package lotto.domain.lotto.generator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.lotto.Generator.AutoLottoTicketGenerator;
import lotto.domain.lotto.LottoTicket;

class AutoLottoGeneratorTest {
	@DisplayName("자동 티켓 개수를 갖고있는 객체의 generate()를 호출하면 LottoTicket 생성")
	@Test
	void generate_ValidCountOfLotto_returnLottoTicket() {
		AutoLottoTicketGenerator lottoTicketGenerator = new AutoLottoTicketGenerator(5);
		assertThat(lottoTicketGenerator.generate()).isInstanceOf(LottoTicket.class);
	}
}
