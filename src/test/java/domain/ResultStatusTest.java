package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultStatusTest {
    private ResultStatus resultStatus;

    @BeforeEach
    void setUp() {
        resultStatus = new ResultStatus();
    }

    @Test
    @DisplayName("Result의 결과에 따라 기록에 잘 반영이 되는지 확인한다.")
    void updateRecordTest() {
        Map<Rank, Integer> resultStatistics = resultStatus.getResultStatistics();
        resultStatus.updateRecord(Rank.FIRST);

        Integer actual = resultStatistics.get(Rank.FIRST);
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("Result 결과들에 따라 상금 계산을 잘 하는지 확인한다.")
    void calculateTotalIncomeTest() {
        Map<Rank, Integer> resultStatistics = resultStatus.getResultStatistics();
        resultStatus.updateRecord(Rank.FIRST);

        double actual = resultStatus.calculateTotalIncome();
        assertThat(actual).isEqualTo((double) Rank.FIRST.getReward());
    }
}
