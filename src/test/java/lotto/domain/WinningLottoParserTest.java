package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class WinningLottoParserTest {
	@Test
	void parser_StringInputWinningLotto_CreateWinningLotto() {
		String inputWinningLotto = "1, 2, 3, 4, 5, 6";

		Set<LottoNumber> winningLottoNumbers = WinningLottoParser.parseToLottoNumberSet(inputWinningLotto);

		Set<LottoNumber> expected = new HashSet<>();
		expected.add(LottoNumber.valueOf(1));
		expected.add(LottoNumber.valueOf(2));
		expected.add(LottoNumber.valueOf(3));
		expected.add(LottoNumber.valueOf(4));
		expected.add(LottoNumber.valueOf(5));
		expected.add(LottoNumber.valueOf(6));

		assertThat(winningLottoNumbers).isEqualTo(expected);
	}
}
