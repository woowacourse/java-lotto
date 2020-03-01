package lotto.domain.lottostore;

import lotto.domain.lottocount.LottoCount;
import lotto.domain.lottocount.ManualLottoCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.lottocount.AutoLottoCount;
import lotto.domain.lottos.Lottos;

/**
 * LottoStore 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/28
 */
public class LottoStoreTest {
	private LottoStore lottoStore;

	@BeforeEach
	void setUp() {
		lottoStore = new LottoStore();
	}

	@Test
	void purchase_수동_2장_자동_2장_구매_성공() {
		LottoCount manualLottoCount = new ManualLottoCount("2", 4);
		LottoCount autoLottoCount = new AutoLottoCount("2", 4);
		List<String> inputManualLottoNumbers = Arrays.asList(
				"1,2,3,4,5,6",
				"7,8,9,42,12,31"
		);
		assertThat(lottoStore.purchase(autoLottoCount, inputManualLottoNumbers)).isInstanceOf(Lottos.class);
	}
}
