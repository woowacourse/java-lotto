package lotto.domain;

import lotto.domain.exception.ExceedBoughtLottosAboutMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static lotto.domain.LottoNumber.getLottoNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoughtLottosTest {

    BoughtLottos boughtLottos;

    @BeforeEach
    void setUp() {
        boughtLottos = new BoughtLottos(Arrays.asList(
                new Lotto(Arrays.asList(getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                        getLottoNumber(4), getLottoNumber(5), getLottoNumber(6))),
                new Lotto(Arrays.asList(getLottoNumber(5), getLottoNumber(6), getLottoNumber(10),
                        getLottoNumber(14), getLottoNumber(25), getLottoNumber(42))),
                new Lotto(Arrays.asList(getLottoNumber(11), getLottoNumber(23), getLottoNumber(25),
                        getLottoNumber(29), getLottoNumber(33), getLottoNumber(15)))
        ), 0);
    }

    @Test
    void 생성() {
        assertThat(boughtLottos).isEqualTo(
                new BoughtLottos(Arrays.asList(
                    new Lotto(Arrays.asList(getLottoNumber(1), getLottoNumber(2), getLottoNumber(3),
                            getLottoNumber(4), getLottoNumber(5), getLottoNumber(6))),
                    new Lotto(Arrays.asList(getLottoNumber(5), getLottoNumber(6), getLottoNumber(10),
                            getLottoNumber(14), getLottoNumber(25), getLottoNumber(42))),
                    new Lotto(Arrays.asList(getLottoNumber(11), getLottoNumber(23), getLottoNumber(25),
                            getLottoNumber(29), getLottoNumber(33), getLottoNumber(15)))
                ), 0));
    }

    @Test
    void 금액_14000원인_경우_14개의_로또가_생성되는지_테스트() {
        assertThat(BoughtLottos.buyLottos(new Money(14000), Collections.EMPTY_LIST).getLottos().size()).isEqualTo(14);
    }

    @Test
    void 입력가격보다_수동_생성_로또가_더_많은_경우_테스트() {
        assertThrows(ExceedBoughtLottosAboutMoneyException.class, () -> {
            BoughtLottos.buyLottos(new Money(3000), Arrays.asList(
                    "1, 2, 3, 4, 5, 6", "10, 21, 32, 24, 35, 16", "12, 21, 30, 4, 45, 6",
                    "1, 2, 3, 4, 5, 6", "10, 21, 32, 24, 35, 16", "12, 21, 30, 4, 45, 6"));
        });
    }
}
