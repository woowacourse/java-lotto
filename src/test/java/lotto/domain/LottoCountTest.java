package lotto.domain;

import lotto.exception.ExceedMoneyException;
import lotto.exception.InvalidRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
    private PurchaseMoney money;

    @BeforeEach
    void init(){
        this.money = new PurchaseMoney("15000");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-3"})
    void negativeInputTest(String input){
        assertThatThrownBy(()->{
            LottoCount.create(input, money);
        }).isInstanceOf(InvalidRangeException.class)
                .hasMessageMatching("음수는 입력할 수 없습니다.");
    }

    @Test
    void overNumberManual(){
        assertThatThrownBy(()->{
            LottoCount.create("16",money);
        }).isInstanceOf(ExceedMoneyException.class)
                .hasMessageMatching("15장 이하만 구매가 가능합니다.");
    }

    @Test
    void manualCountTest(){
        assertThat(LottoCount.create("2",money)
                .getManualLottoCount())
                .isEqualTo(2);
    }

    @Test
    void autoCountTest(){
        assertThat(LottoCount.create("2",money)
                .getAutoLottoCount(money))
                .isEqualTo(13);
    }
}
