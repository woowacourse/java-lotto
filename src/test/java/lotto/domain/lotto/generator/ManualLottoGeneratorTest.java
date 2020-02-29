package lotto.domain.lotto.generator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.lotto.Generator.ManualLottoTicketGenerator;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.number.NumberLinesOfManualLotto;

class ManualLottoGeneratorTest {
	@DisplayName("수동 로또 번호 리스트를 갖고 있는 객체의 generate()를 호출하면 LottoTicket 생성")
	@Test
	void generate_ValidNumberLines_returnLottoTicket() {
		NumberLinesOfManualLotto numberLinesOfManualLotto = new NumberLinesOfManualLotto();
		numberLinesOfManualLotto.add("1,2,3,4,5,6");
		ManualLottoTicketGenerator lottoTicketGenerator = new ManualLottoTicketGenerator(numberLinesOfManualLotto);

		assertThat(lottoTicketGenerator.generate()).isInstanceOf(LottoTicket.class);
	}
}
