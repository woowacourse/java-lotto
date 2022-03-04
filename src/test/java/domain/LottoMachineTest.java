package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    private LottoMachine lottoMachine;
    private WinningLotto winningLotto;

    @BeforeEach
    public void setUp() {
        lottoMachine = new LottoMachine(1000, new AlwaysSameSixNumberGenerator());
        winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    @DisplayName("로또 기계 생성 테스트")
    public void createLottoMachine() {
        assertThat(lottoMachine).isInstanceOf(LottoMachine.class);
    }

    @Test
    @DisplayName("로또 기계 생성시 로또 개수가 입력 금액의 1/1000 인지 확인 테스트")
    public void checkPurchasedLottoCountWhenCreateLottoMachine() {
        lottoMachine = new LottoMachine(14000, new AlwaysSameSixNumberGenerator());
        assertThat(lottoMachine.getLottoTicket().getLottos().size()).isEqualTo(14);
    }

    @Test
    @DisplayName("수익 0에 대한 수익률 테스트")
    public void calculateNoWinningProfit() {
        lottoMachine = new LottoMachine(1000, new AlwaysSameLastSixNumberGenerator());
        double profit = lottoMachine.calculateProfit(new LottoResult(lottoMachine.getLottoTicket(), winningLotto));
        assertThat(profit).isEqualTo(0);
    }

    @Test
    @DisplayName("5등 당첨 수익에 대한 수익률 테스트")
    public void calculateFifthWinningProfit() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 7, 8, 9), 10);
        assertThat(
                lottoMachine.calculateProfit(
                        new LottoResult(lottoMachine.getLottoTicket(), winningLotto))
        ).isEqualTo(5);
    }

    @Test
    @DisplayName("1등 당첨 수익에 대한 수익률 테스트")
    public void calculateFirstWinningProfit() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 10);
        assertThat(
                lottoMachine.calculateProfit(
                        new LottoResult(lottoMachine.getLottoTicket(), winningLotto))
        ).isEqualTo(2000000);
    }

    @Test
    @DisplayName("소수점 셋째 자리에서 내리는지 테스트")
    public void checkCalculatingProfitFlooring() {
        LottoMachine lottoMachine = new LottoMachine(14000, new OnlyFirstNumbersOneToSixGenerator());
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 7, 8, 9), 10);
        assertThat(
                lottoMachine.calculateProfit(
                        new LottoResult(lottoMachine.getLottoTicket(), winningLotto))
        ).isEqualTo(0.35);
    }
}
