package lotto.view;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.view.input.ConsoleInputView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ConsoleInputViewTest {

    private static final ConsoleInputView consoleInputView = new ConsoleInputView();

    private static Stream<Arguments> 지난_주_로또_당첨_번호_입력_시_구분자가_쉼표가_아니면_예외_발생_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3),
                        List.of(1, 2, 3, 4),
                        List.of(1, 2, 3, 4, 5)
                )
        );
    }

    @Test
    void 구입_금액_입력_시_문자가_입력되면_예외_발생() {
        String input = "abc";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThatIllegalArgumentException().isThrownBy(consoleInputView::inputPurchaseMoney);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", ""})
    void 구입_금액_입력_시_공백이_입력되면_예외_발생(final String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThatIllegalArgumentException().isThrownBy(consoleInputView::inputPurchaseMoney);
    }

    @ParameterizedTest
    @MethodSource("지난_주_로또_당첨_번호_입력_시_구분자가_쉼표가_아니면_예외_발생_테스트_케이스")
    void 지난_주_로또_당첨_번호_입력_시_구분자가_쉼표가_아니면_예외_발생(final List<Integer> input) {

        String joined = input.stream().map(String::valueOf).collect(Collectors.joining(", "));

        System.setIn(new ByteArrayInputStream(joined.getBytes()));

        assertThatIllegalArgumentException().isThrownBy(consoleInputView::inputWinningLottoNumbers);
    }


    @ParameterizedTest
    @ValueSource(strings = {"1, 2. 3", "1+2, 3", "1-2, 3", "1,,2", "1, , 2"})
    void 지난_주_로또_당첨_번호_입력_시_구분자가_쉼표가_아니면_예외_발생(final String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThatIllegalArgumentException().isThrownBy(consoleInputView::inputWinningLottoNumbers);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a, b, c", "1, 2, a", "+1, 2, 3", "-1, 2, 3"})
    void 지난_주_로또_당첨_번호_입력_시_숫자가_아니면_예외_발생(final String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThatIllegalArgumentException().isThrownBy(consoleInputView::inputWinningLottoNumbers);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "-", "=", "?"})
    void 보너스_번호_입력_시_숫자가_아니면_예외_발생(final String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThatIllegalArgumentException().isThrownBy(consoleInputView::inputBonusNumber);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "+2"})
    void 보너스_번호_입력_시_숫자_부호가_포함되면_예외_발생(final String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertThatIllegalArgumentException().isThrownBy(consoleInputView::inputBonusNumber);
    }
}
