package lotto.domain.lottos;

import lotto.domain.lottocount.AutoLottoCount;
import lotto.domain.lottocount.LottoCount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Auto Lotto Machine 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/01
 */
public class AutoLottosMachineTest {
	@Test
	void create_원하는_수의_자동_로또_생성_확인() {
		LottoCount autoLottoCount = new AutoLottoCount("1", 3);
		AutoLottosMachine autoLottosMachine = new AutoLottosMachine(autoLottoCount);

		assertThat(autoLottosMachine.create().getLottos().size()).isEqualTo(2);
	}
}
