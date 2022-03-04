package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberFactoryTest {
    @DisplayName("로또 번호가 숫자가 아니면 예외가 발생한다")
    @Test
    void type_exception() {
        assertThatThrownBy(() -> LottoNumberFactory.getNumber("일"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 숫자로만 입력해주세요");
    }

    @ParameterizedTest(name = "{0}에 해당하는 LottoNumber를 반환한다")
    @ValueSource(ints = {1, 15, 40, 23})
    void get_number(int number) {
        assertThat(LottoNumberFactory.getNumber(String.valueOf(number)).getValue()).isEqualTo(number);
    }

    @DisplayName("랜덤 로또를 추출한 후에도 알맞는 LottoNumber를 반환한다")
    @ParameterizedTest(name = "{0}에 해당하는 LottoNumber를 반환한다")
    @ValueSource(ints = {1, 15, 40, 23})
    void get_number_after_shuffle(int number) {
        LottoNumberFactory.getRandomNumbers(1);

        assertThat(LottoNumberFactory.getNumber(String.valueOf(number)).getValue()).isEqualTo(number);
    }
}
