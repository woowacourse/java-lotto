package lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void 숫자가_6개인지_확인() {
        Lotto lotto = new Lotto();
        assertThat(lotto.getLotto().size()).isEqualTo(6);
    }

    @Test
    void 숫자가_중복인지_확인() {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5));
        assertThatThrownBy(() -> {
            new Lotto(nums);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NUMBER_DUPLICATE_ERROR);
    }

    @Test
    void 숫자가_범위에_있는지_확인() {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 50));
        assertThatThrownBy(() -> {
            new Lotto(nums);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NUMBER_RANGE_ERROR);
    }
}
