package domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoRankTest {

    @DisplayName("5개의 숫자가 일치하고, 보너스 번호가 일치하면 2등을 반환한다.")
    @Test
    void lottoRankFindMatches() {
        //given
        int matches = 5;
        boolean hasBonus = true;

        //when
        LottoRank findRank = LottoRank.findRankByBonusAndMatches(hasBonus, matches);

        //then
        Assertions.assertThat(findRank).isEqualTo(LottoRank.FIVE_AND_BONUS_MATCHES);
    }

    @DisplayName("4개의 숫자가 일치하고, 보너스 번호와 상관없이 3등을 반환한다.")
    @Test
    void lottoRankFourthMatches() {
        //given
        int matches = 4;
        boolean hasBonus = true;

        //when
        LottoRank findRank = LottoRank.findRankByBonusAndMatches(hasBonus, matches);
        LottoRank findRankWithBonus = LottoRank.findRankByBonusAndMatches(!hasBonus, matches);

        //then
        Assertions.assertThat(findRank).isEqualTo(LottoRank.FOUR_MATCHES);
        Assertions.assertThat(findRankWithBonus).isEqualTo(LottoRank.FOUR_MATCHES);
    }
}