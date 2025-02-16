package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testUtil.StaticNumberPicker;
import util.NumberPicker;

class LottoMachineTest {

    @DisplayName("구입 금액이 1000원 미만이라면 예외가 발생한다.")
    @Test
    void leesThanPurchaseMoney() {
        //given //when //than
        final int purchaseMoney = 999;
        final NumberPicker numberPicker = new StaticNumberPicker(List.of(
            List.of(1, 2, 3, 4, 5, 6),
            List.of(14, 15, 16, 13, 12, 9),
            List.of(43, 41, 40, 23, 35, 22),
            List.of(9, 7, 13, 14, 16, 2)
        ));
        assertThatThrownBy(() -> LottoMachine.of(purchaseMoney, numberPicker))
            .isExactlyInstanceOf(IllegalStateException.class)
            .hasMessage("금액은 1000원 이상이여아 합니다.");
    }

    @DisplayName("구입 금액이 1000원 단위라면 정상 동작한다.")
    @Test
    void nonLeesThanPurchaseMoney() {
        //given //when //than
        final int purchaseMoney = 1000;
        final NumberPicker numberPicker = new StaticNumberPicker(List.of(
            List.of(1, 2, 3, 4, 5, 6),
            List.of(14, 15, 16, 13, 12, 9),
            List.of(43, 41, 40, 23, 35, 22),
            List.of(9, 7, 13, 14, 16, 2)
        ));
        assertThatCode(() -> LottoMachine.of(purchaseMoney, numberPicker))
            .doesNotThrowAnyException();
    }

    @DisplayName("구입 금액만큼 로또를 발행한다.")
    @Test
    void issuesLotto() {
        //given
        final int purchaseMoney = 4000;
        final NumberPicker numberPicker = new StaticNumberPicker(List.of(
            List.of(1, 2, 3, 4, 5, 6),
            List.of(14, 15, 16, 13, 12, 9),
            List.of(43, 41, 40, 23, 35, 22),
            List.of(9, 7, 13, 14, 16, 2)
        ));

        final LottoMachine lottoMachine = LottoMachine.of(purchaseMoney, numberPicker);

        // when
        final Lottos result = lottoMachine.issueLottos();

        // then
        assertThat(result.getLottos()).hasSize(4);
    }

}
