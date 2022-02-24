package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentTest {

	@DisplayName("로또 가격보다 작은 경우 실패")
	@Test
	void input_less_than_lotto_price() {
		assertThatThrownBy(() -> new Payment("500"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 가격보다 크거나 같은 경우 성공")
	@Test
	void input_more_than_lotto_price() {
		assertThatCode(() -> new Payment("1000"))
				.doesNotThrowAnyException();
	}

	@DisplayName("숫자인 경우 성공")
	@Test
	void input_success() {
		assertThatCode(() -> new Payment("1000"))
			.doesNotThrowAnyException();
	}

	@DisplayName("문자인 경우 실패")
	@Test
	void input_fail() {
		assertThatThrownBy(() -> new Payment("천만원"))
			.isInstanceOf(NumberFormatException.class);
	}

	@DisplayName("빈문자열 실패")
	@Test
	void input_empty_fail() {
		assertThatThrownBy(() -> new Payment(""))
			.isInstanceOf(NumberFormatException.class);
	}

	@DisplayName("구입금액 10만원 초과 실패")
	@Test
	void range_max() {
		assertThatThrownBy(() -> new Payment("110000"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 생성 횟수")
	@Test
	void calculate_lotto_count() {
		assertThat(new Payment("14000").calculateLottoCount()).isEqualTo(14);
	}

	@DisplayName("수익률 계산")
	@Test
	void calculate_profit_rate() {
		List<Rank> lottos = Arrays.asList(Rank.FIFTH, Rank.FOURTH);
		LottoResult lottoResult = new LottoResult(lottos);
		Payment payment = new Payment("10000");

		double profitRate = payment.calculateProfitRate(lottoResult.calculateTotalProfit());

		assertThat(profitRate).isEqualTo(5.5);
	}
}
