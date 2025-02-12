package domain;




import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또_번호_범위_테스트")
    @Test
    void rangeTest() {
        Assertions.assertThatThrownBy(() -> new Lotto(List.of(0,1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("로또 번호 범위 오류");
    }
    @DisplayName("로또 번호 중복 테스트")
    @Test
    void duplicateTest() {
        Assertions.assertThatThrownBy(() -> new Lotto(List.of(1,1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("로또 번호 중복 오류");
    }
    @DisplayName("로또 번호 갯수 테스트")
    @Test
    void sizeTest() {
        Assertions.assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,6,7)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("로또 번호 갯수 오류");
    }

}