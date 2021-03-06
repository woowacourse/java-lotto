package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottosTest {
    private static Lottos lottos;
    private static Money money;

    @BeforeEach
    void setUp() {
        money = new Money("3000");
        lottos = new Lottos(money, "3");
    }

    @Test
    @DisplayName("Lottos가 잘 생성되는지 확인")
    void create() {
        final String manualCount = "3";
        assertThatCode(() -> new Lottos(money, manualCount))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("수동로또 개수의 타입이 다를 때 오류를 잘 내는지 확인")
    void validateType() {
        final String wrongType = "test";
        assertThatThrownBy(() -> {
            new Lottos(money, wrongType);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Lottos.NUMBER_TYPE_ERROR);
    }

    @Test
    @DisplayName("수동로또 개수 범위가 다를 때 오류를 잘 내는지 확인")
    void validateRange() {
        final String wrongNumber = "4";
        assertThatThrownBy(() -> {
            new Lottos(money, wrongNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Lottos.NUMBER_RANGE_ERROR);
    }

    @Test
    @DisplayName("수동로또가 잘 생성되는지 확인")
    void generateManualLotto() {
        final List<String> sampleInput = Arrays.asList("1, 2, 3, 4, 5, 6");
        assertThatCode(() -> lottos.generateManualLotto(sampleInput))
                .doesNotThrowAnyException();
    }
}

