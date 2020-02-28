package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ManualLottoTicketCountTest {
    @DisplayName("입력한 수동 로또 구매의 수가 유효하지 않은 값일 때 예외 출력 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10, 11", "5, -1"}, delimiter = ',')
    void manualLottoTicketCountConstructorTest(int allLottoTicketCount, int manualLottoTicketCount) {
        Assertions.assertThatThrownBy(() -> {
            new ManualLottoTicketCount(allLottoTicketCount, manualLottoTicketCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
