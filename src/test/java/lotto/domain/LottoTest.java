package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void validateDistinctNumberTest_중복숫자가_있을_때() {
        List<Integer> invalidNumbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 5));
        assertThatThrownBy(() -> Lotto.validateDistinctNumbers(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되는 로또 번호가 존재합니다.");
    }


}
