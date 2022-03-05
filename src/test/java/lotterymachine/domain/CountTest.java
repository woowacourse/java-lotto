package lotterymachine.domain;

import lotterymachine.domain.vo.Count;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CountTest {

    @Test
    @DisplayName("Money를 입력 받아 총 로또 구매 개수를 표현하는 객체 Count를 생성한다.")
    void create() {
        Count count = new Count(3, 14);
        assertThat(count.getAutoValue()).isEqualTo(3);
        assertThat(count.getPassivityValue()).isEqualTo(11);
    }

    @Test
    @DisplayName("수동 구매 개수가 총 로또 구매 개수 보다 높을 시, 에러가 발생한다.")
    void validatePassivityValue() {
        assertThatThrownBy(() -> {
            Count count = new Count(3, 2);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수동 로또 개수가 총 구매 개수 보다 높습니다.");
    }
}
