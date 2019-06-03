package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoParserTest {

    @Test
    void 올바른_당첨로또_입력_검증() {
        String[] scannedWinningNumbers = "1, 2, 3, 4, 5, 6".split(",");
        List<LottoNumber> lottoNumbers = WinningLottoParser.parseLottoNumberList(scannedWinningNumbers);
        Lotto winningLotto = new WinningLotto(lottoNumbers);
        for (int i = 0; i < 6; i++) {
            assertThat(winningLotto.getLottoNumberByIndex(i).getLottoNumber()).isEqualTo(i + 1);
        }
    }

    @Test
    void 갯수가_유효하지_않은_당첨로또_생성_검증() {
        assertThrows(InvalidLottoException.class,
                () -> new WinningLotto((Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5)))));
    }

    @Test
    void 중복된_수가_있는_당첨로또_생성_검증() {
        assertThrows(InvalidLottoException.class, () ->
                new WinningLotto(Arrays.asList(new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(5))));
    }

}
