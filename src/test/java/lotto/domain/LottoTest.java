package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void 중복되는_번호_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> lottos = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                lottos.add(1);
            }
            Lotto.of(lottos);
        });
    }

    @Test
    void 숫자가_6개가_아닌_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> lottos = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                lottos.add(i);
            }
            Lotto.of(lottos);
        });
    }
}
