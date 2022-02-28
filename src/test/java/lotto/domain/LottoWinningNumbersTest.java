package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Nested
@DisplayName("당첨번호가")
class LottoWinningNumbersTest {
    @Nested
    @DisplayName("보너스볼과 중복된다면")
    class duplicate_bonus_ball {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6)
                    .stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
            assertThatThrownBy(
                    () -> new LottoWinningNumbers(new Lotto(lottoNumbers), new LottoNumber(6)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("보너스볼과 중복되지 않는다면")
    class if_input_right {
        @Test
        @DisplayName("정상적으로 생성된다.")
        void create() {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6)
                    .stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
            assertThatCode(() -> new LottoWinningNumbers(new Lotto(lottoNumbers),
                    new LottoNumber(7))).doesNotThrowAnyException();
        }
    }
}
