package service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.LottoNumbers;
import model.WinLotto;
import model.WinLottoInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinLottoInfoMapperTest {

    @Test
    @DisplayName("1등 당첨 테스트")
    public void firstPrizeTest() {
        // given
        LottoNumbers purchasedLotto = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        // when
        WinLottoInfo result = WinLottoInfoMapper.result(purchasedLotto, winLotto);
        // then
        assertThat(result).isEqualTo(WinLottoInfo.FIRST);
    }

    @Test
    @DisplayName("3등 당첨 테스트 (5개 일치, 보너스 번호 불일치)")
    public void thirdPrizeTest() {
        // given
        LottoNumbers purchasedLotto = new LottoNumbers(List.of(1, 2, 3, 4, 5, 10));
        WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        // when
        WinLottoInfo result = WinLottoInfoMapper.result(purchasedLotto, winLotto);
        // then
        assertThat(result).isEqualTo(WinLottoInfo.THIRD);
    }

    @Test
    @DisplayName("당첨이 없는 경우 테스트")
    public void noWinTest() {
        // given
        LottoNumbers purchasedLotto = new LottoNumbers(List.of(10, 20, 30, 40, 41, 42));
        WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        // when
        WinLottoInfo result = WinLottoInfoMapper.result(purchasedLotto, winLotto);
        // then
        assertThat(result).isEqualTo(WinLottoInfo.NONE);
    }

    @Test
    @DisplayName("보너스 번호 일치 시 2등 당첨 테스트")
    public void secondPrizeTest() {
        // given
        LottoNumbers purchasedLotto = new LottoNumbers(List.of(1, 2, 3, 4, 5, 7)); // 5개 + 보너스 번호(7)
        WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        // when
        WinLottoInfo result = WinLottoInfoMapper.result(purchasedLotto, winLotto);
        // then
        assertThat(result).isEqualTo(WinLottoInfo.SECOND);
    }
}
