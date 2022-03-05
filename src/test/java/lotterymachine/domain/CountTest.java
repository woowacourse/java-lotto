package lotterymachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CountTest {

    @Test
    @DisplayName("Money를 입력 받아 총 로또 구매 개수를 표현하는 객체 Count를 생성한다.")
    void create() {
        Money money = new Money(14000);
        Count count = new Count(3, 14);
        assertThat(count.getAutoValue()).isEqualTo(3);
        assertThat(count.getPassivityValue()).isEqualTo(11);
    }
}
