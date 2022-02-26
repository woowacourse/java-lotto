package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lotto lotto1;
    private Lotto lotto2;
    private List<Lotto> lottoList;

    @BeforeEach
    void init() {
        lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        lottoList = new ArrayList<>(List.of(lotto1, lotto2));
    }

    @Test
    @DisplayName("Lottos에 Lotto 저장 테스트")
    void lottosSaveTest() {
        Lottos lottos = new Lottos(lottoList);
        assertThat(lottos.getLottos()).containsExactly(lotto1, lotto2);
    }
}