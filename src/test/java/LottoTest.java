
import lotto.Lotto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void validateDistinctNumberTest_중복숫자가_있을_때() {
        List<Integer> invalidNumbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 5));
        assertThatThrownBy(() -> Lotto.validateDistinctNumbers(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되는 로또 번호가 존재합니다.");
    }

    @Test
    void validateNumberScope_숫자가_범위_밖에_있을_때() {
        List<Integer> invalidNumbersUnderScope = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, -11));
        assertThatThrownBy(() -> Lotto.validateNumberScope(invalidNumbersUnderScope))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 범위가 잘못되었습니다.");

        List<Integer> invalidNumberOverScope = new ArrayList<Integer>(Arrays.asList(1, 2, 50, 4, 5, 11));
        assertThatThrownBy(() -> Lotto.validateNumberScope(invalidNumberOverScope))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 범위가 잘못되었습니다.");
    }
}
