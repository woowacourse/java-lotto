package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("로또 개수가 음수이면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(ints = {
            -1, -2, -3, -4, -10
    })
    void generateWithNegativeCountOfPurchasingLottoValue(int lottoNumber) {
        //when //then
        assertThatThrownBy(() -> new CountOfPurchasingLotto(lottoNumber))
                .isExactlyInstanceOf(IllegalArgumentException.class);
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

    @DisplayName("구입할 로또 개수가 있는지 확인하는 기능")
    @ParameterizedTest
    @CsvSource(value = {
            "1:true", "2:true", "0:false"
    }, delimiter = ':')
    void isPurchasingLottoLeft(int needLottoNumber, boolean expected) {
        //give
        CountOfPurchasingLotto countOfPurchasingLotto = new CountOfPurchasingLotto(needLottoNumber);

        //when
        boolean actual = countOfPurchasingLotto.isPurchasingLottoLeft();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
