package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputConverterTest {

    private InputConverter inputConverter;

    @BeforeEach
    void setUp() {
        this.inputConverter = new InputConverter();
    }

    @Test
    void 당첨_번호를_리스트로_반환한다() {
        String input = "1,2,3,4,5,6";

        List<Integer> winningNumbers = inputConverter.convertWinningNumbers(input);
        assertThat(winningNumbers).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @ValueSource(strings = {"a", "-1", "1,,2", "1,a", "1,46", "0,45", "1,1,2,3,4,5"})
    @ParameterizedTest
    void 당첨_번호_입력_값이_올바르지_않다면_예외를_던진다(String input) {

        assertThatThrownBy(() -> inputConverter.convertWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_번호를_정수로_반환한다() {
        String input = "1";

        int bonusNumber = inputConverter.convertBonusNumber(input);
        assertThat(bonusNumber).isEqualTo(1);
    }

    @ValueSource(strings = {"a", "-1", "1,,2", "1,a", "1,2", "0", "46"})
    @ParameterizedTest
    void 보너스_번호_입력_값이_올바르지_않다면_예외를_던진다(String input) {

        assertThatThrownBy(() -> inputConverter.convertBonusNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입_금액을_정수로_반환한다() {
        String input = "1000";

        int purchaseAmount = inputConverter.convertPurchaseAmount(input);
        assertThat(purchaseAmount).isEqualTo(1000);
    }


    @ValueSource(strings = {"999", "a", "1001"})
    @ParameterizedTest
    void 구입_금액_입력_값이_올바르지_않다면_예외를_던진다(String input) {

        assertThatThrownBy(() -> inputConverter.convertPurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}