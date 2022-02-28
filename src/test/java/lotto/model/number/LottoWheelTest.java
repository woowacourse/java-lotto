package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoWheelTest {
    @ParameterizedTest(name = "{0}에 해당하는 LottoNumber를 반환한다")
    @ValueSource(ints = {1, 15, 40, 23})
    void get_number(int number) {
        assertThat(LottoWheel.get(number).getValue()).isEqualTo(number);
    }

    @DisplayName("랜덤 로또를 추출한 후에도 알맞는 LottoNumber를 반환한다")
    @ParameterizedTest(name = "{0}에 해당하는 LottoNumber를 반환한다")
    @ValueSource(ints = {1, 15, 40, 23})
    void get_number_after_shuffle(int number) {
        LottoWheel.draw(6);

        assertThat(LottoWheel.get(number).getValue()).isEqualTo(number);
    }
}
