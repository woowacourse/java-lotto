package vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TrialNumberTest {
    @ParameterizedTest(name = "로또 구매 횟수 생성 테스트 - new TrialNumber({0})")
    @ValueSource(ints = {1, 5, 7})
    void trialNumberCreationTest(int trial) {
        // given
        TrialNumber trialNumber = new TrialNumber(trial);

        // when
        int extractedTrialNumber = trialNumber.getTrialNumber();

        // then
        assertThat(extractedTrialNumber).isEqualTo(trial);
    }

    @ParameterizedTest(name = "양수 아닌 값으로 횟수 생성시 IAE 발생 - new TrialNumber({0})")
    @ValueSource(ints = {-1, 0})
    void createTrialNumberWithNegativeOrZeroShouldFail(int trialNumber) {
        assertThatThrownBy(() -> new TrialNumber(trialNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("횟수는 1 보다 작을 수 없습니다 😂");
    }
}
