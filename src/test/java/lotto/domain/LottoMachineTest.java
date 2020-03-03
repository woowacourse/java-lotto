package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 수동과 자동 로또를 생성하는 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/03/03
 */
public class LottoMachineTest {
	@Test
	@DisplayName("로또 숫자 리스트를 인자로 받아 로또 기계를 생성")
	void constructor() {
		List<String> lottoNumbers = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7", "40,41,42,43,44,45");
		assertThat(new LottoMachine(lottoNumbers)).isInstanceOf(LottoMachine.class);
	}

	@Test
	@DisplayName("10,000원을 받아 수동로또 3장과 자동로또 7장을 합쳐 10장의 로또를 만듦")
	void generate() {
		List<String> lottoNumbers = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7", "40,41,42,43,44,45");
		LottoGenerative lottoMachine = new LottoMachine(lottoNumbers);
		LottoTicket lottoTicket = lottoMachine.generate(new PurchaseMoney(10_000L));
		assertThat(lottoTicket.size()).isEqualTo(10);
	}
}
