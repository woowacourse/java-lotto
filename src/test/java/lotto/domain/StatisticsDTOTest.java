package lotto.domain;

import lotto.view.dto.StatisticsDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.PrizeGroup.FIFTH;
import static lotto.domain.PrizeGroup.SIXTH;
import static org.assertj.core.api.Assertions.assertThat;

class StatisticsDTOTest {

    @DisplayName("상금결과로 통계결과 만들기")
    @Test
    void getPrizeCount() {
        //given
        List<PrizeGroup> prizeGroups = Arrays.asList(FIFTH, FIFTH, FIFTH, FIFTH, FIFTH, FIFTH, SIXTH, SIXTH, SIXTH, SIXTH);
        double expect = 3.0D;

        //when
        StatisticsDTO statisticsDTO = new StatisticsDTO(prizeGroups);
        double result = statisticsDTO.getRate();

        //then
        assertThat(result).isEqualTo(expect);
    }
}
