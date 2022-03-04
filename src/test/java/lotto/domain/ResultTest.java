package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import lotto.domain.lotto.Count;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Number;
import lotto.domain.lotto.WinningLotto;

public class ResultTest {

    private Result result;

    @BeforeEach
    void setUp() {

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(List.of(lotto));
        WinningLotto winningLotto = new WinningLotto(lotto, new Number(7));
        result = lottos.getResult(winningLotto);
    }

    @Nested
    @DisplayName("결과는")
    class NewResult {

        @Nested
        @DisplayName("당첨 번호와 로또들이 주어지면")
        class Context_with_empty {

            @Test
            @DisplayName("당첨된 결과를 저장한다.")
            void it_create_ok() {
                assertThat(result.getCount(LottoRanking.FIRST)).isEqualTo(1);
            }
        }
    }

    @Nested
    @DisplayName("당첨 금액의 총합을 반환하는 기능은")
    class Get_total_profit {

        @Test
        @DisplayName("당첨 금액의 총합을 반환한다.")
        void it_returns_total_profit() {
            assertThat(result.getTotalProfit()).isEqualTo(2000000000L);
        }
    }
}
