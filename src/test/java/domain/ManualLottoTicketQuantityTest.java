package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ManualLottoTicketQuantityTest {

    @DisplayName("ManualLottoTicketQuantity 클래스 생성자 유효성 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1000,500", "14000,f", "14000,"})
    void manualBuyLottoTicketCountConstructorTest(String inputMoney, String inputQuantity) {
        Money money = new Money(inputMoney);
        Assertions.assertThatThrownBy(() -> {
            new ManualLottoTicketQuantity(money, inputQuantity);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("자동 로또 티켓 수량을 구하는 메서드 테스트")
    @ParameterizedTest
    @CsvSource(value = {"14000,4", "20000,10", "100000,50"})
    void getAutoLottoTicketQuantityTest(String inputMoney, String inputManualQuantity) {
        Money money = new Money(inputMoney);
        ManualLottoTicketQuantity manualLottoTicketQuantity = new ManualLottoTicketQuantity(money, inputManualQuantity);

        int manualQuantity = manualLottoTicketQuantity.getManualLottoTicketQuantity();
        int autoQuantity = manualLottoTicketQuantity.getAutoLottoTicketQuantity(money);

        Assertions.assertThat(Integer.parseInt(inputMoney)).isEqualTo((manualQuantity + autoQuantity) * 1_000);
    }
}
