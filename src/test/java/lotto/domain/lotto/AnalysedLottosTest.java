package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

import java.util.Arrays;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.Number;
import lotto.domain.number.PayOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnalysedLottosTest {

    @Test
    @DisplayName("수익률 계산")
    void getYield() {
        WinningNumber winningNumber = new WinningNumber("1, 2, 3, 4, 5, 6", "7");
        LottoGroup lottoGroup = new LottoGroup(Arrays.asList(
            new LottoNumbers(
                Arrays.asList(
                    new LottoNumber(new Number(1)),
                    new LottoNumber(new Number(2)),
                    new LottoNumber(new Number(3)),
                    new LottoNumber(new Number(4)),
                    new LottoNumber(new Number(5)),
                    new LottoNumber(new Number(6))
                )
            ),
            new LottoNumbers(
                Arrays.asList(
                    new LottoNumber(new Number(1)),
                    new LottoNumber(new Number(2)),
                    new LottoNumber(new Number(3)),
                    new LottoNumber(new Number(4)),
                    new LottoNumber(new Number(5)),
                    new LottoNumber(new Number(34))
                )
            ),
            new LottoNumbers(
                Arrays.asList(
                    new LottoNumber(new Number(1)),
                    new LottoNumber(new Number(2)),
                    new LottoNumber(new Number(3)),
                    new LottoNumber(new Number(4)),
                    new LottoNumber(new Number(5)),
                    new LottoNumber(new Number(7))
                )
            )
        ));
        AnalysedLottos result = winningNumber.analysingLottos(lottoGroup);
        assertThat(2031500000D / 3000D).isEqualTo(result.getYield( new PayOut(3000)), withPrecision(2d));
    }
}