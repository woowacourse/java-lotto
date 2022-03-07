package lotto.util;

import java.util.ArrayList;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputConvertorTest {

    @Test
    @DisplayName("하나의 입력값을 정수로 반환하여 반환한다.")
    void toIntForOne() {
        Assertions.assertThat(InputConvertor.toInt("-100")).isEqualTo(-100);
    }

    @Test
    @DisplayName("여러개의 입력값 리스트를 정수로 변환하여 반환한다.")
    void toIntForList() {
        Assertions.assertThat(InputConvertor.toInt(new ArrayList<>(Arrays.asList("-1", "0", "1"))))
                .containsExactly(-1, 0, 1);
    }

    @Test
    @DisplayName("입략값을 split하여 리스트로 반환한다.")
    void splitInput() {
        Assertions.assertThat(InputConvertor.splitInput("1, 2, 3, 4, 5, 6", ","))
                .containsExactly("1", "2", "3", "4", "5", "6");
    }
}
