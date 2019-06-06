package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoVendingMachineTest {
    Money money;
    LottoOMRCard lottoOMRCard;
    LottoVendingMachine lottoVendingMachine;
    Lotto customLotto1;
    Lotto customLotto2;
    Lotto customLotto3;
    Lotto customLotto4;

    @BeforeEach
    void setUp() {
        money = new Money(4000);
        lottoOMRCard = new LottoOMRCard();
        lottoOMRCard.addCustomLotto("1,2,3,4,5,6".split(","));
        lottoOMRCard.addCustomLotto("1,2,3,4,5,7".split(","));
        lottoOMRCard.addCustomLotto("1,2,3,4,5,8".split(","));
        lottoOMRCard.addCustomLotto("1,2,3,4,5,9".split(","));

        lottoVendingMachine = new LottoVendingMachine();

        customLotto1 = new CustomLottoGenerator("1,2,3,4,5,6".split(",")).makeLotto();
        customLotto2 = new CustomLottoGenerator("1,2,3,4,5,7".split(",")).makeLotto();
        customLotto3 = new CustomLottoGenerator("1,2,3,4,5,8".split(",")).makeLotto();
        customLotto4 = new CustomLottoGenerator("1,2,3,4,5,9".split(",")).makeLotto();
    }

    @Test
    void 생성테스트() {
        LottoPaper lottoPaper = new LottoPaper(Arrays.asList(customLotto1, customLotto2, customLotto3, customLotto4));
        assertThat(lottoVendingMachine.buyLotto(money, lottoOMRCard).equals(lottoPaper));
    }
}