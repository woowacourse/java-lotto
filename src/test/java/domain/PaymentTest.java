package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PaymentTest {

	@Test
	void division() {
		assertThatThrownBy(() -> new Payment("2500"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void division2() {
		assertThatCode(() -> new Payment("3000"))
			.doesNotThrowAnyException();
	}

	@Test
	void 음수인_경우() {
		assertThatThrownBy(() -> new Payment("-1000"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 문자인_경우_성공() {
		assertThatCode(() -> new Payment("1000"))
			.doesNotThrowAnyException();
	}

	@Test
	void 문자인_경우_실패() {
		assertThatThrownBy(() -> new Payment("천만원"))
			.isInstanceOf(NumberFormatException.class);
	}

	@Test
	void 빈문자열_실패() {
		assertThatThrownBy(() -> new Payment(""))
			.isInstanceOf(NumberFormatException.class);
	}

	@Test
	void 구입금액_10만원_초과_실패() {
		assertThatThrownBy(() -> new Payment("110000"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 로또_생성_횟수() {
		assertThat(new Payment("14000").calculateLottoCount()).isEqualTo(14);
	}

	@Test
	void 수익률_계산() {
		List<Rank> lottos = Arrays.asList(Rank.FIFTH, Rank.FOURTH);
		LottoResult lottoResult = new LottoResult(lottos);
		Payment payment = new Payment("10000");

		double profitRate = payment.calculateProfitRate(lottoResult.calculateTotalProfit());

		assertThat(profitRate).isEqualTo(5.5);
	}
}
