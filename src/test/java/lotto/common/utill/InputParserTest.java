package lotto.common.utill;

import static lotto.common.utill.InputParser.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    void testParseToInt() {
        String str = "1000";
        assertThat(parseToInt(str)).isEqualTo(1000);
    }

    @Test
    void testParseToIntError() {
        String str = "1000익";
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testBlankError() {
        String str = " ";
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testNullError() {
        String str = "";
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class);
    }
}