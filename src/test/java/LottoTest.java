import domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("로또는 6개의 숫자를 갖는다")
    void 로또는_6개의_숫자를_갖는다() {
        // given
        List<Integer> validNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        // then
        assertThatCode(() -> new Lotto(validNumbers))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또가 6개의 숫자보다 적게 갖는다면 예외가 발생한다")
    void 로또는_6개의_숫자를_갖지_않는다면_예외가_발생한다1() {
        // given
        List<Integer> invalidNumbers = Arrays.asList(1, 2, 3, 4, 5);

        // when
        // then
        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또가 6개의 숫자보다 많이 갖는다면 예외가 발생한다")
    void 로또는_6개의_숫자를_갖지_않는다면_예외가_발생한다2() {
        // given
        List<Integer> invalidNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        // when
        // then
        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 숫자 정렬 테스트")
    void 로또_숫자_정렬_테스트() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 6, 5);
        List<Integer> sortedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = new Lotto(numbers);

        // then
        assertThat(lotto.getNumbers()).isEqualTo(sortedNumbers);
    }

    @Test
    @DisplayName("로또 출력 테스트")
    void 로또_출력_테스트() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 6, 5);
        Lotto lotto = new Lotto(numbers);

        // when
        String lottoFormat = lotto.toString();

        // then
        assertThat(lottoFormat).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    @DisplayName("로또 번호 중복 검증")
    void 로또_번호_중복_검증() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5);

        // when
        // then
        assertThatThrownBy(() -> new Lotto(numbers)).isInstanceOf(IllegalArgumentException.class);
    }
}