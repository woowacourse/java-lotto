package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_입력_생성자_테스트() {
        Assertions.assertThat(new PurchaseLottos(inputLottoNumbers))
                .isInstanceOf(PurchaseLottos.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자에_빈_입력이_들어간_경우() {
        Assertions.assertThatThrownBy(() -> {
            new PurchaseLottos(new ArrayList<>());
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Null or empty input to PurchaseLottos.");
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자에_null이_들어온_경우() {
        Assertions.assertThatThrownBy(() -> {
            new PurchaseLottos(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Null or empty input to PurchaseLottos.");
    }
}
