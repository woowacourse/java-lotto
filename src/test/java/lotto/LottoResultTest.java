package lotto;

import lotto.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;

public class LottoResultTest {
    @Test
    void  수익률_0프로() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        lottos.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        lottos.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        Lottos lottosCollection = new Lottos(lottos);

        Lotto winningLottoTicket = new Lotto(Arrays.asList(10,11,12,13,14,15));
        LottoNumber bonusNumber = new LottoNumber(16);
        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);

        Money money = new Money("3000");
        LottoResult lottoResult = new LottoResult(money, lottosCollection.getPrizes(winningLotto));
        assertThat(lottoResult.calculateProfitRate()).isEqualTo(0);
    }

    @Test
    void  수익률_25프로() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        lottos.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        lottos.add(new Lotto(Arrays.asList(1,2,3,4,5,6)));
        lottos.add(new Lotto(Arrays.asList(10,11,12,13,2,3)));
        Lottos lottosCollection = new Lottos(lottos);

        Lotto winningLottoTicket = new Lotto(Arrays.asList(10,11,12,17,14,15));
        LottoNumber bonusNumber = new LottoNumber(16);
        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);

        Money money = new Money("4000");

        LottoResult lottoResult = new LottoResult(money, lottosCollection.getPrizes(winningLotto));
        assertThat(lottoResult.calculateProfitRate()).isEqualTo(1.25,offset(0.009));
    }
}
