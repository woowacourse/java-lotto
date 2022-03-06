import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import domain.Lotto;
import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("로또 숫자 1~45 사이일 시 생성 성공")
    @ValueSource(ints = {1, 2, 44, 45})
    void check_range_success(int number) {

        assertThatCode(() -> new LottoNumber(number))
            .doesNotThrowAnyException();
    }


    @ParameterizedTest
    @DisplayName("로또 숫자 1~45 사이 아닐 시 예외 발생")
    @ValueSource(ints = {0, 46})
    void check_range_fail(int number) {

        assertThatThrownBy(
            () -> new LottoNumber(number)
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 1~45 사이 정수만 가능합니다.");

    }

}
