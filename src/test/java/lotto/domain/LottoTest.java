package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@Nested
@DisplayName("한 장의 로또가")
public class LottoTest {

    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_WRONG_LOTTO_SIZE = "[ERROR] 로또는 " + LOTTO_SIZE + "개의 숫자로 이루어져야 합니다.";
    private static final String ERROR_DUPLICATE_LOTTO_NUMBER = "[ERROR] 로또 번호는 서로 중복되지 않아야 합니다.";

    @Nested
    @DisplayName("6개의 숫자로 이루어져있지 않다면")
    class wrong_lotto_count {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4)
                    .stream()
                    .map(LottoNumber::from)
                    .collect(Collectors.toList());
            assertThatThrownBy(() -> Lotto.createManualLotto(lottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_WRONG_LOTTO_SIZE);
        }
    }

    @Nested
    @DisplayName("중복된 숫자를 포함한다면")
    class has_duplicate_number {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 5)
                    .stream()
                    .map(LottoNumber::from)
                    .collect(Collectors.toList());
            assertThatThrownBy(() -> Lotto.createManualLotto(lottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_DUPLICATE_LOTTO_NUMBER);
        }
    }

    @Nested
    @DisplayName("중복되지 않은 6개의 숫자를 입력받는다면")
    class right_lotto_number {
        @Test
        @DisplayName("정상적으로 생성된다.")
        void create_lotto() {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6)
                    .stream()
                    .map(LottoNumber::from)
                    .collect(Collectors.toList());
            assertThatCode(() -> Lotto.createManualLotto(lottoNumbers)).doesNotThrowAnyException();
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6})
        @DisplayName("입력받은 6개의 숫자를 모두 포함한다.")
        void contains_lotto_number(int value) {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6)
                    .stream()
                    .map(LottoNumber::from)
                    .collect(Collectors.toList());
            assertThat(lottoNumbers.contains(LottoNumber.from(value))).isTrue();
        }
    }
    @Nested
    @DisplayName("자동이라면")
    class create_auto_lotto {
        @Test
        @DisplayName("정상적으로 생성된다.")
        void not_return_error() {
            assertThatCode(() -> Lotto.createAutoLotto()).doesNotThrowAnyException();
        }
    }
}
