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
    @Nested
    @DisplayName("6개의 숫자로 이루어져 있지 않다면")
    class wrong_lotto_count {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4)
                    .stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
            assertThatThrownBy(() -> new Lotto(lottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("중복된 숫자로 이루어져 있다면")
    class has_duplicate_number {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 5)
                    .stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
            assertThatThrownBy(() -> new Lotto(lottoNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("중복되지 않은 6개의 숫자로 이루어져 있다면")
    class right_lotto_number {
        @Test
        @DisplayName("정상적으로 생성된다.")
        void create_lotto() {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6)
                    .stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
            assertThatCode(() -> new Lotto(lottoNumbers)).doesNotThrowAnyException();
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6})
        @DisplayName("입력받은 6개의 숫자를 모두 포함한다.")
        void contains_lotto_number(int value) {
            List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6)
                    .stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());
            assertThat(lottoNumbers.contains(new LottoNumber(value))).isTrue();
        }
    }
}
