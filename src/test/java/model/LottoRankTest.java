package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoRankTest {

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @Test
        @DisplayName("로또 번호가 6개 일치한다면, 1등을 반환한다.")
        void createLottoRankFirst() {
            // given
            int matchCount = 6;
            boolean bonusBallMatch = false;

            // when
            LottoRank lottoRank = LottoRank.of(matchCount, bonusBallMatch);

            // then
            assertThat(lottoRank).isEqualTo(LottoRank.FIRST_PLACE);
        }

        @Test
        @DisplayName("로또 번호가 5개 일치하고, 보너스 번호가 일치한다면, 2등을 반환한다.")
        void createLottoRankSecond() {
            // given
            int matchCount = 5;
            boolean bonusBallMatch = true;

            // when
            LottoRank lottoRank = LottoRank.of(matchCount, bonusBallMatch);

            // then
            assertThat(lottoRank).isEqualTo(LottoRank.SECOND_PLACE);
        }

        @Test
        @DisplayName("로또 번호가 5개 일치한다면, 3등을 반환한다.")
        void createLottoRankThird() {
            // given
            int matchCount = 5;
            boolean bonusBallMatch = false;

            // when
            LottoRank lottoRank = LottoRank.of(matchCount, bonusBallMatch);

            // then
            assertThat(lottoRank).isEqualTo(LottoRank.THIRD_PLACE);
        }

        @Test
        @DisplayName("로또 번호가 4개 일치한다면, 4등을 반환한다.")
        void createLottoRankFourth() {
            // given
            int matchCount = 4;
            boolean bonusBallMatch = false;

            // when
            LottoRank lottoRank = LottoRank.of(matchCount, bonusBallMatch);

            // then
            assertThat(lottoRank).isEqualTo(LottoRank.FOURTH_PLACE);
        }

        @Test
        @DisplayName("로또 번호가 3개 일치한다면, 5등을 반환한다.")
        void createLottoRankFifth() {
            // given
            int matchCount = 3;
            boolean bonusBallMatch = false;

            // when
            LottoRank lottoRank = LottoRank.of(matchCount, bonusBallMatch);

            // then
            assertThat(lottoRank).isEqualTo(LottoRank.FIFTH_PLACE);
        }

        @Test
        @DisplayName("로또 번호가 3개 미만으로 일치한다면, 탈락을 반환한다.")
        void createLottoRankFail() {
            // given
            int matchCount = 2;
            boolean bonusBallMatch = false;

            // when
            LottoRank lottoRank = LottoRank.of(matchCount, bonusBallMatch);

            // then
            assertThat(lottoRank).isEqualTo(LottoRank.FAIL);
        }

    }
}
