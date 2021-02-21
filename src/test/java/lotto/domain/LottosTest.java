package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    private Lotto lotto1;
    private Lotto lotto2;

    @BeforeEach
    void setUp() {
        lotto1 = new Lotto(Arrays.asList(1, 2, 3, 20, 21, 40));
        lotto2 = new Lotto(Arrays.asList(1, 2, 20, 25, 29, 45));

    }

    @DisplayName("수동, 자동 로또 생성 확인")
    @Test
    void createLottos(){
        List<Lotto> manualLotto = Arrays.asList(lotto1, lotto2);

        Lottos lottos = new Lottos(manualLotto, 0);

        assertThat(lottos).isEqualTo(new Lottos(Arrays.asList(lotto1, lotto2),0));
    }



}
