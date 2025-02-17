package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    void 보너스볼과_당첨번호가_중복된_경우_예외를_반환한다() {
        //given
        LottoNumber bonus = new LottoNumber(3);
        Lotto lotto = new Lotto(
                List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        );

        //when //then
        assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
