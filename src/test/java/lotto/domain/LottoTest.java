package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.utils.Validation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Test
    public void 로또번호_개수_검증() {
        List<Integer> testValues = new ArrayList<>();
        testValues.add(1);
        testValues.add(2);
        testValues.add(3);
        testValues.add(4);
        Lotto lotto = new Lotto(testValues);
        assertThat(lotto.getNumbers().size()).isNotEqualTo(LottoNumber.LOTTO_SELECT_NUMBER);
    }

    @Test
    public void 로또번호_중복_검증() {
        List<Integer> testValues = new ArrayList<>();
        testValues.add(1);
        testValues.add(2);
        testValues.add(3);
        testValues.add(4);
        testValues.add(5);
        testValues.add(5);
        assertThatThrownBy(() -> new Lotto(testValues))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Validation.ERROR_DUPLICATE_NUMBER);
    }
}
