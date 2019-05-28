package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SelfLottoFactoryTest {
    @Test
    void 수동으로_만든_로또_확인() {
        assertThat(SelfLottoFactory.generateSelfLotto(Arrays.asList("1","2","3","4","5","6"))).isEqualTo(new Lotto(Arrays.asList(1,2,3,4,5,6)));
    }
}
