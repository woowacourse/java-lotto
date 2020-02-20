package lotto.view.dto;

import lotto.domain.result.win.prize.PrizeGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.result.win.prize.PrizeGroup.FIFTH;
import static lotto.domain.result.win.prize.PrizeGroup.SIXTH;
import static org.assertj.core.api.Assertions.assertThat;

class StatisticsResponseDTOTest {

    @DisplayName("상금결과로 통계결과 만들기")
    @Test
    void getPrizeCount() {
        //given
        List<PrizeGroup> prizeGroups = Arrays.asList(FIFTH, FIFTH, FIFTH, FIFTH, FIFTH, FIFTH, SIXTH, SIXTH, SIXTH, SIXTH);
        String expect = "300.0%";

        //when
        StatisticsResponseDTO statisticsResponseDTO = new StatisticsResponseDTO(prizeGroups);
        String result = statisticsResponseDTO.getRate();

        //then
        assertThat(result).isEqualTo(expect);
    }
}
