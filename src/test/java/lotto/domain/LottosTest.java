package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottosTest {
    private List<String> manualLotto;

    @BeforeEach
    void setUp() {
        manualLotto = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7", "10,13,15,20,25,40");
    }

    @Test
    void 로또_수동만_여러장_생성() {
        Lottos lottos = new Lottos(manualLotto, 3);
        assertThat(lottos).isEqualTo(new Lottos(manualLotto, 3));
    }

    @Test
    void 수동_개수가_전체_구매보다_큰경우() {
        assertThrows(IllegalArgumentException.class, () -> new Lottos(manualLotto, 2));
    }

    @Test
    void 로또_총개수_확인() {
        Lottos lottos = new Lottos(manualLotto, 10);
        assertThat(lottos.getLottoCount()).isEqualTo(10);
    }
}
