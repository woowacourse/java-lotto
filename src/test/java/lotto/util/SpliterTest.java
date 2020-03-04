package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SpliterTest {

    @Test
    @DisplayName("문자열로 된 사용자 입력 분리 테스트")
    void splitInput() {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);
        assertThat(Spliter.splitInput("1,2 ,3")).isEqualTo(result);
    }

    @Test
    @DisplayName("숫자 외 입력 예외처리 테스트")
    void splitAndCheckInteger() {
        assertThatThrownBy(() -> Spliter.splitInput("d,3,2"))
                .isInstanceOf(NumberFormatException.class);
    }
}
