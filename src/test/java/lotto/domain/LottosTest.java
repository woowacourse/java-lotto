package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
	private List<LottoNumber> lottoNumbers;

	@BeforeEach
	void setUp() {
		lottoNumbers = Arrays.asList(
			LottoNumber.valueOf(1),
			LottoNumber.valueOf(2),
			LottoNumber.valueOf(3),
			LottoNumber.valueOf(4),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(6));
	}

	@DisplayName("Lottos 생성자에 Lotto List 입력이 들어올 때 객체 생성")
	@Test
	void constructor_LottoList_CreateLottos() {
		List<Lotto> lottos = Arrays.asList(
			new Lotto(lottoNumbers),
			new Lotto(lottoNumbers),
			new Lotto(lottoNumbers)
		);
		assertThat(new Lottos(lottos)).isInstanceOf(Lottos.class);
	}
}
