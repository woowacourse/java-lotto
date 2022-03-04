package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class RankTest {

    @ParameterizedTest
    @CsvSource(value = {"3,false,FIFTH","4,false,FOURTH","5,false,THIRD","5,true,SECOND","6,false,FIRST"})
    @DisplayName("당첨 숫자의 개수, 보너스볼 당첨 여부로 당첨 순위 계산 테스트")
    public void rankValueOfTest(int count, boolean hasBonusBall, String rankName) {
        Rank rank = Rank.valueOf(count, hasBonusBall);

        assertEquals(Rank.valueOf(rankName).equals(rank), true);
    }
}
