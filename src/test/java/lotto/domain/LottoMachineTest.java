package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class LottoMachineTest {
	@Test
	void 주어진_개수만큼_자동_로또_사기() {
		LottoMachine lottoMachine = LottoMachine.getInstance();
		assertThat(lottoMachine.makeAutoLottos(6).size()).isEqualTo(6);
	}

	@Test
	void 수동_로또_개수만큼_사기() {
		LottoMachine lottoMachine = LottoMachine.getInstance();
		LottoNumbers lottoNumbers = new LottoNumbers(
			Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
				new LottoNumber(5), new LottoNumber(6)));
		List<LottoNumbers> manualLottoNumbers = new ArrayList<>();

		manualLottoNumbers.add(lottoNumbers);
		manualLottoNumbers.add(lottoNumbers);
		assertThat(lottoMachine.makeManualLottos(manualLottoNumbers).size()).isEqualTo(2);
	}
}
