package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoTest {
    List<Integer> inputNumbers;

    @BeforeEach
    void initiate() {
        inputNumbers = new ArrayList<>();
        inputNumbers.add(1);
        inputNumbers.add(2);
        inputNumbers.add(3);
        inputNumbers.add(4);
        inputNumbers.add(5);
        inputNumbers.add(6);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_입력_생성자_테스트() {
        Assertions.assertThat(new Lotto(inputNumbers))
                .isInstanceOf(Lotto.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 입력의_크기가_정상_범위보다_큰_경우() {
        Assertions.assertThatThrownBy(() -> {
            inputNumbers.add(7);
            new Lotto(inputNumbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lotto number amount must be 6.");
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_범위보다_부족한_경우() {
        Assertions.assertThatThrownBy(() -> {
            inputNumbers.remove(0);
            new Lotto(inputNumbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lotto number amount must be 6.");
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자에_null이_들어온_경우() {
        Assertions.assertThatThrownBy(() -> {
            new Lotto(null);
        }).isInstanceOf(NullPointerException.class);
    }
}
