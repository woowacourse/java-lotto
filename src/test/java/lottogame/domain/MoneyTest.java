package lottogame.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    private Money money;

    @BeforeEach
    void setUp() {
        this.money = new Money(1000);
    }

    @Test
    @DisplayName("Money 생성 테스트")
    void testMoneyCreate() {
        assertThat(money).isEqualTo(new Money(1000));
    }

    @Test
    @DisplayName("Money 사용 테스트")
    void testMoneySpent() {
        money.spent(200);
        assertThat(money).isEqualTo(new Money(800));
    }

    @Test
    @DisplayName("Money 소비 가능 테스트")
    void testMoneyCanSpent() {
        assertThat(money.canBuyAmount(1000)).isTrue();
        assertThat(money.canBuyAmount(1001)).isFalse();
    }

    @Test
    @DisplayName("Money 생성에 음수가 입력되었을 때 예외처리 진행")
    void testMoneyCreateNegativeException() {
        assertThatThrownBy(() -> new Money(-1)).isInstanceOf(IllegalArgumentException.class);
    }

}
