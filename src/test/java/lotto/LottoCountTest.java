package lotto;

import domain.LottoCount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
    @Test
    void 입력한_수동_로또_개수가_숫자가_아닌_경우_예외_처리() {
        int totalLottoCount = 6;
        String manualLottoCount = "a";
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(totalLottoCount, manualLottoCount);
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수동 로또 개수는 숫자로 입력해야합니다.");
    }

    @Test
    void 입력한_수동_로또_개수가_전체_로또_개수보다_많은_경우_예외_처리() {
        int totalLottoCount = 6;
        String manualLottoCount = "7";
        assertThatThrownBy(() -> {
            LottoCount lottoCount = new LottoCount(totalLottoCount, manualLottoCount);
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수동 로또 개수가 전체 로또 개수보다 클 수 없습니다.");
    }
}
