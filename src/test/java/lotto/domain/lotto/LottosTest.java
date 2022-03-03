package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import lotto.domain.LottoRanking;
import lotto.domain.Result;
import lotto.domain.factory.LottoFactory;

public class LottosTest {
    @Nested
    @DisplayName("로또를 추가하는 기능은")
    class Add {
        @Nested
        @DisplayName("로또가 주어지면")
        class Context_with_lotto {
            @Test
            @DisplayName("로또를 추가한다.")
            void it_add_lotto() {
                Lottos lottos = new Lottos();
                lottos.add(LottoFactory.auto());
                assertThat(lottos.totalPrice()).isEqualTo(new Money(1000));
            }
        }
    }

    @Nested
    @DisplayName("순위 결과를 알려주는 기능은")
    class GetResult {
        @Nested
        @DisplayName("당첨 번호가 주어지면")
        class Context_with_winning_lotto {
            @Test
            @DisplayName("순위 결과를 알려준다.")
            void it_returns_result() {
                Lotto winninglotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
                Number bonusNumber = new Number(7);
                WinningLotto winningLotto = new WinningLotto(winninglotto, bonusNumber);

                Lottos lottos = new Lottos();
                lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
                lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 7)));
                Result result = lottos.getResult(winningLotto);
                assertThat(result.getCount(LottoRanking.FIRST)).isEqualTo(1);
                assertThat(result.getCount(LottoRanking.SECOND)).isEqualTo(1);

            }
        }
    }

    @Nested
    @DisplayName("구매한 가격의 총합을 알려주는 기능은")
    class totalPrice {
        @Test
        @DisplayName("구매한 로또의 총합 가격을 알려준다.")
        void it_returns_total_price() {
            Lottos lottos = new Lottos();
            lottos.add(LottoFactory.auto());
            lottos.add(LottoFactory.auto());
            lottos.add(LottoFactory.auto());

            assertThat(lottos.totalPrice()).isEqualTo(new Money(3000));
        }

    }
}
