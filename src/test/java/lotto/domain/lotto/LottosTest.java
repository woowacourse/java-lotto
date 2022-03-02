package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.domain.Money;
import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.MoneyFactory;

public class LottosTest {

    @Nested
    @DisplayName("Lottos는")
    class New {

        @Nested
        @DisplayName("구입금액이 주어지면")
        class Context_with_money {

            @ParameterizedTest
            @CsvSource(value = {"1000|1", "3000|3", "5000|5"}, delimiter = '|')
            @DisplayName("구입금액에 맞는 개수의 로또를 생성한다.")
            void It_create_lottos(String money, int expected) {
                Lottos lottos = new Lottos(MoneyFactory.valueOf(money));

                assertThat(lottos.getCount()).isEqualTo(expected);
            }
        }
    }

    @Nested
    @DisplayName("로또를 추가하는 기능은")
    class Add {
        @Nested
        @DisplayName("로또가 주어지면")
        class Context_with_lotto {
            @Test
            @DisplayName("로또를 추가한다.")
            void it_add_lotto() {
                Lottos lottos = new Lottos(new Money(5000));
                lottos.add(LottoFactory.auto());
                assertThat(lottos.getCount()).isEqualTo(6);
            }
        }
    }
}
