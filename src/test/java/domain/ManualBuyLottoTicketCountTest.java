package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ManualBuyLottoTicketCountTest {

    @DisplayName("ManualBuyLottoTicketCount 클래스 생성자 유효성 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1000,500", "14000,f", "14000,"})
    void manualBuyLottoTicketCountConstructorTest(String inputMoney, String inputCount) {
        Money money = new Money(inputMoney);
        Assertions.assertThatThrownBy(() ->{
            new ManualBuyLottoTicketCount(money, inputCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
