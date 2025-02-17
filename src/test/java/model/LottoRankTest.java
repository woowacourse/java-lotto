package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoRankTest {

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        private WinningNumbers winningNumbers;
        private BonusBall bonusBall;
        private WinningLotto winningLotto;

        public ValidCases() {
            this.winningNumbers = new WinningNumbers(new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                            new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))));
            this.bonusBall = BonusBall.of(7, winningNumbers);
            this.winningLotto = new WinningLotto(winningNumbers, bonusBall);
        }

        @Test
        @DisplayName("로또 번호가 6개 일치한다면, 1등을 반환한다.")
        void createLottoRankFirst() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                            new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
            Lotto lotto = new Lotto(lottoNumbers);

            // when
            LottoRank lottoRank = LottoRank.of(lotto, winningLotto);

            // then
            assertThat(lottoRank).isEqualTo(LottoRank.FIRST_PLACE);
        }

        @Test
        @DisplayName("로또 번호가 5개 일치하고, 보너스 번호가 일치한다면, 2등을 반환한다.")
        void createLottoRankSecond() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                            new LottoNumber(4), new LottoNumber(5), new LottoNumber(7)));
            Lotto lotto = new Lotto(lottoNumbers);

            // when
            LottoRank lottoRank = LottoRank.of(lotto, winningLotto);

            assertThat(lottoRank).isEqualTo(LottoRank.SECOND_PLACE);
        }

        @Test
        @DisplayName("로또 번호가 5개 일치한다면, 3등을 반환한다.")
        void createLottoRankThird() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                            new LottoNumber(4), new LottoNumber(5), new LottoNumber(8)));
            Lotto lotto = new Lotto(lottoNumbers);

            // when
            LottoRank lottoRank = LottoRank.of(lotto, winningLotto);

            assertThat(lottoRank).isEqualTo(LottoRank.THIRD_PLACE);
        }

        @Test
        @DisplayName("로또 번호가 4개 일치한다면, 4등을 반환한다.")
        void createLottoRankFourth() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                            new LottoNumber(4), new LottoNumber(15), new LottoNumber(16)));
            Lotto lotto = new Lotto(lottoNumbers);

            // when
            LottoRank lottoRank = LottoRank.of(lotto, winningLotto);

            assertThat(lottoRank).isEqualTo(LottoRank.FOURTH_PLACE);
        }

        @Test
        @DisplayName("로또 번호가 3개 일치한다면, 5등을 반환한다.")
        void createLottoRankFifth() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                            new LottoNumber(14), new LottoNumber(15), new LottoNumber(16)));
            Lotto lotto = new Lotto(lottoNumbers);

            // when
            LottoRank lottoRank = LottoRank.of(lotto, winningLotto);

            assertThat(lottoRank).isEqualTo(LottoRank.FIFTH_PLACE);
        }

        @Test
        @DisplayName("로또 번호가 3개 미만으로 일치한다면, 탈락을 반환한다.")
        void createLottoRankFail() {
            // given
            List<LottoNumber> lottoNumbers = new ArrayList<>(
                    List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(13),
                            new LottoNumber(14), new LottoNumber(15), new LottoNumber(16)));
            Lotto lotto = new Lotto(lottoNumbers);

            // when
            LottoRank lottoRank = LottoRank.of(lotto, winningLotto);

            assertThat(lottoRank).isEqualTo(LottoRank.FAIL);
        }

    }
}
