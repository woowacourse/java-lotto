import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoTest {

    @Test
    void 숫자6개를갖는다() {
        Numbers validNumbers = new Numbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        Numbers invalidNumbers = new Numbers(Arrays.asList(1, 2, 3, 4, 5));

        assertThatCode(() -> new Lotto(validNumbers))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자는_6개인지_테스트() {
        Numbers numbers = new Numbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertThatThrownBy(() -> {
            new Lotto(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자_정렬_테스트() {
        Numbers numbers = new Numbers(Arrays.asList(1, 2, 3, 4, 6, 5));
        Numbers sortedNumbers = new Numbers(Arrays.asList(1, 2, 3, 4, 5, 6));

        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.getNumbers()).isEqualTo(sortedNumbers.getNumbers());
    }

    @Test
    void 로또_번호_중복_검증() {
        Numbers numbers = new Numbers(Arrays.asList(1, 2, 3, 4, 5, 5));
        assertThatThrownBy(() -> new Lotto(numbers)).isInstanceOf(IllegalArgumentException.class);
    }
}
