package lotto;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.money.Money;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;

public class LottosTest {
    @Test
    void 수익률_0프로() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Lottos lottosCollection = new Lottos(lottos);
        WinningLotto winningLotto = WinningLotto.create(Arrays.asList(10, 11, 12, 13, 14, 15), 16);

        Money money = Money.create(3000);
        LottoResult lottoResult = LottoResult.create(money, lottosCollection.getPrizes(winningLotto));
        assertThat(lottoResult.getPercentage()).isEqualTo(0);
    }

    @Test
    void 수익률_25프로() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(new Lotto(Arrays.asList(10, 11, 12, 13, 2, 3)));
        Lottos lottosCollection = new Lottos(lottos);
        WinningLotto winningLotto = WinningLotto.create(Arrays.asList(10, 11, 12, 31, 14, 15), 16);

        Money money = Money.create(4000);

        LottoResult lottoResult = LottoResult.create(money, lottosCollection.getPrizes(winningLotto));
        assertThat(lottoResult.getPercentage()).isEqualTo(1.25, offset(0.009));
    }


}
