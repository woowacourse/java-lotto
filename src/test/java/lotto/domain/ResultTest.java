package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Number;
import lotto.domain.lotto.WinningLotto;

public class ResultTest {

    @Nested
    @DisplayName("결과는")
    class NewResult {

        @Nested
        @DisplayName("당첨 번호와 로또들이 주어지면")
        class Context_with_empty {

            @Test
            @DisplayName("당첨된 결과를 저장한다.")
            void it_create_ok() {
                Lotto lotto  = new Lotto(List.of(1, 2, 3, 4, 5, 6));
                WinningLotto winningLotto = new WinningLotto(lotto, new Number(7));

                Lottos lottos = new Lottos(lotto);
                Result result = new Result(lottos, winningLotto);

                assertThat(result.getCount(LottoRanking.All)).isEqualTo(1);
            }
        }
    }

    @Nested
    @DisplayName("수익률을 계산하는 메소드는")
    class GetRateOfProfit {

        @Nested
        @DisplayName("돈이 주어지면")
        class Context_with_money {

            @ParameterizedTest
            @CsvSource(value = {"10000|0.5", "5000|1.0", "50000|0.1", "1000|5.0", "1000000|0.005", "10000000|0.001",
                "100000000|0.0"}, delimiter = '|')
            @DisplayName("당첨금액의 합과 비교하여 수익률을 반환한다.")
            void it_returns_rate_of_profit(String money, double expected) {
                Lotto lotto  = new Lotto(List.of(1, 2, 3, 4, 5, 6));
                WinningLotto winningLotto = new WinningLotto(lotto, new Number(7));

                Lottos lottos = new Lottos(new Lotto(List.of(1, 2, 3, 42, 43, 44)));
                Result result = new Result(lottos, winningLotto);
                assertThat(result.getRateOfProfit(new Money(money))).isEqualTo(expected);
            }
        }
    }
}
