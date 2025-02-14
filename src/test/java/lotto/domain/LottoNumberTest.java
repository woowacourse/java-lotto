package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    void 같은_숫자면_True를_반환한다() {
        LottoNumber number1 = LottoNumber.from("5");
        LottoNumber number2 = LottoNumber.from("5");

        assertThat(number1).isEqualTo(number2);
    }

    @Test
    void 숫자는_1과_45_사이만_가능하다() {
        IntStream.range(1, 45)
                .forEach(i -> assertThatCode(() -> new LottoNumber(i))
                        .doesNotThrowAnyException());

    }

}
