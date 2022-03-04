package model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCountTest {
	@Test
	@DisplayName("로또 생성 중단")
	void checkLottoCountIsZero() {
		LottoCount lottoCount = new LottoCount(1);
		lottoCount.reduceCountOfRemain();

		assertThat(lottoCount.haveRemainToMake()).isFalse();
	}

	@Test
	@DisplayName("로또 생성 횟수 차감")
	void checkLottoCountReduce() {
		LottoCount lottoCount = new LottoCount(100);
		lottoCount.reduceCountOfRemain();

		assertThat(lottoCount.getCount()).isEqualTo(99);
	}
}
