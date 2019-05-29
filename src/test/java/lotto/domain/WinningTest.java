package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningTest {
    @Test
    void 생성() {
        List<Integer> lottos = Arrays.asList(1,2,3,4,5,6);
        Winning winning = Winning.of(lottos, 7);
        assertThat(winning).isEqualTo(Winning.of(lottos, 7));
    }

    @Test
    void 보너스번호_범위_확인() {
        List<Integer> lottos = Arrays.asList(1,2,3,4,5,6);
        assertThrows(IllegalArgumentException.class, () -> Winning.of(lottos, 46));
        assertThrows(IllegalArgumentException.class, () -> Winning.of(lottos, -1));
    }

    @Test
    void 보너스번호가_당첨번호에_포함되는지() {
        List<Integer> lottos = Arrays.asList(1,2,3,4,5,6);
        assertThrows(IllegalArgumentException.class, () -> Winning.of(lottos, 5));
    }
}
