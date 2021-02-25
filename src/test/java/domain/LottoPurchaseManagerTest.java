package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoPurchaseManagerTest {
    private LottoPurchaseManager lottoPurchaseManager;
    @BeforeEach
    void setUp() {
        this.lottoPurchaseManager = new LottoPurchaseManager(new Money(3_000));
    }

    @DisplayName("Budget에서 금액을 차감하고 로또를 자동 구매한다.")
    @Test
    public void purchaseLottoTicketTest() {
        LottoTicket lottoTicket = lottoPurchaseManager.buyAutomatically();
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
        assertThat(lottoPurchaseManager.remainBudget()).isEqualTo(new Money(2_000));
    }

    @DisplayName("로또 숫자의 리스트를 넘기면 Budget에서 금액을 차감하고 로또를 수동 구매한다.")
    @Test
    public void purchaseLottoTicketManuallyTest() {
        LottoTicket lottoTicket = lottoPurchaseManager.buyManually(Arrays.asList(1,2,3,4,5,6));
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
        assertThat(lottoPurchaseManager.remainBudget()).isEqualTo(new Money(2_000));
    }
}
