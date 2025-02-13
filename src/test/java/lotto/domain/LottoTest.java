package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {


    @DisplayName("로또_생성을_확인한다")
    @Test
    void 로또_생성을_확인한다() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        Assertions.assertThat(lotto.getSize()).isEqualTo(6);
    }

    @DisplayName("로또번호가 범위내에 없을시 에러를 발생한다")
    @Test
    void 로또번호가_범위내에_없을시_에러를_발생한다() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);

        Assertions.assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 범위를 벗어나는 숫자입니다.");
    }

    @DisplayName("로또번호가 중복될 시 에러를 발생한다")
    @Test
    void 로또번호가_중복될시_에러를_발생한다() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        Assertions.assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

    @DisplayName("로또의 사이즈가 다를시 에러를 발생한다")
    @Test
    void 로또의_사이즈가_다를시_에러를_발생한다() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Assertions.assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또의 사이즈가 일치하지 않습니다.");
    }
}
