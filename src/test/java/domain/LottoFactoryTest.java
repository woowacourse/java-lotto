package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

	private final LottoFactory lottoFactory = new LottoFactory();

	@Test
	@DisplayName("구입한 금액을 모두 자동 로또 발급")
	void generateLottoByAuto() {
		int purchaseMoney = 14000;
		Money money = new Money(purchaseMoney);

		assertThat(lottoFactory.generateLottosAsAuto(money).size()).isEqualTo(14);
	}

	@Test
	@DisplayName("수동 로또 발급 테스트")
	public void generateLottoByManual() {
		//given
		List<List<Integer>> inputManualLotto = Arrays.asList(
			Arrays.asList(8, 21, 23, 41, 42, 43),
			Arrays.asList(3, 5, 11, 16, 32, 38),
			Arrays.asList(7, 11, 16, 35, 36, 44)
		);
		//when
		List<Lotto> manualLotto = lottoFactory.generateLottosAsManual(inputManualLotto);
		//then
		assertThat(manualLotto.size()).isEqualTo(3);
	}

	@Test
	@DisplayName("수동 로또에서 로또 숫자의 범위가 벗어난 경우")
	public void checkManualLottoOutOfLottoRange() {
		//given
		List<List<Integer>> inputManualLotto = Collections.singletonList(
			Arrays.asList(8, 21, 23, 41, 42, 46)
		);

		//when and then
		assertThatThrownBy(() -> lottoFactory.generateLottosAsManual(inputManualLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("번호는 1 ~ 45의 숫자여야 합니다");
	}

	@Test
	@DisplayName("입력한 숫자의 수가 7개인 경우")
	public void checkManualLottoSize_1() {
		//given
		List<List<Integer>> inputManualLotto = Collections.singletonList(
			Arrays.asList(8, 21, 23, 37, 41, 42, 45)
		);
		//when and then
		assertThatThrownBy(() -> lottoFactory.generateLottosAsManual(inputManualLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 번호는 6개의 숫자여야 합니다");
	}

	@Test
	@DisplayName("입력한 숫자의 수가 5개인 경우")
	public void checkManualLottoSize_2() {
		//given
		List<List<Integer>> inputManualLotto = Collections.singletonList(
			Arrays.asList(8, 21, 23, 41, 42)
		);
		//when and then
		assertThatThrownBy(() -> lottoFactory.generateLottosAsManual(inputManualLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 번호는 6개의 숫자여야 합니다");
	}

	@Test
	@DisplayName("중복된 숫자가 경우")
	public void checkDuplicateNumberInManualLotto() {
		//given
		List<List<Integer>> inputManualLotto = Collections.singletonList(
			Arrays.asList(8, 21, 23, 41, 41, 45)
		);
		//when and then
		assertThatThrownBy(() -> lottoFactory.generateLottosAsManual(inputManualLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("중복된 숫자를 입력할 수 없습니다");
	}
}
