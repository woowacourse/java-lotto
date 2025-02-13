package domain;




import static org.assertj.core.api.Assertions.*;

import exception.ErrorMessage;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또_번호_범위_테스트")
    @Test
    void rangeTest() {
        assertThatThrownBy(() -> new Lotto(List.of(0,1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(ErrorMessage.LOTTO_RANGE_ERROR.getMessage());
    }

    @DisplayName("로또 번호 중복 테스트")
    @Test
    void duplicateTest() {
        assertThatThrownBy(() -> new Lotto(List.of(1,1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(ErrorMessage.LOTTO_NUMBER_DUPLICATED_ERROR.getMessage());
    }

    @DisplayName("로또 번호 갯수 테스트")
    @Test
    void sizeTest() {
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,6,7)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(ErrorMessage.LOTTO_SIZE_ERROR.getMessage());
    }

    @DisplayName("로또 번호 정렬 테스트")
    @Test
    void sortTest(){
        // given
        Lotto lotto = new Lotto(List.of(6,5,4,3,2,1));
        // when & then
        List<Integer> numbers = lotto.getSortedNumbers();
        assertThat(numbers).containsExactly(1,2,3,4,5,6);
    }

}