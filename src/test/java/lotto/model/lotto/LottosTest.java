package lotto.model.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

	@DisplayName("totalCount 가 3이면 3개의 로또를 생성한다")
	@Test
	void purchase_3() {
		Lottos lottos = Lottos.purchase(3, 1, List.of(Arrays.asList("1", "2", "3", "4", "5", "6")));

		assertThat(lottos.getLottos().size()).isEqualTo(3);
	}

	@DisplayName("totalCount 가 5이면 5개의 로또를 생성한다")
	@Test
	void purchase_5() {
		Lottos lottos = Lottos.purchase(5, 1, List.of(Arrays.asList("1", "2", "3", "4", "5", "6")));

		assertThat(lottos.getLottos().size()).isEqualTo(5);
	}
}
