import static org.assertj.core.api.Assertions.assertThat;
<<<<<<< HEAD
=======
import static org.assertj.core.api.Assertions.assertThatThrownBy;
>>>>>>> b694d594de2bfb389fd414a7cf2d9a0ea23d3c9b

import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
<<<<<<< HEAD
=======
    @DisplayName("생성자에 string숫자가 들어간다")
    void make_money() {
        Money money = new Money("12000");

        assertThat(money.money()).isEqualTo(12000);
    }

    @Test
    @DisplayName("생성자에 숫자가 아닌 문자가 들어간다")
    void make_money2() {
                assertThatThrownBy(() ->
                        new Money("abdd12")
                ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
>>>>>>> b694d594de2bfb389fd414a7cf2d9a0ea23d3c9b
    @DisplayName("생성자에 integer숫자가 들어간다")
    void make_money3() {
        Money money = new Money(12000);
        assertThat(money.money()).isEqualTo(12000);
    }
}
