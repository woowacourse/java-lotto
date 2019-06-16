package lotto.domain.lotto;

import lotto.domain.*;
import lotto.domain.vo.LottoResult_VO;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class lottoResult_Test {

    @Test
    void 매칭_테스트() {
        Price price = new Price("8000");
        NumberOfCustomLotto amount = new NumberOfCustomLotto("5",price);
        List<String> customLotto = new ArrayList<>(Arrays.asList(
                "1,2,3,4,11,12", "1,2,3,10,11,12", "1,2,3,10,11,13", "1,2,9,10,11,13", "1,2,19,20,21,22"
        ));
        LottoTicket ticket = new LottoTicket(amount, customLotto);
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", "7");

        LottoResult_VO lottoResult_vo = new LottoResult_VO(new LottoResult(ticket, winningLotto).matchLotto(), price);
        assertThat(lottoResult_vo.getResult().get(Rank.FOURTH)).isEqualTo(1);
        assertThat(lottoResult_vo.getResult().get(Rank.FIFTH)).isEqualTo(2);
        assertThat(lottoResult_vo.getResult().get(Rank.MISS)).isEqualTo(5);

        assertThat(sum(lottoResult_vo)).isEqualTo(60_000);
    }

    private static double sum(LottoResult_VO lottoResult) {
        return lottoResult.getResult().keySet()
                .stream()
                .map(rank -> rank.getWinningMoney() * lottoResult.getResult().get(rank))
                .reduce(0, Integer::sum);
    }

}
