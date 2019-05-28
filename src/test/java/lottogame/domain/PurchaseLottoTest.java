package lottogame.domain;

import lottogame.utils.InvalidLottoPriceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseLottoTest {
    @Test
    void 금액이_잘못되었으면_예외를_발생하는지_테스트() {
        assertThrows(InvalidLottoPriceException.class,()-> new PurchaseLotto(999));
    }

    @Test
    void 금액에_맞게_생성되는지_테스트() {
        PurchaseLotto purchaseLotto = new PurchaseLotto(10000);
        System.out.println(purchaseLotto);
    }
}
