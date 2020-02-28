package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.LottoMachine;
import lotto.domain.lottonumber.NumberLinesOfManualLotto;

class LottoMachineTest {
	private static LottoMachine lottoMachine;
	private static int countOfAllLotto;
	private static CountOfManualLotto countOfManualLotto;
	private static NumberLinesOfManualLotto numberLinesOfManualLotto;

	@BeforeAll
	static void setUp() {
		lottoMachine = new LottoMachine();
		countOfAllLotto = 10;
		countOfManualLotto = new CountOfManualLotto("3", countOfAllLotto);
		numberLinesOfManualLotto = new NumberLinesOfManualLotto();
		numberLinesOfManualLotto.add("1,2,3,4,5,6");
		numberLinesOfManualLotto.add("7,8,9,10,11,12");
	}

	@DisplayName("전체 로또의 갯수, 수동 로또의 갯수, 수동 로또 번호가 들어오면 전체 로또를 생성")
	@Test
	void buyLottoTicket_ValidCountAndNumbers_CreateLottos() {
		assertThat(
			lottoMachine.buyLottoTicket(countOfAllLotto, countOfManualLotto, numberLinesOfManualLotto)).isInstanceOf(
			Lottos.class);
	}

	@DisplayName("전체 로또의 갯수와 수동 로또의 갯수가 들어오면 자동 로또의 갯수를 반환")
	@Test
	void calculateCountOfAutoLottos_InvalidCount_ExceptionThrown() {
		assertThat(lottoMachine.calculateCountOfAutoLottos(countOfAllLotto, countOfManualLotto)).isEqualTo(7);
	}
}
