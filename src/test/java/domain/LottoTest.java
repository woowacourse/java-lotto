package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTest {

    @Test
    void 랜덤_번호가_1에서_45사이로_1개_발급된다() {
        final int minNumber = 1;
        final int maxNumber = 45;

        assertThat(Lotto.generateNumber())
                .isBetween(minNumber, maxNumber);
    }

    @Test
    void 로또_번호_일치_개수를_비교한다() {
        final String mainNumbers = "1, 2, 3, 4, 5, 30";
        final String subNumbers = "1, 2, 3, 4, 5, 7";

        Lotto mainLotto = new Lotto(mainNumbers);
        Lotto subLotto = new Lotto(subNumbers);

        assertThat(mainLotto.calculateMatchNumber(subLotto))
                .isEqualTo(5);
    }

    @Test
    void 랜덤_번호로_로또가_생성된다() {
       assertThatNoException()
                .isThrownBy(() -> new Lotto());
    }

    @Test
    void 커스텀_번호로_로또가_생성된다() {
        final String numbers = "1, 2, 3, 4, 5, 6";

        assertThatNoException()
                .isThrownBy(() -> new Lotto(numbers));
    }

    @Test
    void 로또_번호가_중복될_경우_예외가_발생한다() {
        final String numbers = "1, 2, 3, 4, 5, 5";

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "번호: {0}, 존재 여부: {1}")
    @CsvSource({
            "2, true",
            "22, false",
            "30, true"
    })
    void 로또_내부에_번호가_존재하는지_확인한다(int number, boolean expected) {
        final String numbers = "1, 2, 3, 4, 5, 30";
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.isContained(number))
                .isEqualTo(expected);
    }
}