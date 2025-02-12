package src.view;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import src.view.input.ConsoleInputView;

public class ConsoleInputViewTest {

    private static final ConsoleInputView consoleInputView = new ConsoleInputView();

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
}
