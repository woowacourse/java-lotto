package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumbersTest {

    @DisplayName("LottoNumbers 정상 생성된다")
    @Test
    void LottoNumbers_생성_테스트() {
        assertThatCode(() -> LottoNumbers.generate(Arrays.asList(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoNumbers의 인스턴스의 사이즈가 6이 아니면 에러가 발생한다. ")
    @Test
    void LottoNumbers_사이즈_예외_테스트() {
        assertThatThrownBy(() -> LottoNumbers.generate(Arrays.asList(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("LottoNumbers에 중복된 LottoNumber가 있다면 에러가 발생한다.")
    @Test
    void LottoNumbers_중복_예외_테스트() {
        assertThatThrownBy(() -> LottoNumbers.generate(Arrays.asList(1, 2, 2, 2, 2, 2)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
