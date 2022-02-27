package lotto.domain;


import lotto.MakeEachRankStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class LottoTest {
    private Money money;
    private Lotto lotto;
    private LottoResult lottoResult;

    @BeforeEach
    void setLotto() {
        money = new Money("10000");
        MakeEachRankStrategy makeEachRankStrategy = new MakeEachRankStrategy();
        lotto = new Lotto(money, makeEachRankStrategy);
        ChoiceNumber choiceNumber = new ChoiceNumber("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7", choiceNumber);
        WinningNumber winningNumber = new WinningNumber(choiceNumber, bonusNumber);
        lottoResult = lotto.computeResult(winningNumber);
    }

    @ParameterizedTest
    @DisplayName("당첨번호와 갖고있는 로또들을 잘 비교하는가?")
    @EnumSource(LottoRank.class)
    void Compute_Rank_Result(LottoRank lottoRank) {
        Assertions.assertThat(lottoResult.getResult().get(lottoRank)).isEqualTo(1);
    }

    @Test
    @DisplayName("모든 등수가 한번씩 당첨됐을 때 수익률을 정상적으로 계산하는가?")
    void Compute_Yield() {
        int totalPrize = 0;
        for (LottoRank value : LottoRank.values()) {
            totalPrize += value.getPrizeAmount();
        }
        Assertions.assertThat(lotto.getYield()).isEqualTo(totalPrize / money.getAmount());
    }
}
