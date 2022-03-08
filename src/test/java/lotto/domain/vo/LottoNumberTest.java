package lotto.domain.vo;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest(name = "number : {0}")
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호는 1이상 45이하가 아니면 예외가 발생한다.")
    void throwExceptionWhenOutOfRange(int number) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoNumber.of(number))
            .withMessageMatching("로또 번호는 1이상 45이하이어야 한다.");
    }

    @ParameterizedTest(name = "number : {0}")
    @ValueSource(ints = {1, 45})
    @DisplayName("로또 번호는 1이상 45이하로 생성된다.")
    void createNumber(int number) {
        assertThat(LottoNumber.of(number)).isNotNull();
    }

    @Test
    @DisplayName("숫자가 같으면 동등한 객체이다.")
    void equalsNumber() {
        assertThat(LottoNumber.of(1)).isEqualTo(LottoNumber.of(1));
    }

    @Test
    @DisplayName("1부터 45까지의 숫자를 생성한다.")
    void numberValues() {
        List<LottoNumber> values = LottoNumber.values();

        assertThat(values).isEqualTo(IntStream.rangeClosed(1, 45)
            .mapToObj(LottoNumber::of)
            .collect(Collectors.toList()));
    }
}
