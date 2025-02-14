import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("숫자6개를 갖는다")
    void 숫자6개를갖는다() {
        List<Integer> validNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> invalidNumbers = Arrays.asList(1, 2, 3, 4, 5);

        assertThatCode(() -> new Lotto(validNumbers))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 숫자는 6개인지 테스트")
    void 로또_숫자는_6개인지_테스트() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertThatThrownBy(() -> {
            new Lotto(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 숫자 정렬 테스트")
    void 로또_숫자_정렬_테스트() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 6, 5);
        List<Integer> sortedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.getNumbers()).isEqualTo(sortedNumbers);
    }

    @Test
    @DisplayName("로또 출력 테스트")
    void 로또_출력_테스트() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 6, 5);

        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    @DisplayName("로또 번호 중복 검증")
    void 로또_번호_중복_검증() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> new Lotto(numbers)).isInstanceOf(IllegalArgumentException.class);
    }
}
