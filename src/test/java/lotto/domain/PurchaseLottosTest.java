package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PurchaseLottosTest {
    List<Lotto> inputLottoNumbers;

    @BeforeEach
    void initiate() {
        inputLottoNumbers = new ArrayList<>();
        List<Integer> inputNumbers = new ArrayList<>();
        inputNumbers.add(1);
        inputNumbers.add(2);
        inputNumbers.add(3);
        inputNumbers.add(4);
        inputNumbers.add(5);
        inputNumbers.add(6);
        inputLottoNumbers.add(new Lotto(inputNumbers));
    }

    @Test
    @DisplayName("정상 입력 생성자 테스트")
    void purchaseLottos() {
        Assertions.assertThat(new PurchaseLottos(inputLottoNumbers))
                .isInstanceOf(PurchaseLottos.class);
    }

    @Test
    @DisplayName("빈 입력이 들어간 경우")
    void purchaseLottos_empty() {
        Assertions.assertThatThrownBy(() -> {
            new PurchaseLottos(new ArrayList<>());
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Null or empty input to PurchaseLottos.");
    }

    @Test
    @DisplayName("null이 들어간 경우")
    void purchaseLottos_null() {
        Assertions.assertThatThrownBy(() -> {
            new PurchaseLottos(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Null or empty input to PurchaseLottos.");
    }
}
