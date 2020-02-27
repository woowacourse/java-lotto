package lotto;

import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @DisplayName("범위 밖의 로또 번호 생성 시 예외 발생 확인")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void checkLottoRangeTest(int number) {
        assertThatThrownBy(() -> {
            new LottoNumber(number);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자 범위를 넘어섰습니다.");
    }

    @Test
    @DisplayName("로또 번호가 숫자가 아닌경우 예외 발생 확인")
    void checkNotNumberTest() {
        assertThatThrownBy(() -> {
            new LottoNumber("a");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("로또 넘버는 숫자여야 합니다. 입력한 문자 : %s", "a"));
    }

    @Test
    @DisplayName("String 숫자로 로또 넘버 생성 확인 및 equals 동치 확인")
    void createLottoNumberWithStringNumberTest() {
        assertThat(new LottoNumber("1")).isEqualTo(new LottoNumber(1));
    }
}
