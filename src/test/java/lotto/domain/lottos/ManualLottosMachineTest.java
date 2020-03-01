package lotto.domain.lottos;

import lotto.domain.lotto.Lotto;
import lotto.domain.lottonumber.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Manual Lotto Machine 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/01
 */
public class ManualLottosMachineTest {
	@Test
	void create_원하는_수의_수동_로또_생성_확인() {
		List<String> inputManualLottoNumbers = Arrays.asList(
				"1,2,3,4,5,6",
				"7,8,9,42,12,31"
		);
		ManualLottosMachine manualLottosMachine = new ManualLottosMachine(inputManualLottoNumbers);

		assertThat(manualLottosMachine.create().getLottos().size()).isEqualTo(2);
	}

	@Test
	void create_원하는_내용의_수동_로또_생성_확인() {
		List<String> inputManualLottoNumbers = Arrays.asList(
				"1,2,3,4,5,6"
		);
		ManualLottosMachine manualLottosMachine = new ManualLottosMachine(inputManualLottoNumbers);

		Lotto expectedLotto = new Lotto(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(5),
						LottoNumber.of(6)
				)
		);
		assertThat(manualLottosMachine.create().getLottos().get(0).getLottoNumbers()).isEqualTo(expectedLotto.getLottoNumbers());
	}
}
