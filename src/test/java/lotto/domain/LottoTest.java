package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("로또_생성을_확인한다")
    @Test
    void 로또_생성을_확인한다() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 45);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getSize()).isEqualTo(6);
    }

    @DisplayName("로또번호가 범위를 초과하면 에러를 발생한다")
    @Test
    void 로또번호가_범위를_초과하면_에러를_발생한다() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 범위를 벗어나는 숫자입니다.");
    }

    @DisplayName("로또번호가 범위 미만이면 에러를 발생한다")
    @Test
    void 로또번호가_범위_미만이면_에러를_발생한다() {

        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 범위를 벗어나는 숫자입니다.");
    }

    @DisplayName("로또번호가 중복될 시 에러를 발생한다")
    @Test
    void 로또번호가_중복될시_에러를_발생한다() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

    @DisplayName("로또의 사이즈가 작을 시 에러를 발생한다")
    @Test
    void 로또의_사이즈가_작을_시_에러를_발생한다() {

        List<Integer> numbers = List.of(1);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또의 갯수가 일치하지 않습니다.");
    }

    @DisplayName("로또의 사이즈가 클 시 에러를 발생한다")
    @Test
    void 로또의_사이즈가_클_시_에러를_발생한다() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또의 갯수가 일치하지 않습니다.");
    }

    @DisplayName("로또와 당첨 번호와의 겹친 갯수를 반환한다.")
    @Test
    void 로또와_당첨_번호와의_겹친_갯수를_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        int totalMatchCount = lotto.checkMatchCount(winningNumbers);

        assertThat(totalMatchCount).isEqualTo(6);
    }

    @DisplayName("로또에 보너스 번호가 있다면 true를 반환한다.")
    @Test
    void 로또애_보너스_번호가_있다면_true를_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 5;

        boolean matchBonus = lotto.checkBonus(bonusNumber);
        assertThat(matchBonus).isEqualTo(true);
    }

    @DisplayName("로또에 보너스 번호가 없다면 false를 반환한다.")
    @Test
    void 로또애_보너스_번호가_없다면_false를_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        boolean matchBonus = lotto.checkBonus(bonusNumber);
        assertThat(matchBonus).isEqualTo(false);
    }
}
