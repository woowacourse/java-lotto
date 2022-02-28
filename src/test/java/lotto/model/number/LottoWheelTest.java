package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoWheelTest {
    @ParameterizedTest(name = "{0}에 해당하는 LottoNumber를 반환한다")
    @ValueSource(ints = {1, 15, 40, 23})
    void get_number(int number) {
        assertThat(LottoWheel.get(number)).isEqualTo(LottoNumber.from(String.valueOf(number)));
    }
}
