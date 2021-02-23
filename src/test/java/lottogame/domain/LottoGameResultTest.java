package lottogame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameResultTest {

    private LottoGameResult lottoGameResult;

    @BeforeEach
    void setUp() {
        Map<Rank, Integer> matchingMap = new EnumMap<>(Rank.class);
        matchingMap.put(Rank.FAIL, 3);
        matchingMap.put(Rank.RANK1, 2);
        matchingMap.put(Rank.RANK2, 1);
        matchingMap.put(Rank.RANK3, 0);
        this.lottoGameResult = new LottoGameResult(matchingMap);
    }

    @Test
    @DisplayName("총 구입 금액이 일치하는지 확인")
    void testSameMatchCount() {
        assertThat(lottoGameResult.totalInvestment()).isEqualTo(6 * 1000);
    }

    @Test
    @DisplayName("총 획득 상금이 일치하는지 확인")
    void testSameBonusMatch() {
        assertThat(lottoGameResult.totalWinningPrice()).isEqualTo(
            Rank.RANK1.getPrice() * 2 + Rank.RANK2.getPrice()
        );
    }
}
