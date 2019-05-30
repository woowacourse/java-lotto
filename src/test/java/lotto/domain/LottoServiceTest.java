package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {
    private LottoFactory lottoFactory;

    @BeforeEach
    public void setUp() {
        lottoFactory = new LottoFactory();
    }

    @Test
    public void 구매를_제대로하고_당첨로또에_따른_결과를_제대로_반환해주는지() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);   // BUY
        List<Integer> fifthNumbers = Arrays.asList(42, 2, 3, 4, 10, 11);  //FIFTH
        List<Integer> missNumbers = Arrays.asList(1, 2, 17, 18, 19, 20);  //MISS

        LottoService service = new LottoService(2000);

        service.buy(fifthNumbers);
        while (service.canBuy()) {
            service.buy(missNumbers);
        }

        LottoGameResult gameResult = service.resultOf(lottoFactory.create(winningNumbers), LottoNumber.of(11));
        assertThat(gameResult.profit(LottoMachine.LOTTO_MONEY)).isEqualTo(250);
    }
}