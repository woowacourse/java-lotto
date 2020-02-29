package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class LottoMachineTest {
	@Test
	void 주어진_개수만큼_수동_자동_로또_사기() {
		LottoMachine lottoMachine = LottoMachine.getInstance();
		List<LottoNumbers> manualLottoNumbers = Arrays.asList(
			new LottoNumbers(
				Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
					new LottoNumber(5), new LottoNumber(6))),
			new LottoNumbers(
				Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
					new LottoNumber(5), new LottoNumber(6))));

		assertThat(lottoMachine.makeLottos(new LottoCount(new Money(5_000), 2), manualLottoNumbers).size())
			.isEqualTo(5);
	}
}
