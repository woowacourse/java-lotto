package model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCountTest {
	@Test
	@DisplayName("로또 생성 중단")
	void checkLottoCountIsZero() {
		LottoCount lottoCount = new LottoCount(1);
		lottoCount.increaseMadeLottoCount();

		assertThat(lottoCount.haveRemainToMake()).isFalse();
	}

	@Test
	@DisplayName("생성된 로또 개수 확인")
	void checkLottoCountReduce() {
		LottoCount lottoCount = new LottoCount(100);
		lottoCount.increaseMadeLottoCount();

		assertThat(lottoCount.getMadeCount()).isEqualTo(1);
	}
}
