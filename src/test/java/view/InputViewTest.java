package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class InputViewTest {
    private static final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setIn(originalIn);
    }

    @DisplayName("구입 금액 형식이 올바르지 않은 경우 예외 처리 테스트1")
    @Test
    void 구입_금액_형식이_올바르지_않은_경우_예외_처리_테스트1() {
        String input = "ab\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThatThrownBy(InputView::inputPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액 형식이 올바르지 않은 경우 예외 처리 테스트2")
    @Test
    void 구입_금액_형식이_올바르지_않은_경우_예외_처리_테스트2() {
        String input = "-3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThatThrownBy(InputView::inputPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호 형식이 올바르지 않은 경우 예외 처리 테스트1")
    @Test
    void 당첨_번호_형식이_올바르지_않은_경우_예외_처리_테스트1() {
        String input = "1,2,\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThatThrownBy(InputView::inputPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호 형식이 올바르지 않은 경우 예외 처리 테스트2")
    @Test
    void 당첨_번호_형식이_올바르지_않은_경우_예외_처리_테스트2() {
        String input = "1,1,2,3,4,5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThatThrownBy(InputView::inputPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 볼 형식이 올바르지 않은 경우1")
    @Test
    void 보너스_볼_형식이_올바르지_않은_경우1() {
        String input = "a\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThatThrownBy(InputView::inputPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 볼 형식이 올바르지 않은 경우2")
    @Test
    void 보너스_볼_형식이_올바르지_않은_경우2() {
        String input = "-15\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThatThrownBy(InputView::inputPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 볼 형식이 올바르지 않은 경우3")
    @Test
    void 보너스_볼_형식이_올바르지_않은_경우3() {
        String input = "60\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThatThrownBy(InputView::inputPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
