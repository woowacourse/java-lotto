package lotto.domain.lottocount;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Auto Lotto Count 테스트
 *
 * @author 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/26
 */
public class AutoLottoCountTest {
	@Test
	void LottoCount_생성자_올바른_동작_확인() {
		assertThat(new AutoLottoCount("2", 12)).isInstanceOf(AutoLottoCount.class);
	}

	@Test
	void LottoCount_멤버에_자동_장수가_올바르게_들어갔는지_확인() {
		LottoCount lottoCount = new AutoLottoCount("2", 12);
		assertThat(lottoCount.getLottoCount()).isEqualTo(10);
	}
}
