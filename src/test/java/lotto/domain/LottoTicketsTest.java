package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @DisplayName("LottoTickets 생성 테스트")
    @Test
    void create() {
        Lottos lottos = new Lottos(14);
        assertThat(lottos.getLottos().size()).isEqualTo(14);
    }

    @DisplayName("같은 번호가 생성되었는지 테스트")
    @Test
    void numberSort() {
        Lotto lotto = new Lotto(Arrays.asList(2, 45, 6, 5, 3, 1));
        List<Lotto> manualLotto = Collections.singletonList(lotto);
        Lottos lottos = new Lottos(0, manualLotto);
        List<Lotto> expectedLottoTickets = new ArrayList<>();

        expectedLottoTickets.add(new Lotto(Arrays.asList(1, 2, 3, 45, 5, 6)));
        assertThat(lottos.getLottos()).isEqualTo(expectedLottoTickets);
    }
}
