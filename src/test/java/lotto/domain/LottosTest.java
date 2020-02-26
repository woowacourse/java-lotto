package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import lotto.domain.exception.InvalidLottosException;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;

class LottosTest {
	@DisplayName("Lottos 생성자에 Lotto List 입력이 들어올 때 객체 생성")
	@Test
	void constructor_LottoList_CreateLottos() {
		List<Lotto> lottos = new ArrayList<>();
		Set<LottoNumber> lottoNumbers = new HashSet<>();
		lottoNumbers.add(LottoNumber.valueOf(1));
		lottoNumbers.add(LottoNumber.valueOf(2));
		lottoNumbers.add(LottoNumber.valueOf(3));
		lottoNumbers.add(LottoNumber.valueOf(4));
		lottoNumbers.add(LottoNumber.valueOf(5));
		lottoNumbers.add(LottoNumber.valueOf(6));
		lottos.add(new Lotto(lottoNumbers));

		assertThat(new Lottos(lottos)).isInstanceOf(Lottos.class);
	}

	@DisplayName("Lottos 생성자에 null이나 빈 리스트 입력이 들어올 때 Exception 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void constructor_NullOrEmptyList_ExceptionThrown(List<Lotto> lottos) {
		assertThatThrownBy(() -> new Lottos(lottos))
			.isInstanceOf(InvalidLottosException.class)
			.hasMessage(InvalidLottosException.NULL_OR_EMPTY);
	}
}
