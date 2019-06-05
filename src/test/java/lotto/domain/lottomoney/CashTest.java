package lotto.domain.lottomoney;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CashTest {
    private Cash cash;

    @BeforeEach
    void setUp() {
        cash = new Cash(3550);
    }

    @Test
    void 같은_정수로_현금_객체_생성_후_비교하기() {
        assertThat(cash).isEqualTo(new Cash(3550));
    }

    @Test
    void 현금에_대한_최소_조건을_만족하는지() {
        assertThrows(InvalidLottoPriceException.class, () -> {
            new Cash(0);
        });
    }

    @Test
    void 입력된_가격과_비교해서_더_적은_금액인지_알려주는_메소드() {
        assertThat(cash.isLittleThan(4000)).isTrue();
        assertThat(cash.isLittleThan(3500)).isFalse();
    }

    @Test
    void 입력된_가격에_대해_몫을_계산하는_메소드() {
        assertThat(cash.calculateQuotient(1000)).isEqualTo(3);
    }

    @Test
    void 입력된_가격에_대해_나머지를_구하는_메소드() {
        assertThat(cash.calculateRemainder(1000)).isEqualTo(550);
    }

    @AfterEach
    void tearDown() {
        cash = null;
    }
}