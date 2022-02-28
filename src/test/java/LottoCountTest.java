import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.LottoCount;
import domain.Money;

class LottoCountTest {

	@Test
	@DisplayName("수동 개수 0 미만일 때 예외")
	void validateManualCountRange() {
		assertThatThrownBy(() -> LottoCount.of(-1, Money.from(1000)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("입력 금액으로 수동 로또를 사지 못할 때 예외")
	void validateManualCountWithInputMoney() {
		assertThatThrownBy(() -> LottoCount.of(5, Money.from(4900)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("수동 개수, 자동 개수 정상 생성")
	void lottoCountNormalCreate() {
		LottoCount lottoCount = LottoCount.of(3, Money.from(3990));

		assertThat(lottoCount.getAutoCount()).isEqualTo(0);
	}
}