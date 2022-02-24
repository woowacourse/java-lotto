package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottosTest {

    @Nested
    @DisplayName("Lottos는")
    class NewLottos {

        @Nested
        @DisplayName("구입금액이 주어지면")
        class Context_with_money {

            @ParameterizedTest
            @ValueSource(strings = {"1000", "2000"})
            @DisplayName("구입금액에 맞는 개수의 로또를 생성한다.")
            void It_create_lottos(String value) {
                Lottos lottos = new Lottos(new Money(value));

                assertThat(lottos.getCount()).isEqualTo(value);
            }
        }
    }
}
