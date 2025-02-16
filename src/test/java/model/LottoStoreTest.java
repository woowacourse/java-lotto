package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.numbers.LottoNumber;
import model.numbers.LottoNumberGenerator;
import model.numbers.LottoNumbers;
import model.numbers.WinningLotto;
import model.rank.LottoRank;
import model.rank.LottoRankCalculator;
import model.rank.LottoRankResult;
import org.junit.jupiter.api.Test;

class LottoStoreTest {

    private LottoStore lottoStore = new LottoStore(new LottoNumberGenerator(), new LottoRankCalculator());

    @Test
    void 당첨결과_개수를_센다() {
        // given
        List<LottoNumbers> lottoNumbers = List.of(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        WinningLotto winningLotto = new WinningLotto(new LottoNumbers(List.of(1, 2, 3, 4, 5, 7)), new LottoNumber(6));

        // when
        LottoRankResult lottoRankResult = lottoStore.calculateRankMatchCount(lottoNumbers, winningLotto);

        // then
        assertThat(lottoRankResult.getCountByRank(LottoRank.SECOND)).isEqualTo(1);
    }
}
