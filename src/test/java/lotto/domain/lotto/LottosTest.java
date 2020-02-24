package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.lottonumber.LottoNumber;

class LottosTest {
	@DisplayName("Lottos 생성자 매개변수에 올바른 Lotto 리스트가 들어오면 정상적으로 Lottos객체 생성")
	@Test
	void constructor_validLottoList_createLottos() {
		Set<LottoNumber> lottoNumbers = new HashSet<>(Arrays.asList(
			LottoNumber.valueOf(1),
			LottoNumber.valueOf(2),
			LottoNumber.valueOf(3),
			LottoNumber.valueOf(4),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(6)
		));
		Set<LottoNumber> lottoNumbers2 = new HashSet<>(Arrays.asList(
			LottoNumber.valueOf(1),
			LottoNumber.valueOf(2),
			LottoNumber.valueOf(3),
			LottoNumber.valueOf(4),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(6)
		));
		Lottos lottos = new Lottos(Arrays.asList(
			new Lotto(lottoNumbers),
			new Lotto(lottoNumbers2)));
		assertThat(lottos).isInstanceOf(Lottos.class);
	}
}
