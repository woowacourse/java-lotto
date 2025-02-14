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

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getSize()).isEqualTo(6);
    }

    @DisplayName("로또번호가 범위내에 없을시 에러를 발생한다")
    @Test
    void 로또번호가_범위내에_없을시_에러를_발생한다() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);

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

    @DisplayName("로또의 사이즈가 다를시 에러를 발생한다")
    @Test
    void 로또의_사이즈가_다를시_에러를_발생한다() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

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
    void 로또애_보너스_번호가_없다면_true를_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        boolean matchBonus = lotto.checkBonus(bonusNumber);
        assertThat(matchBonus).isEqualTo(false);
    }
}
