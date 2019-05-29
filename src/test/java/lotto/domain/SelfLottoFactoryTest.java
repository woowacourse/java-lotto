package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SelfLottoFactoryTest {
    @Test
    void 수동_횟수_예외() {
        assertThrows(IllegalArgumentException.class,() ->{
            new SelfLottoFactory(-1,10);
        });
    }

    @Test
    void 수동_로또_확인() {
        SelfLottoFactory selfLottoFactory = new SelfLottoFactory(1,4);
        assertThat(selfLottoFactory.generateSelfLotto("1,2,3,4,5,6")).isEqualTo(new Lotto(Arrays.asList(1,2,3,4,5,6)));
    }
}
