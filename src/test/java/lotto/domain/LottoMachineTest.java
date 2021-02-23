package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.domain.Money.MANUAL_LOTTO_QUANTITY_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoMachineTest {
	@DisplayName("생성자 테스트")
	@Test
	void lottoMachineConstructor() {
		LottoMachine lottoMachine = new LottoMachine(new Money(3999), new LottoQuantity(2));

		assertThat(lottoMachine.getMoney()).isNotEqualTo(new Money(3999));
		assertThat(lottoMachine.getMoney()).isEqualTo(new Money(3000)); // actual money taken should be 3000
		assertThat(lottoMachine.getManualLottoQuantityAsInt()).isEqualTo(2);
		assertThat(lottoMachine.getAutoLottoQuantityAsInt()).isEqualTo(1);
	}

	@DisplayName("투입된 금액으로 살 수 없는 양의 로또 갯수가 주어질때 에러 반환하는지")
	@Test
	void lottoMachineConstructor_givenNotAffordableManualLottoQuantity_throwError() {
		assertThatThrownBy(() -> new LottoMachine(new Money(3999), new LottoQuantity(4))).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(MANUAL_LOTTO_QUANTITY_ERROR);
	}

	@DisplayName("로또를 주어진 수동 로또 정보대로 잘 생성하는지")
	@Test
	void createLottosFrom() {
		LottoMachine lottoMachine = new LottoMachine(new Money(5999), new LottoQuantity(2));
		List<int[]> numbersSequence = new ArrayList<>();
		numbersSequence.add(new int[]{1, 2, 3, 4, 5, 6});
		numbersSequence.add(new int[]{1, 2, 3, 4, 5, 7});

		Lottos lottos = lottoMachine.createLottosFrom(numbersSequence);

		assertThat(lottos.toList().get(0)).isEqualTo(new Lotto(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(6)
		)));
		assertThat(lottos.toList().get(1)).isEqualTo(new Lotto(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(4),
				new LottoNumber(5),
				new LottoNumber(7)
		)));
	}
}