package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.LottoQuantity.NEGATIVE_LOTTO_QUANTITY_ERROR;
import static lotto.domain.Money.LOTTO_PRICE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoQuantityTest {
	private LottoQuantity lottoQuantity;

	@BeforeEach
	void setUp() {
		lottoQuantity = new LottoQuantity(3);
	}

	@DisplayName("생성자 테스트")
	@Test
	void lottoQuantityConstructor() {
		assertThat(new LottoQuantity(0)).isEqualTo(new LottoQuantity(0));
	}

	@DisplayName("음수인 정수가 들어왔을 때 검증해주는지")
	@Test
	void lottoQuantityConstructor_negativeInteger_throwError() {
		assertThatThrownBy(() -> new LottoQuantity(-1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(NEGATIVE_LOTTO_QUANTITY_ERROR);
	}

	@DisplayName("로또 생성 갯수만큼 로또를 잘 생성해 내는지")
	@Test
	void createLottosWith() {
		Lottos manualLottos = lottoQuantity.createLottosUsing(new ManualLottoGenerator(Arrays.asList(
				new int[]{1, 2, 3, 4, 5, 6},
				new int[]{1, 2, 3, 4, 5, 7},
				new int[]{1, 2, 3, 4, 5, 8}
		)));

		assertThat(manualLottos).isEqualTo(new Lottos(Arrays.asList(
				createCustomLotto(1, 2, 3, 4, 5, 6),
				createCustomLotto(1, 2, 3, 4, 5, 7),
				createCustomLotto(1, 2, 3, 4, 5, 8)
		)));

		Lottos autoLottos = lottoQuantity.createLottosUsing(new AutomaticLottoGenerator());
		assertThat(autoLottos.toList().size()).isEqualTo(3);
	}

	private Lotto createCustomLotto(final int... numbers) {
		return new Lotto(Arrays.asList(
				new LottoNumber(numbers[0]),
				new LottoNumber(numbers[1]),
				new LottoNumber(numbers[2]),
				new LottoNumber(numbers[3]),
				new LottoNumber(numbers[4]),
				new LottoNumber(numbers[5])));
	}

	@DisplayName("갯수에 맞는 로또 총 가격을 계산 해내는지")
	@Test
	void getTotalPrice() {
		assertThat(lottoQuantity.getTotalPrice()).isEqualTo(3 * LOTTO_PRICE);
	}
}