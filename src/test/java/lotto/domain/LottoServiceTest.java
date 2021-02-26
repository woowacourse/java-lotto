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
        lottoService = new LottoService(lottoMachine, new Ticket(new Money(2000)));
    }

    @Test
    @DisplayName("로또 구매 결과 확인")
    void buyLottoCheck() {
        LottoService lottoService = new LottoService(lottoMachine, new Ticket(new Money(14000)));
        lottoService.generateLottos();
        List<Integer> lottoNumbers = lottoMachine.generate()
                                                 .stream()
                                                 .map(LottoNumber::intValue)
                                                 .collect(Collectors.toList());

        List<Lotto> lottos = lottoService.getLottos();
        assertThat(lottos.get(0)
                         .getNumbers()).isEqualTo(lottoNumbers);
        assertThat(lottos.size()).isEqualTo(14);
    }

    @Test
    @DisplayName("수동 로또 구매하기")
    void manualLottoCheck() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        lottoService.addLotto(Lotto.of(expected));
        assertThat(lottoService.getLottos()
                               .get(0)
                               .getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또 긁은 내역 확인")
    void scratchLottoCheck() {
        Lotto lotto = new Lotto(lottoMachine.generate());
        lottoService = new LottoService(lottoMachine, new Ticket(new Money(2000)));

        lottoService.generateLottos();
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.valueOf(7));
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
