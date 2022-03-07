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

    private static final String ERROR_DUPLICATE_BONUS_NUMBER = "[ERROR] 보너스번호는 로또번호와 중복되지 않아야 합니다.";

    @Nested
    @DisplayName("보너스볼과 중복된다면")
    class duplicate_bonus_ball {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6)
                    .stream()
                    .map(LottoNumber::from)
                    .collect(Collectors.toList());
            assertThatThrownBy(() -> new LottoWinningNumbers(Lotto.createManualLotto(lottoNumbers), LottoNumber.from(6)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_DUPLICATE_BONUS_NUMBER);
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
                    .map(LottoNumber::from)
                    .collect(Collectors.toList());
            assertThatCode(() -> new LottoWinningNumbers(Lotto.createManualLotto(lottoNumbers),
                    LottoNumber.from(7))).doesNotThrowAnyException();
        }
    }
}
