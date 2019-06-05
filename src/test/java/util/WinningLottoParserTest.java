package util;

import domain.Lotto;
import domain.LottoGenerator;
import domain.Number;
import domain.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoParserTest {

    @Test
    void parse_() {
        Lotto lotto = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        Number bonusNumber = Number.from(45);
        WinningLotto winningLotto = WinningLotto.of(lotto, bonusNumber);

        assertThat(WinningLottoParser.parse("1,2,3,4,5,6", "45")).isEqualTo(winningLotto);
    }
}