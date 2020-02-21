package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class WinningLottoParserTest {
	@Test
	void parser_StringInputWinningLotto_CreateWinningLotto() {
		String inputWinningLotto = "1, 2, 3, 4, 5, 6";

		List<LottoNumber> winningLottoNumbers = WinningLottoParser.parseToLottoNumberList(inputWinningLotto);

		List<LottoNumber> expected = Arrays.asList(
			LottoNumber.valueOf("1"),
			LottoNumber.valueOf("2"),
			LottoNumber.valueOf("3"),
			LottoNumber.valueOf("4"),
			LottoNumber.valueOf("5"),
			LottoNumber.valueOf("6"));

		assertThat(winningLottoNumbers).isEqualTo(expected);
	}
}
