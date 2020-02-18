package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Test
    @DisplayName("로또번호 중복검사")
    void validateDuplication() {
        List<Integer> input1 = Arrays.asList(1, 2, 3, 4, 5, 5);
        List<Integer> input2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> Lotto.validateDuplication(input1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatCode(() ->
            Lotto.validateDuplication(input2)
        ).doesNotThrowAnyException();
    }
}
