package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.Number;
import org.junit.jupiter.api.Test;

public class WinningNumberTest {

    @Test
    void getStatistics() {
        WinningNumber winningNumber = new WinningNumber("1, 2, 3, 4, 5, 6");
        LottoNumbers expected = new LottoNumbers(
                Arrays.asList(
                    new LottoNumber(new Number(1)),
                    new LottoNumber(new Number(2)),
                    new LottoNumber(new Number(3)),
                    new LottoNumber(new Number(4)),
                    new LottoNumber(new Number(5)),
                    new LottoNumber(new Number(6))
                )
            );

        assertThat(expected).isEqualTo(winningNumber.getLottoNumbers());
    }
}