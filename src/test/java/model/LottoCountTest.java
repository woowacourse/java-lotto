package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoCountTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings ={"","  ","\t","\n"})
    @DisplayName("투입 금액 공백 검증")
    void validateLottoNumber(String number) {
        assertThatThrownBy(() -> new LottoCount(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings ={"azpi","++","greeanlawn","1dksl","-1"})
    @DisplayName("투입 금액이 숫자가 아닌 경우 검증")
    void validateInputMoneyIsNumber(String number) {
        assertThatThrownBy(() -> new LottoCount(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"100","1001","100001"})
    @DisplayName("투입 금액이 천원 단위가 아닌 경우인")
    void validateNotThousandUnitInputMoney(String number) {
        assertThatThrownBy(() -> new LottoCount(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("투입 금액이 천원 단위 경우")
    void validateThousandUnitInputMoney() {
        LottoCount lottoCount = new LottoCount("1000");
        assertThat(lottoCount.getMoney()).isEqualTo(1000);
    }
}
