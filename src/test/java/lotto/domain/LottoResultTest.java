package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoResultTest {

    @ParameterizedTest
    @CsvSource(value = {"FIRST:2", "SECOND:1", "THIRD:0", "FOURTH:0",
            "FIFTH:0"}, delimiter = ':')
    @DisplayName("LottoResult에 값이 추가가 되는가?")
    void Add_Rank_To_Result(LottoRank lottoRank, int value) {
        LottoResult lottoResult = new LottoResult();
        lottoResult.add(LottoRank.FIRST);
        lottoResult.add(LottoRank.FIRST);
        lottoResult.add(LottoRank.SECOND);
        Assertions.assertThat(lottoResult.getResult().get(lottoRank)).isEqualTo(value);
    }

    @Test
    @DisplayName("당첨금 총합을 계산하는 기능")
    void Calculate_Sum_Of_Prize() {
        int thirdPrizeAmount = LottoRank.THIRD.getPrizeAmount();
        int fourthPrizeAmount = LottoRank.FOURTH.getPrizeAmount();
        int fifthPrizeAmount = LottoRank.FIFTH.getPrizeAmount();
        int totalAmount = thirdPrizeAmount + fourthPrizeAmount + fifthPrizeAmount;

        LottoResult lottoResult = new LottoResult();
        lottoResult.add(LottoRank.THIRD);
        lottoResult.add(LottoRank.FOURTH);
        lottoResult.add(LottoRank.FIFTH);
        Assertions.assertThat(lottoResult.sumOfPrize()).isEqualTo(totalAmount);
    }
}
