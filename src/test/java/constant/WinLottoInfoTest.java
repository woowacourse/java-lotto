package constant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.LottoNumbers;
import model.WinLotto;
import model.WinLottoInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.WinLottoInfoMapper;

class WinLottoInfoTest {
    List<LottoNumbers> lottoNumbersList = new ArrayList<>();
    WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 4, 5, 6), 7);
    List<WinLottoInfo> resultList;
    List<WinLottoInfo> winLottoInfo = Arrays.stream(WinLottoInfo.values()).toList();

    @BeforeEach
    void setUp() {
        lottoNumbersList.add(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6))); // FIRST
        lottoNumbersList.add(new LottoNumbers(List.of(1, 2, 3, 4, 5, 7))); // SECOND
        lottoNumbersList.add(new LottoNumbers(List.of(1, 2, 3, 4, 5, 8))); // THIRD
        lottoNumbersList.add(new LottoNumbers(List.of(1, 2, 3, 4, 7, 8))); // FOURTH
        lottoNumbersList.add(new LottoNumbers(List.of(1, 2, 3, 10, 8, 9))); // FIFTH
        lottoNumbersList.add(new LottoNumbers(List.of(14, 15, 10, 11, 12, 13))); // NONE
        resultList = lottoNumbersList.stream()
                .map((purchasedLotto) -> WinLottoInfoMapper.result(purchasedLotto, winLotto))
                .toList();
    }

    @Test
    @DisplayName("1등 당첨 결과 메서드 테스트")
    public void test1() {
        assertThat(resultList.get(0)).isEqualTo(winLottoInfo.get(0));
    }

    @Test
    @DisplayName("2등 당첨 결과 메서드 테스트")
    void test2() {
        assertThat(resultList.get(1)).isEqualTo(winLottoInfo.get(1));
    }

    @Test
    @DisplayName("3등 당첨 결과 메서드 테스트")
    void test3() {
        assertThat(resultList.get(2)).isEqualTo(winLottoInfo.get(2));
    }

    @Test
    @DisplayName("4등 당첨 결과 메서드 테스트")
    void test4() {
        assertThat(resultList.get(3)).isEqualTo(winLottoInfo.get(3));
    }

    @Test
    @DisplayName("5등 당첨 결과 메서드 테스트")
    void test5() {
        assertThat(resultList.get(4)).isEqualTo(winLottoInfo.get(4));
    }

    @Test
    @DisplayName("꽝 결과 메서드 테스트")
    void test6() {
        assertThat(resultList.get(5)).isEqualTo(winLottoInfo.get(5));
    }
}
