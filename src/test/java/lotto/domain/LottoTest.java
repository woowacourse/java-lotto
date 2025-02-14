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
        //given
        Lotto lotto = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(7)));

        //when & then
        assertThat(lotto.getSize()).isEqualTo(6);
    }

    @DisplayName("로또번호가 중복될 시 에러를 발생한다")
    @Test
    void 로또번호가_중복될시_에러를_발생한다() {

        //when & then
        assertThatThrownBy(
                () -> new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(5))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

    @DisplayName("로또의 사이즈가 다를시 에러를 발생한다")
    @Test
    void 로또의_사이즈가_다를시_에러를_발생한다() {

        //when & then
        assertThatThrownBy(
                () -> new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또의 갯수가 일치하지 않습니다.");
    }


    @DisplayName("로또와 당첨 번호와의 겹친 갯수를 반환한다.")
    @Test
    void 로또와_당첨_번호와의_겹친_갯수를_반환한다() {

        //given
        Lotto lotto = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(7)));

        //when
        int totalMatchCount = lotto.checkMatchCount(lotto);

        //then
        assertThat(totalMatchCount).isEqualTo(6);
    }

    @DisplayName("로또에 주어진 번호가 있다면 true를 반환한다.")
    @Test
    void 로또에_주어진_번호가_있다면_true를_반환한다() {

        //given
        Lotto lotto = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(7)));

        //when
        boolean hasNumber = lotto.hasNumber(new LottoNumber(5));

        //then
        assertThat(hasNumber).isEqualTo(true);
    }

    @DisplayName("로또에 주어진 번호가 없다면 false를 반환한다.")
    @Test
    void 로또에_주어진_번호가_없다면_false를_반환한다() {

        //given
        Lotto lotto = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(7)));

        //when
        boolean hasNumber = lotto.hasNumber(new LottoNumber(10));

        //then
        assertThat(hasNumber).isEqualTo(false);
    }

    @DisplayName("로또에 보너스 번호가 있다면 true를 반환한다.")
    @Test
    void 로또애_보너스_번호가_있다면_true를_반환한다() {

        //given
        Lotto lotto = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        LottoNumber bonusNumber = new LottoNumber(5);

        //when & then
        assertThat(lotto.checkBonus(bonusNumber)).isEqualTo(true);
    }

    @DisplayName("로또에 보너스 번호가 없다면 false를 반환한다.")
    @Test
    void 로또애_보너스_번호가_없다면_true를_반환한다() {

        //given
        Lotto lotto = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        LottoNumber bonusNumber = new LottoNumber(7);

        //when & then
        assertThat(lotto.checkBonus(bonusNumber)).isEqualTo(false);
    }
}
