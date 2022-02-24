package domain;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
import domain.player.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessage;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
=======
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessage;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.*;
>>>>>>> 1f14e05 (feat: Money 객체 생성)
=======
>>>>>>> 94c4d43 (style: 코드 포멧팅)

class MoneyTest {

    @Test
    @DisplayName("1000원 미만이면 예외가 발생한다.")
<<<<<<< HEAD
<<<<<<< HEAD
    void create_1000원_미만_예외_테스트() {
        int lessThan1000 = 100;
        assertThatThrownBy(() -> new Money(lessThan1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_ENOUGH_MONEY);
=======
    void create_1000원_미만_예외_테스트(){
=======
    void create_1000원_미만_예외_테스트() {
>>>>>>> 94c4d43 (style: 코드 포멧팅)
        int lessThan1000 = 100;
        assertThatThrownBy(() -> new Money(lessThan1000))
                .isInstanceOf(IllegalArgumentException.class)
<<<<<<< HEAD
                .hasMessage(Money.NOT_ENOUGH_MONEY);
>>>>>>> 1f14e05 (feat: Money 객체 생성)
=======
                .hasMessage(ExceptionMessage.NOT_ENOUGH_MONEY);
>>>>>>> d722001 (refactor: 예외 메세지 별도 클래스로 분리)
    }

}