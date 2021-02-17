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

    @Test
    void 랜덤_숫자_생성() {
        LottoGenerator.generate(1, 45);
        for (int i = 0; i < 10; i++) {
            Lotto lotto = new Lotto(LottoGenerator.makeNumbers());
            System.out.println(lotto.toString());
        }
    }
}
