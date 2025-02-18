package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @DisplayName("5개_맞고_보너스_넘버가_없다면_THIRD_반환한다")
    @Test
    void returnThird() {
        assertThat(Rank.find(5, false)).isEqualTo(Rank.THIRD);
    }

    @DisplayName("5개_맞고_보너스_넘버가_있다면_SECOND_반환한다")
    @Test
    void returnSecond() {
        assertThat(Rank.find(5, true)).isEqualTo(Rank.SECOND);
    }

    @DisplayName("순위에 없는 일치 개수는 NO_REWARD 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void returnNoReward(int correctCount) {
        List<Boolean> booleans = List.of(true, false);

        for (Boolean hasBonusNumber : booleans) {
            assertThat(Rank.find(correctCount, hasBonusNumber)).isEqualTo(Rank.NO_REWARD);
        }
    }

    @DisplayName("4개 맞고 보너스 넘버가 맞아도 FOURTH 반환한다.")
    @Test
    void returnFourth(){
        assertThat(Rank.find(4, true)).isEqualTo(Rank.FOURTH);
    }

}
