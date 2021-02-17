package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @DisplayName("올바른 입력값 확인")
    @Test
    void is_number(){
        Money money = new Money("1000");

        assertThat(money).isEqualTo(new Money("1000"));
    }

    @DisplayName("숫자인지 확인 ")
    @Test
    void is_not_number() {
        assertThatThrownBy(() -> {
            new Money("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1000원 이하 인지 확인 ")
    @Test
    void is_less_1000() {
        assertThatThrownBy(() -> {
            new Money("999");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
