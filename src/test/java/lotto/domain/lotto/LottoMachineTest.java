package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.domain.LottoMachine;
import lotto.domain.money.InvalidLottoMoneyException;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.NumberLinesOfManualLotto;

class LottoMachineTest {
	private static NumberLinesOfManualLotto numberLinesOfManualLotto;

	@BeforeAll
	static void setUp() {
		numberLinesOfManualLotto = new NumberLinesOfManualLotto();
		numberLinesOfManualLotto.add("1,2,3,4,5,6");
		numberLinesOfManualLotto.add("7,8,9,10,11,12");
	}

	@DisplayName("생성자의 인자로 정상적인 금액과 수동 로또 개수가 들어오면 LottoMachine 객체 생성")
	@ParameterizedTest
	@CsvSource(value = {"2000:0", "2000:1", "2000:2"}, delimiter = ':')
	void constructor_ValidMoneyAndManualLottoCount_CreateLottoMachine(String money, String countOfManualLotto) {
		assertThat(new LottoMachine(money, countOfManualLotto)).isInstanceOf(LottoMachine.class);
	}

	@DisplayName("생성자의 인자로 비정상적인 금액과 정상적인 수동 로또 개수가 들어오면 InvalidLottoMoneyException 발생")
	@ParameterizedTest
	@CsvSource(value = {"cc:0", "-1:1", "0:2"}, delimiter = ':')
	void constructor_InvalidMoneyAndValidManualLottoCount_ExceptionThrown(String money, String countOfManualLotto) {
		assertThatThrownBy(() -> new LottoMachine(money, countOfManualLotto)).isInstanceOf(
			InvalidLottoMoneyException.class);
	}

	@DisplayName("생성자의 인자로 정상적인 금액과 비정상적인 수동 로또 개수가 들어오면 InvalidCountOfManualLottoTicketException 발생")
	@ParameterizedTest
	@CsvSource(value = {"1000:s", "1000:-1", "1000:2"}, delimiter = ':')
	void constructor_ValidMoneyAndInvalidManualLottoCount_ExceptionThrown(String money, String countOfManualLotto) {
		assertThatThrownBy(() -> new LottoMachine(money, countOfManualLotto)).isInstanceOf(
			InvalidCountOfManualLottoTicketException.class);
	}

	@DisplayName("buyLottoTicket의 인수로 정상적인 수동로또번호가 들어왔을 때 전체 로또 티켓을 반환")
	@Test
	void buyLottoTicket_ValidNumberLinesOfManualLotto_ReturnLottoTicket() {
		LottoMachine lottoMachine = new LottoMachine("2000", "2");

		Set<LottoNumber> lottoNumbers1 = new HashSet<>(Arrays.asList(
			LottoNumber.valueOf(1),
			LottoNumber.valueOf(2),
			LottoNumber.valueOf(3),
			LottoNumber.valueOf(4),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(6))
		);

		Set<LottoNumber> lottoNumbers2 = new HashSet<>(Arrays.asList(
			LottoNumber.valueOf(7),
			LottoNumber.valueOf(8),
			LottoNumber.valueOf(9),
			LottoNumber.valueOf(10),
			LottoNumber.valueOf(11),
			LottoNumber.valueOf(12))
		);

		List<Lotto> lottoTicket = Arrays.asList(new Lotto(lottoNumbers1), new Lotto(lottoNumbers2));

		assertThat(lottoMachine.buyLottoTicket(numberLinesOfManualLotto)).isEqualTo(lottoTicket);
	}

	@DisplayName("수동입력을 더 받아야 할 경우 true 반환")
	@Test
	void needMoreManualNumber_NeedMoreInput_ReturnTrue() {
		LottoMachine lottoMachine = new LottoMachine("3000", "3");
		assertThat(lottoMachine.needMoreManualNumber()).isTrue();
	}

	@DisplayName("수동입력을 그만 받아야 할 경우 false 반환")
	@Test
	void needMoreManualNumber_DoNotNeedMoreInput_ReturnTrue() {
		LottoMachine lottoMachine = new LottoMachine("3000", "3");

		for (int i = 0; i < 3; i++) {
			lottoMachine.needMoreManualNumber();
		}

		assertThat(lottoMachine.needMoreManualNumber()).isFalse();
	}
}
