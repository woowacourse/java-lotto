package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoOrder;
import org.junit.jupiter.api.Test;


@SuppressWarnings("NonAsciiCharacters")
class LottoOrderTest {

    @Test
    void 수동_로또_주문수에_비해서_금액이_부족한_경우_테스트() {
        List<List<Integer>> manualNumbers = new ArrayList<>();
        manualNumbers.add(List.of(1, 2, 3, 4, 5, 6));
        manualNumbers.add(List.of(7, 8, 9, 10, 11, 12));

        assertThatThrownBy(() -> new LottoOrder(1000, manualNumbers))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("금액이 부족하여 주문한 수만큼 로또를 구매할 수 없습니다.");
    }

    @Test
    void 수동_로또를_주문하고_남은금액으로_자동_로또_개수를_게산하는_기능_테스트() {
        List<List<Integer>> manualNumbers = new ArrayList<>();
        manualNumbers.add(List.of(1, 2, 3, 4, 5, 6));
        LottoOrder lottoOrder = new LottoOrder(2000, manualNumbers);
        lottoOrder.billingManualLottoOrder();

        assertThat(lottoOrder.getNumberOfAutoLotto()).isEqualTo(1);
    }
}
