import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoTest {

    @Test
    void 숫자6개를갖는다() {
        List<Integer> validNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> invalidNumbers = Arrays.asList(1, 2, 3, 4, 5);

        assertThatCode(() -> new Lotto(validNumbers))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자는_6개인지_테스트() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertThatThrownBy(() -> {
            new Lotto(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자_정렬_테스트() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 6, 5);
        List<Integer> sortedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.getNumbers()).isEqualTo(sortedNumbers);
    }

    @Test
    void 로또_번호_중복_검증() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> new Lotto(numbers)).isInstanceOf(IllegalArgumentException.class);
    }
}
