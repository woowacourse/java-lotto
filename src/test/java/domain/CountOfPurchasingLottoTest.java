package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CountOfPurchasingLottoTest {

    @DisplayName("CountOfLotto 객체를 생성하는 기능")
    @Test
    void generateCountOfLotto() {
        //given
        int lottoNumber = 3;

        //when
        CountOfPurchasingLotto countOfPurchasingLotto = new CountOfPurchasingLotto(lottoNumber);

        //then
        assertThat(countOfPurchasingLotto).isNotNull();
    }

    @DisplayName("CountOfLotto의 값을 1 감소시키는 기능")
    @Test
    void purchaseLotto() {
        //given
        CountOfPurchasingLotto countOfPurchasingLotto = new CountOfPurchasingLotto(2);

        //when
        countOfPurchasingLotto.purchaseLotto();

        //then
        assertThat(countOfPurchasingLotto).isEqualTo(new CountOfPurchasingLotto(1));
    }
}
