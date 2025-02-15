package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.NumbersGenerator;
import utils.RandomNumbersGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoStoreTest {

    private NumbersGenerator numbersGenerator = new RandomNumbersGenerator();

    @DisplayName("구입 금액 만큼 로또 구매를 성공한다")
    @Test
    void issueLottosTest() {
        //given
        final Money money1 = new Money(11000);
        final Money money2 = new Money(1000);

        //when
        final LottoStore lottoStore1 = new LottoStore(numbersGenerator, money1);
        final LottoStore lottoStore2 = new LottoStore(numbersGenerator, money2);

        //then
        assertAll(
                () -> assertThat(lottoStore1.issueLottos()).size().isEqualTo(11),
                () -> assertThat(lottoStore2.issueLottos()).size().isEqualTo(1)
        );
    }
}
