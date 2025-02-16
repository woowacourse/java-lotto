package model;

import static model.LottoRank.FIRST_PLACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static testfixture.LottoNumberFixture.convertToLottoNumbers;

import java.util.List;
import mock.MockNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("로또 머신이 로또를 발급한다.")
        @Test
        void issueLottos() {
            // given
            LottoMachine lottoMachine = new LottoMachine(new MockNumberGenerator());

            Lotto expectedLotto = new Lotto(convertToLottoNumbers(1, 2, 3, 4, 5, 6));

            // when
            List<Lotto> lottos = lottoMachine.issueLottos(1);

            // then
            assertSoftly(softly -> {
                softly.assertThat(lottos.size())
                    .isEqualTo(1);
                softly.assertThat(lottos.getFirst())
                    .usingRecursiveComparison()
                    .isEqualTo(expectedLotto);
            });
        }

        @DisplayName("로또 머신이 로또의 순위를 알려준다.")
        @Test
        void checkWinningRank() {
            // given
            LottoMachine lottoMachine = new LottoMachine(new MockNumberGenerator());

            Lotto lotto = new Lotto(convertToLottoNumbers(1, 2, 3, 4, 5, 6));
            WinningNumbers winningNumbers =
                new WinningNumbers(convertToLottoNumbers(1, 2, 3, 4, 5, 6), LottoNumber.of(7));

            // when
            LottoRank lottoRank = lottoMachine.checkWinningRank(lotto, winningNumbers);

            // then
            assertThat(lottoRank).isEqualTo(FIRST_PLACE);
        }
    }
}
