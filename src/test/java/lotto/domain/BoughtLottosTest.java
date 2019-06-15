package lotto.domain;

import lotto.domain.exception.ExceedBoughtLottosAboutMoneyException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoughtLottosTest {

    public static BoughtLottos boughtLottos = new BoughtLottos(Arrays.asList(
            new Lotto(generateLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6))),
            new Lotto(generateLottoNumbers(Arrays.asList(3, 4, 5, 6, 7, 8)))
    ), 2);

    @Test
    void 생성() {
        assertThat(boughtLottos).isEqualTo(
                new BoughtLottos(Arrays.asList(
                        new Lotto(generateLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6))),
                        new Lotto(generateLottoNumbers(Arrays.asList(3, 4, 5, 6, 7, 8)))
                ), 2));
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
