package model.lotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static global.constant.ErrorMessage.LOTTO_NUMBER_DUPLICATE_MESSAGE;
import static global.constant.ErrorMessage.NUMERIC_INPUT_ONLY_MESSAGE;
import static global.constant.ErrorMessage.RANGE_INPUT_ONLY_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    void 입력에_따라_로또가_생성된다() {
        // given
        String input = "1, 2, 3, 4, 5, 6";
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = new Lotto(input);

        // then
        expectedNumbers.forEach(number ->
                assertThat(lotto.isContained(number))
                        .isTrue()
        );
    }

    @ParameterizedTest(name = "입력 : {0}")
    @ValueSource(strings = {"a", "^", "🚀"})
    void 입력이_숫자가_아닌_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMERIC_INPUT_ONLY_MESSAGE.getMessage());
    }

    @ParameterizedTest(name = "입력 : {0}")
    @ValueSource(strings = {"0", "46"})
    void 로또_숫자_범위가_아닌_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(RANGE_INPUT_ONLY_MESSAGE.getMessage());
    }

    @ParameterizedTest(name = "입력 : {0}")
    @ValueSource(strings = {
            "1, 1, 1, 1, 1, 1",
            "5, 5, 5, 5, 5, 5"
    })
    void 로또_숫자_중복된_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUMBER_DUPLICATE_MESSAGE.getMessage());
    }

    @ParameterizedTest(name = "입력 : {0}")
    @ValueSource(strings = {
            "1, 2, 3, 4, 5",
            "1, 2, 3, 4, 5, 6, 7"
    })
    void 로또의_크기와_일치하지_않으면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(RANGE_INPUT_ONLY_MESSAGE.getMessage());
    }

    @Test
    void 매칭된_로또_숫자를_계산해_반환한다() {
        // given
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");
        Lotto otherLotto = new Lotto("1, 2, 3, 4, 5, 7");

        // when
        int result = lotto.calculateMatchNumber(otherLotto);

        // then
        assertThat(result)
                .isEqualTo(5);
    }

    @Test
    void 포함된_로또_번호인_경우_TRUE를_반환한다() {
        // given
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");

        // when & then
        assertThat(lotto.isContained(6))
                .isTrue();
    }

    @Test
    void 포함된_로또_번호가_아닌_경우_FALSE를_반환한다() {
        // given
        Lotto lotto = new Lotto("1, 2, 3, 4, 5, 6");

        // when & then
        assertThat(lotto.isContained(7))
                .isFalse();
    }
}
