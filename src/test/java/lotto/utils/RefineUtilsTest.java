package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefineUtilsTest {

    @DisplayName("입력된 문자열에 정수만 적혀있으면 정수를 반환한다.")
    @Test
    void refine() {
        String value = "3";
        String value2 = "d";
        String value3 = " 5  ";
        String value4 = "";

        assertThat(RefineUtils.refineIntegerValue(value)).isEqualTo(3);
        assertThatIllegalArgumentException()
            .isThrownBy(() -> RefineUtils.refineIntegerValue(value2));
        assertThat(RefineUtils.refineIntegerValue(value3)).isEqualTo(5);
        assertThatIllegalArgumentException()
            .isThrownBy(() -> RefineUtils.refineIntegerValue(value4));
    }

}