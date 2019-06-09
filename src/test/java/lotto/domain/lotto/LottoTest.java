package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void 중복되는_번호_확인() {
        assertThrows(InvalidLottoException.class, () -> {
            List<Integer> lottos = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                lottos.add(1);
            }
            Lotto.of(lottos);
        });
    }

    @Test
    void 숫자가_6개가_아닌_경우() {
        assertThrows(InvalidLottoException.class, () -> {
            List<Integer> lottos = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                lottos.add(i);
            }
            Lotto.of(lottos);
        });
    }

    @Test
    void 숫자_일치_개수() {
        Lotto lotto = Lotto.of(Arrays.asList(1,2,3,4,5,6));
        Lotto lotto1 = Lotto.of(Arrays.asList(7,8,9,10,11,12));
        assertThat(lotto.sameNumberCount(lotto1)).isEqualTo(0);
    }
}
