package lotto.domain.lotto;

import static lotto.domain.mock.LottoMock.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import lotto.domain.exception.InvalidLottosException;

class LottosTest {
	@DisplayName("Lottos 생성자에 Lotto List 입력이 들어올 때 객체 생성")
	@Test
	void constructor_LottoList_CreateLottos() {
		Lottos lottos = getLottosMock();
		assertThat(lottos).isInstanceOf(Lottos.class);
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
