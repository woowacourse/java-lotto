package lotto.domain.number;

import lotto.domain.result.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoRoundsTest {

    private static LottoRounds lottoRounds;

    @BeforeEach
    void initialize() {
        List<LottoNumber> lottoRoundArguments1 = new ArrayList(); // lottoRound1 : 1,2,3,4,5,6
        lottoRoundArguments1.add(LottoNumber.of(1));
        lottoRoundArguments1.add(LottoNumber.of(2));
        lottoRoundArguments1.add(LottoNumber.of(3));
        lottoRoundArguments1.add(LottoNumber.of(4));
        lottoRoundArguments1.add(LottoNumber.of(5));
        lottoRoundArguments1.add(LottoNumber.of(6));
        LottoRound lottoRound1 = new LottoRound(lottoRoundArguments1);

        List<LottoNumber> lottoRoundArguments2 = new ArrayList(); // lottoRound1 : 30,2,3,4,5,6
        lottoRoundArguments2.add(LottoNumber.of(30));
        lottoRoundArguments2.add(LottoNumber.of(2));
        lottoRoundArguments2.add(LottoNumber.of(3));
        lottoRoundArguments2.add(LottoNumber.of(4));
        lottoRoundArguments2.add(LottoNumber.of(5));
        lottoRoundArguments2.add(LottoNumber.of(6));
        LottoRound lottoRound2 = new LottoRound(lottoRoundArguments2);

        List<LottoNumber> lottoRoundArguments3 = new ArrayList(); // lottoRound1 : 45,2,3,4,5,6
        lottoRoundArguments3.add(LottoNumber.of(45));
        lottoRoundArguments3.add(LottoNumber.of(2));
        lottoRoundArguments3.add(LottoNumber.of(3));
        lottoRoundArguments3.add(LottoNumber.of(4));
        lottoRoundArguments3.add(LottoNumber.of(5));
        lottoRoundArguments3.add(LottoNumber.of(6));
        LottoRound lottoRound3 = new LottoRound(lottoRoundArguments3);

        List<LottoNumber> lottoRoundArguments4 = new ArrayList(); // lottoRound1 : 45,45,45,45,5,6
        lottoRoundArguments4.add(LottoNumber.of(45));
        lottoRoundArguments4.add(LottoNumber.of(45));
        lottoRoundArguments4.add(LottoNumber.of(45));
        lottoRoundArguments4.add(LottoNumber.of(45));
        lottoRoundArguments4.add(LottoNumber.of(5));
        lottoRoundArguments4.add(LottoNumber.of(6));
        LottoRound lottoRound4 = new LottoRound(lottoRoundArguments4);

        List<LottoRound> lottoRoundArguments = new ArrayList<>();
        lottoRoundArguments.add(lottoRound1);
        lottoRoundArguments.add(lottoRound2);
        lottoRoundArguments.add(lottoRound3);
        lottoRoundArguments.add(lottoRound4);

        lottoRounds = new LottoRounds(lottoRoundArguments);
    }
    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자에_Null_입력() {
        assertThatThrownBy(() -> {
            LottoRounds lottoRounds = new LottoRounds(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자에_empty_list_입력() {
        assertThatThrownBy(() -> {
            LottoRounds lottoRounds = new LottoRounds(Collections.emptyList());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 게임_결과_계산_및_출력() {
        // given
        List<LottoNumber> winningNumberArguments = new ArrayList(); // winningNumbers : 1,2,3,4,5,6
        winningNumberArguments.add(LottoNumber.of(1));              // bonusNumber = 30
        winningNumberArguments.add(LottoNumber.of(2));
        winningNumberArguments.add(LottoNumber.of(3));
        winningNumberArguments.add(LottoNumber.of(4));
        winningNumberArguments.add(LottoNumber.of(5));
        winningNumberArguments.add(LottoNumber.of(6));
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberArguments, LottoNumber.of(30));

        // when
        List<GameResult> gameResults = lottoRounds.calculateGameResult(winningNumbers);
        List<GameResult> expected = new ArrayList<>();
        expected.add(GameResult.FIRST_RANK);
        expected.add(GameResult.SECOND_RANK);
        expected.add(GameResult.THIRD_RANK);
        expected.add(GameResult.NO_RANK);
        // then
        assertThat(gameResults).hasSize(4);
        assertThat(gameResults).isEqualTo(expected);
    }

    @Test
    void LottoRounds_합치기() {
        // given
        List<LottoNumber> anotherLottoRoundArguments1 = new ArrayList(); // lottoRound1 : 1,2,3,4,5,6
        anotherLottoRoundArguments1.add(LottoNumber.of(1));
        anotherLottoRoundArguments1.add(LottoNumber.of(2));
        anotherLottoRoundArguments1.add(LottoNumber.of(3));
        anotherLottoRoundArguments1.add(LottoNumber.of(4));
        anotherLottoRoundArguments1.add(LottoNumber.of(5));
        anotherLottoRoundArguments1.add(LottoNumber.of(6));
        LottoRound anotherLottoRound1 = new LottoRound(anotherLottoRoundArguments1);

        List<LottoNumber> anotherLottoRoundArguments2 = new ArrayList(); // lottoRound1 : 30,2,3,4,5,6
        anotherLottoRoundArguments2.add(LottoNumber.of(30));
        anotherLottoRoundArguments2.add(LottoNumber.of(2));
        anotherLottoRoundArguments2.add(LottoNumber.of(3));
        anotherLottoRoundArguments2.add(LottoNumber.of(4));
        anotherLottoRoundArguments2.add(LottoNumber.of(5));
        anotherLottoRoundArguments2.add(LottoNumber.of(6));
        LottoRound anotherLottoRound2 = new LottoRound(anotherLottoRoundArguments2);

        List<LottoNumber> anotherLottoRoundArguments3 = new ArrayList(); // lottoRound1 : 45,2,3,4,5,6
        anotherLottoRoundArguments3.add(LottoNumber.of(45));
        anotherLottoRoundArguments3.add(LottoNumber.of(2));
        anotherLottoRoundArguments3.add(LottoNumber.of(3));
        anotherLottoRoundArguments3.add(LottoNumber.of(4));
        anotherLottoRoundArguments3.add(LottoNumber.of(5));
        anotherLottoRoundArguments3.add(LottoNumber.of(6));
        LottoRound anotherLottoRound3 = new LottoRound(anotherLottoRoundArguments3);

        List<LottoRound> anotherLottoRoundArguments = new ArrayList<>();
        anotherLottoRoundArguments.add(anotherLottoRound1);
        anotherLottoRoundArguments.add(anotherLottoRound2);
        anotherLottoRoundArguments.add(anotherLottoRound3);

        LottoRounds anotherLottoRounds = new LottoRounds(anotherLottoRoundArguments);

        // when
        LottoRounds combinedLottoRounds = lottoRounds.combineLottoRounds(anotherLottoRounds);

        // then
        assertThat(combinedLottoRounds.getAllLottoNumbers()).contains(anotherLottoRound1);
        assertThat(combinedLottoRounds.getAllLottoNumbers()).contains(anotherLottoRound2);
        assertThat(combinedLottoRounds.getAllLottoNumbers()).contains(anotherLottoRound3);
        assertThat(combinedLottoRounds.getAllLottoNumbers()).hasSize(7);
    }
}