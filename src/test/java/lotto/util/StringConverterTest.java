package lotto.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringConverterTest {
    @Test
    void 정상_수열_처리() {
        assertThat(StringConverter.toNumbers("1,2,3, 4,  5"))
                .isEqualTo(Arrays.asList(1, 2, 3, 4, 5));
    }

    @Test
    void 비정상_수열() {
        assertThrows(NumberFormatException.class, () -> {
            StringConverter.toNumbers("1, a, 3,4, 5");
        });
    }
}