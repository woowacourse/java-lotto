package model;

import static model.LottoRank.FIRST_PLACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.ArrayList;
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

            List<LottoNumber> lottoNumbers = new ArrayList<>(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                    LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)));
            Lotto expectedLotto = new Lotto(lottoNumbers);

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

            List<LottoNumber> lottoNumbers = new ArrayList<>(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                    LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)));
            Lotto lotto = new Lotto(lottoNumbers);
            WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, LottoNumber.of(7));

            // when
            LottoRank lottoRank = lottoMachine.checkWinningRank(lotto, winningNumbers);

            // then
            assertThat(lottoRank).isEqualTo(FIRST_PLACE);
        }
    }
}
