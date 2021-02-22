package lotto.domain;

import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.lottomachine.TestLottoMachine;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoServiceTest {
    LottoMachine lottoMachine;
    LottoService lottoService;

    @BeforeEach
    void setup() {
        lottoMachine = new TestLottoMachine();
        lottoService = new LottoService(lottoMachine);
    }

    @Test
    @DisplayName("로또 구매 결과 확인")
    void buyLottoCheck() {
        Ticket ticket = new Ticket(new Money(14000));
        lottoService.generateLottos(ticket);
        List<Integer> lottoNumbers = lottoMachine.generate()
                                                 .stream()
                                                 .map(LottoNumber::getNumber)
                                                 .collect(Collectors.toList());

        List<Lotto> lottos = lottoService.getLottos();
        assertThat(lottos.get(0)
                         .getNumbers()).isEqualTo(lottoNumbers);
        assertThat(lottos.size()).isEqualTo(14);
    }

    @Test
    @DisplayName("수동 로또 구매하기")
    void manualLottoCheck() {
        List<Integer> expected = Arrays.asList(1,2,3,4,5,6);
        Lotto lotto = Lotto.createByInteger(expected);
        lottoService.generateManualLotto(lotto);
        assertThat(lottoService.getLottos().get(0).getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또 긁은 내역 확인")
    void scratchLottoCheck() {
        Ticket ticket = new Ticket(new Money(2000));
        Lotto lotto = new Lotto(lottoMachine.generate());

        lottoService.generateLottos(ticket);
        WinningLotto winningLotto = new WinningLotto(lotto, new LottoNumber(7));
        lottoService.scratchLotto(winningLotto);
        RatingCounter ratingCounter = lottoService.getRatingCounter();

        assertThat(ratingCounter.get(Rating.FIRST)).isEqualTo(2);
    }

    @Test
    void getEarningRate() {
        double actual = lottoService.getEarningRate(new Money(5000));
        assertThat(actual).isEqualTo(0);
    }

    @Test
    void getTotalSum() {
        RatingCounter ratingCounter = lottoService.getRatingCounter();
        ratingCounter.update(Rating.FIRST);
        assertThat(ratingCounter.totalSum()).isEqualTo(2000000000);
    }
}
