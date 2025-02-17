package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinLottoTest {
    @Test
    @DisplayName("로또번호 매칭 테스트")
    public void countMatchNumberTest() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(4, 5, 6, 7, 8, 9));
        WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 4, 5, 6), 44);
        // when
        Integer count = winLotto.countMatchNumber(lottoNumbers);
        //then
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("보너스 매칭 테스트")
    public void matchBonusTest() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(4, 5, 6, 7, 8, 9));
        WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 4, 5, 6), 9);
        // when
        Boolean bonusMatch = winLotto.bonusMatch(lottoNumbers);
        //then
        assertThat(bonusMatch).isTrue();
    }

    @Test
    @DisplayName("보너스 번호 숫자 범위 판별 테스트")
    public void validateBoundTest() {
        // given
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        Integer bonusNumber = 46;

        // when & then
        assertThatThrownBy(() -> new WinLotto(lottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~45 사이의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("보너스 번호 중복 판별 테스트")
    public void numberDuplicateTest() {
        // given
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        Integer bonusNumber = 1;

        // when & then
        assertThatThrownBy(() -> new WinLotto(lottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호와 중복이 아닌 숫자를 입력해주세요");
    }
}
