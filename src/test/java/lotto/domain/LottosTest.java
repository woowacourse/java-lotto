package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("Lotto 추가 테스트")
    void testAdd() {
        Lottos lottos = new Lottos(Arrays.asList(new Lotto(), new Lotto()));
        lottos.add(new Lotto());
        assertThat(lottos.lottos().size()).isEqualTo(3);
    }
}
