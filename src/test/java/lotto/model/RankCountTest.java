package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankCountTest {

    private RankCount rankCount;

    @BeforeEach
    void init() {
        Map<Rank, Integer> rankCountMap = new LinkedHashMap<>();
        rankCountMap.put(Rank.FIRST, 2);
        rankCountMap.put(Rank.SECOND, 0);
        rankCountMap.put(Rank.THIRD, 0);
        rankCountMap.put(Rank.FOURTH, 1);
        rankCountMap.put(Rank.FIFTH, 0);
        rankCountMap.put(Rank.LOSER, 0);
        rankCount = new RankCount(rankCountMap);
    }

    @DisplayName("RankCount 정상 생성 테스트")
    @Test
    void ScoreTest() {
        assertThat(rankCount).isInstanceOf(RankCount.class);
    }

    @DisplayName("당첨금 합계 테스트")
    @Test
    void addTotalScoreTest() {
        long actual = rankCount.gatherTotalMoney();
        System.out.println(actual);
        assertThat(actual).isEqualTo(4000000000L + 50000);
    }
}
