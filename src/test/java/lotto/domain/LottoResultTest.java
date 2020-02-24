package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@SuppressWarnings("NonAsciiCharacters")
public class LottoResultTest {
	@ParameterizedTest
	@NullAndEmptySource
	void 널이나_빈_값(List<WinningPrize> winningPrizes) {
		assertThatThrownBy(() -> new LottoResult(winningPrizes))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}
}
