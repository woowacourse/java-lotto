package domain;

import static domain.exception.ExceptionMessage.INVALID_POSITIVE;
import static domain.exception.ExceptionMessage.INVALID_UNIT_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @Nested
    class 금액_검증_테스트 {
        @ParameterizedTest
        @ValueSource(ints = {-5, 0})
        void 금액이_양수가_아니면_예외가_발생한다(int price) {
            assertThatThrownBy(() -> {
                new Money(price);
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_POSITIVE.getMessage());
        }

        @ParameterizedTest
        @ValueSource(ints = {1500, 1234})
        void 금액이_1000원_단위가_아니면_예외가_발생한다(int price) {
            assertThatThrownBy(() -> {
                new Money(price);
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_UNIT_PRICE.getMessage());
        }
    }

    @Test
    void 수익률을_계산할_수_있다() {
        //given
        Money money = new Money(14000);
        EnumMap<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
        rankCount.put(Rank.FIFTH, 1);

        //when-then
        assertThat(money.calculateProfit(rankCount)).isEqualTo(0.35);
    }

    @Test
    void 로또를_구매할_수_있다() {
        //given
        Money money = new Money(14000);

        //when-then
        assertThat(money.buyLottos(() -> List.of(1, 2, 3, 4, 5, 6))
                .getLottos()
                .size()).isEqualTo(14);
    }
}
