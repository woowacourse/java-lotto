package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningStatusTest {
    @DisplayName("로또 번호와 당첨 번호의 일치 개수와 보너스 번호와의 일치 여부를 통해 당첨 결과를 구한다")
    @ParameterizedTest
    @CsvSource(value = {"6:false:FIRST", "5:true:SECOND", "5:false:THIRD", "4:false:FOURTH", "3:false:FIFTH", "2:false:NONE", "1:false:NONE", "0:false:NONE"}, delimiter = ':'
    )
    void findWinningStatus(int matchingCount, boolean matchesBonusNumber, WinningStatus expectedStatus) {
        assertThat(WinningStatus.findBy(matchingCount, matchesBonusNumber)).isEqualTo(expectedStatus);
    }

    @DisplayName("로또 번호와 당첨 번호의 일치 개수가 다섯 개일 때를 제외하고 보너스 번호와의 일치 여부를 고려하지 않는다")
    @ParameterizedTest
    @CsvSource(value = {"5:true:SECOND"}, delimiter = ':'
    )
    void findWinningStatusByBonusNumber(int matchingCount, boolean matchesBonusNumber, WinningStatus expectedStatus) {
        assertThat(WinningStatus.findBy(matchingCount, matchesBonusNumber)).isEqualTo(expectedStatus);
    }

    @DisplayName("당첨 결과는 상금을 기준으로 오름차순 정렬한다")
    @Test
    void sortStatusByPrice() {
        List<WinningStatus> sortedWinningStatus = WinningStatus.getSorted();
        for(int i = 0; i < sortedWinningStatus.size() - 1; i++) {
            assertThat(sortedWinningStatus.get(i).getPrice()).isLessThan(sortedWinningStatus.get(i+1).getPrice());
        }
    }
}