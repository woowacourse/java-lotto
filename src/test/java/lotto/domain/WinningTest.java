package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningTest {
    @Test
    void 생성() {
        List<Integer> lottos = Arrays.asList(1,2,3,4,5,6);
        Winning winning = Winning.of(lottos);
        assertThat(winning).isEqualTo(Winning.of(lottos));
    }
}
