package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @Test
    void 객체_생성() {
        List<Integer> randomValues = Arrays.asList(new Integer[]{8, 21, 23, 41, 42, 43});
        Lotto lotto = new Lotto(randomValues);
        assertThat(lotto).isEqualTo(new Lotto(randomValues));
    }
}
