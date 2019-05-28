package lotto.domain;

import lotto.domain.exception.LottoSizeException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void init() {
        assertThat(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)))
                .isEqualTo(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 번호가_6개가_아닌_경우_테스트() {
        assertThrows(LottoSizeException.class, () -> new Lotto(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    void lotto안에_5가_있는지_여부_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotto.hasNumber(5)).isEqualTo(1);
    }
}
