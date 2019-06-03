package lotto.domain.generate;

import lotto.domain.Lotto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SelfLottoFactoryTest {

    @Test
    void 셀프로또_생성() {
        assertThat(new SelfLottoFactory("1,2,3,4,5,6").create()).isEqualTo(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }
}
