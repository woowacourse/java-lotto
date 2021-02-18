package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @DisplayName("Lottos를 생성하는 기능")
    @Test
    void generate() {
        //given
        List<Lotto> lottoGroup = new ArrayList();
        lottoGroup.add(new Lotto(new int[]{1, 2, 3, 4, 5, 6}));
        lottoGroup.add(new Lotto(new int[]{7, 8, 9, 10, 11, 12}));
        lottoGroup.add(new Lotto(new int[]{13, 14, 15, 16, 17, 18}));

        //when
        Lottos lottos = new Lottos(lottoGroup);

        //then
        assertThat(lottos).isNotNull();
    }
}
