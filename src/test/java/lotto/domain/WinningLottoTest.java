package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.ManualLottoGeneratorTest.createCustomLotto;
import static lotto.domain.WinningLotto.DUPLICATE_BONUS_NUMBER_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningLottoTest {
    private WinningLotto winingLotto;
    private ManualLottoGenerator manualLottoGenerator;

    public static WinningLotto createCustomWinningLotto(String customNumbers, String bonusNumber) {
        return new WinningLotto(createCustomLotto(customNumbers), new LottoNumber(Integer.parseInt(bonusNumber)));
    }

    @BeforeEach
    void setUp() {
        winingLotto = createCustomWinningLotto("1, 2, 3, 4, 5, 6", "20");
        manualLottoGenerator = new ManualLottoGenerator(new int[]{1, 2, 3, 4, 5, 20});
    }

    @DisplayName("보너스가 당첨 번호와 중복 되는 것이 있는지 검증")
    @Test
    void validateDuplicatesWithWinningNumbers() {
        assertThatThrownBy(() -> createCustomWinningLotto("1, 2, 3, 4, 5, 6", "5")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATE_BONUS_NUMBER_ERROR);
    }

    @DisplayName("당첨 번호와 로또와의 매칭 확인 제대로 하는지")
    @Test
    void countMatchingNumbers() {
        assertThat(winingLotto.countMatchingNumbers(manualLottoGenerator.createLottoNumbers())).isEqualTo(5);
    }

    @DisplayName("보너스 번호와 로또와의 매칭 확인 제대로 하는지")
    @Test
    void hasBonusMatch() {
        assertThat(winingLotto.hasBonusMatch(manualLottoGenerator.createLottoNumbers())).isTrue();
    }
}