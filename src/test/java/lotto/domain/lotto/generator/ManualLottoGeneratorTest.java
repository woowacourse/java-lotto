package lotto.domain.lotto.generator;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.lotto.Generator.ManualLottoTicketGenerator;
import lotto.domain.number.NumberLinesOfManualLotto;

class ManualLottoGeneratorTest {
	@DisplayName("수동 로또 번호 목록을 갖고 있는 객체의 generate()를 호출하면 List<Lotto> 생성")
	@Test
	void generate_ValidNumberLines_ReturnLottoTicket() {
		NumberLinesOfManualLotto numberLinesOfManualLotto = new NumberLinesOfManualLotto();
		numberLinesOfManualLotto.add("1,2,3,4,5,6");
		ManualLottoTicketGenerator lottoTicketGenerator = new ManualLottoTicketGenerator(numberLinesOfManualLotto);

		assertThat(lottoTicketGenerator.generate()).isInstanceOf(ArrayList.class);
	}
}
