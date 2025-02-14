package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    void 보너스볼과_당첨번호가_중복된_경우_예외를_반환한다() {
        //given
        Number bonus = new Number(3);
        Lotto lotto = new Lotto(
                List.of(
                        new Number(1),
                        new Number(2),
                        new Number(3),
                        new Number(4),
                        new Number(5),
                        new Number(6)
                )
        );

        //when //then
        assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    void 당첨_번호와_몇개가_같은지_계산할_수_있다() {
        //given
        Number bonus = new Number(7);
        Lotto lotto = new Lotto(
                List.of(
                        new Number(1),
                        new Number(2),
                        new Number(3),
                        new Number(4),
                        new Number(5),
                        new Number(6)
                )
        );
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);
        Lotto purchasedLotto = new Lotto(
                List.of(
                        new Number(1),
                        new Number(2),
                        new Number(10),
                        new Number(11),
                        new Number(12),
                        new Number(13)
                )
        );

        //when
        int matchCount = winningLotto.calculateMatchCount(purchasedLotto);

        //then
        assertThat(matchCount).isEqualTo(2);
    }


    @Test
    void 보너스번호와_일치하는지_판단한다() {
        //given
        Number bonus = new Number(7);
        Lotto lotto1 = new Lotto(
                List.of(
                        new Number(1),
                        new Number(2),
                        new Number(3),
                        new Number(4),
                        new Number(5),
                        new Number(6)
                )
        );

        Lotto lotto2 = new Lotto(
                List.of(
                        new Number(1),
                        new Number(2),
                        new Number(3),
                        new Number(4),
                        new Number(5),
                        new Number(7)
                )
        );
        WinningLotto winningLotto = new WinningLotto(lotto1, bonus);
        //when
        boolean actual = winningLotto.containsBonusNumber(lotto2);
        //then
        assertThat(actual).isTrue();
    }
}
