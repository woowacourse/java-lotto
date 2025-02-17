package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    private List<LottoNumber> lottoNumbers;
    private WinningNumbers winningNumbers;
    private BonusBall bonusBall;

    @BeforeEach
    public void initTestFixture() {
        lottoNumbers = new ArrayList<>(
                Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)));
        winningNumbers = new WinningNumbers(lottoNumbers);
        bonusBall = new BonusBall(new LottoNumber(7), winningNumbers);

    }

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @Test
        @DisplayName("로또 번호를 당첨 번호에 포함하는지 올바르게 비교한다.")
        void containsLottoNumber() {
            // given
            LottoNumber lottoNumber = new LottoNumber(1);
            WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusBall);

            // when
            boolean actual = winningLotto.containsLottoNumber(lottoNumber);

            // then
            assertThat(actual).isTrue();
        }

        @Test
        @DisplayName("보너스 볼이 로또와 매치되는지 올바르게 비교한다.")
        void matchBonusNumber() {
            //given
            Lotto lotto = new Lotto(lottoNumbers);
            WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusBall);

            // when
            boolean actual = winningLotto.matchBonusNumber(lotto);

            // then
            assertThat(actual).isFalse();
        }
    }

}
