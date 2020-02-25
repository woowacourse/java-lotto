package lotto.domain;

import lotto.domain.exception.EmptyOrNullException;
import lotto.domain.exception.InvalidInputSizeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("정상 입력 생성자 테스트")
    void lotto() {
        Assertions.assertThat(new Lotto(inputNumbers))
                .isInstanceOf(Lotto.class);
    }

    @Test
    @DisplayName("입력의 크기가 정상 범위보다 큰 경우")
    void lotto_more() {
        Assertions.assertThatThrownBy(() -> { inputNumbers.add(7);
            new Lotto(inputNumbers);
        }).isInstanceOf(InvalidInputSizeException.class)
                .hasMessage("Lotto number amount must be 6.");
    }

    @Test
    @DisplayName("정상 범위보다 부족한 경우")
    void lotto_less() {
        inputNumbers.remove(0);
        Assertions.assertThatThrownBy(() -> {
            new Lotto(inputNumbers);
        }).isInstanceOf(InvalidInputSizeException.class)
                .hasMessage("Lotto number amount must be 6.");
    }

    @Test
    @DisplayName("생성자에 null이 들어온 경우")
    void lotto_null() {
        Assertions.assertThatThrownBy(() -> {
            new Lotto(null);
        }).isInstanceOf(EmptyOrNullException.class)
                .hasMessage("Empty or null lotto exception.");
    }

    @Test
    @DisplayName("size가 0인 입력이 들어온 경우")
    void lotto_empty() {
        Assertions.assertThatThrownBy(() -> {
            new Lotto(new ArrayList<>());
        }).isInstanceOf(EmptyOrNullException.class)
                .hasMessage("Empty or null lotto exception.");
    }
}
