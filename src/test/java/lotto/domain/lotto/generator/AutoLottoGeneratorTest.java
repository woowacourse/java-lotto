package lotto.domain.lotto.generator;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.lotto.Generator.AutoLottoTicketGenerator;

class AutoLottoGeneratorTest {
	@DisplayName("자동 티켓 개수를 갖고있는 객체의 generate()를 호출하면 List<Lotto> 생성")
	@Test
	void generate_ValidCountOfLotto_ReturnLottoTicket() {
		AutoLottoTicketGenerator lottoTicketGenerator = new AutoLottoTicketGenerator(5);
		assertThat(lottoTicketGenerator.generate()).isInstanceOf(ArrayList.class);
	}
}
