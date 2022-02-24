package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottosTest {

    @Nested
    @DisplayName("Lottos는")
    class NewLottos {

        @Nested
        @DisplayName("구입금액이 주어지면")
        class Context_with_money {

            @ParameterizedTest
            @CsvSource(value = {"1000|1", "3000|3", "5000|5"}, delimiter = '|')
            @DisplayName("구입금액에 맞는 개수의 로또를 생성한다.")
            void It_create_lottos(String money, int expected) {
                Lottos lottos = new Lottos(new Money(money));

                assertThat(lottos.getCount()).isEqualTo(expected);
            }
        }
    }
}
