package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 로또 번호 수동 생성 테스트 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/03/02
 */
public class ManualLottoTicketFactoryTest {
	@Test
	@DisplayName("문자와 콤마로 이루어진 로또 리스트를 받아 인스턴스 생성")
	void constructor() {
		List<String> lottoNumbers = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7", "40,41,42,43,44,45");
		assertThat(new ManualLottoTicketFactory(lottoNumbers)).isInstanceOf(ManualLottoTicketFactory.class);
	}

	@ParameterizedTest
	@DisplayName("돈을 입력받아 미리 만들어진 로또 제공")
	@ValueSource(longs = {3_000L, 4_000L})
	void generate(long money) {
		List<String> lottoNumbers = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7", "40,41,42,43,44,45");
		LottoGenerative manualLottoMachine = new ManualLottoTicketFactory(lottoNumbers);
		PurchaseMoney purchaseMoney = new PurchaseMoney(money);
		assertThat(manualLottoMachine.generate(purchaseMoney)).isInstanceOf(LottoTicket.class);
	}

	@Test
	@DisplayName("입력받은 돈이 부족하여 로또를 모두 구입할 수 없는 경우")
	void generate() {
		List<String> lottoNumbers = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7", "40,41,42,43,44,45");
		LottoGenerative manualLottoMachine = new ManualLottoTicketFactory(lottoNumbers);
		PurchaseMoney purchaseMoney = new PurchaseMoney(2_000L);
		assertThatThrownBy(() -> manualLottoMachine.generate(purchaseMoney))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
