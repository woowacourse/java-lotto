import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void 음수이면_예외(){
        // given
        String input = "-1000";

        // when & then
        assertThatThrownBy(()-> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void _0이면_예외(){
        // given
        String input = "0";

        // when & then
        assertThatThrownBy(()-> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 숫자가_아니면_예외(){
        // given
        String input = "woowa";

        // when & then
        assertThatThrownBy(()-> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}