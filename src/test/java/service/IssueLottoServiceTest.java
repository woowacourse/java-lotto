package service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import dto.IssuedLottosDto;
import exception.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class IssueLottoServiceTest {
    private final IssueLottoService issueLottoService = new IssueLottoService();

    @DisplayName("정상 구매 개수 테스트")
    @ParameterizedTest
    @CsvSource({"14000,14","1000,1","1500000,1500"})
    void buyLottoTest(int money, int expectedCount){
        IssuedLottosDto lottosDto = issueLottoService.issueLottos(money);
        assertThat(lottosDto.lottos().size()).isEqualTo(expectedCount);
    }

    @DisplayName("금액 범위 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0,-1000})
    void buyLottoRangeExceptionTest(int money){
        assertThatThrownBy(()->issueLottoService.issueLottos(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PRICE_RANGE_ERROR.getMessage());
    }

    @DisplayName("금액 단위 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,1103,15001})
    void buyLottoUnitExceptionTest(int money){
        assertThatThrownBy(()->issueLottoService.issueLottos(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PRICE_UNIT_ERROR.getMessage());
    }
}