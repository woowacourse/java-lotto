package lotterymachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLotteryTicketGeneratorTest {
    @Test
    @DisplayName("자동 로또 개수와 생성 전략을 입력 받아, 로또 티켓 리스트를 생성한다.")
    void create() {
        LotteryPurchaseCount lotteryPurchaseCount = new LotteryPurchaseCount(2, 3, 5);
        AutoLotteryTicketGenerator autoLotteryTicketGenerator = new AutoLotteryTicketGenerator(lotteryPurchaseCount.getAutoValue(), new RandomLotteryNumbersGenerator());
        assertThat(autoLotteryTicketGenerator.createLotteryTickets().size()).isEqualTo(lotteryPurchaseCount.getAutoValue());
    }
}
