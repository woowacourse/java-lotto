package lotto.domain;

import lotto.domain.lottonumber.Lotto;
import lotto.domain.matchkind.WinningKind;
import lotto.dto.InputLottoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static lotto.domain.matchkind.WinningKind.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LottoMachineTest {
    private final List<Integer> firstLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    private final List<Integer> secondLottoNumbers = Arrays.asList(2, 3, 4, 5, 6, 7);
    private final List<Integer> thirdLottoNumbers = Arrays.asList(3, 4, 5, 6, 7, 8);
    private final List<Integer> fourthLottoNumbers = Arrays.asList(4, 5, 6, 7, 8, 9);
    private final List<Integer> fifthLottoNumbers = Arrays.asList(5, 6, 7, 8, 9, 10);
    private final List<Integer> sixthLottoNumbers = Arrays.asList(6, 7, 8, 9, 10, 11);

    private final List<InputLottoDto> lottos = Arrays.asList(
            new InputLottoDto(firstLottoNumbers), new InputLottoDto(secondLottoNumbers),
            new InputLottoDto(thirdLottoNumbers), new InputLottoDto(fourthLottoNumbers),
            new InputLottoDto(fifthLottoNumbers), new InputLottoDto(sixthLottoNumbers));
    private final LottoMachine lottoMachine = new LottoMachine(6000, lottos);

    private final List<Integer> winningLotto = Arrays.asList(2, 3, 4, 5, 6, 7);
    private final int bonusNumber = 1;

    @Test
    @DisplayName("자동 구매 개수를 반환한다.")
    void getCountOfLottoNumbers_Test() {
        final int expected = 0;
        //when
        final int actual = lottoMachine.getCountOfAutoLottoNumbers();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("수동 구매 개수를 반환한다.")
    void getCountOfManualLottoNumbers_Test() {
        //given
        final int expected = 6;
        //when
        final int actual = lottoMachine.getCountOfManualLottoNumbers();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("모든 로또들을 반환한다.")
    void getLottoNumbersGroup_Test() {
        //given
        final List<Lotto> expected = Arrays.asList(
                new Lotto(firstLottoNumbers), new Lotto(secondLottoNumbers),
                new Lotto(thirdLottoNumbers), new Lotto(fourthLottoNumbers),
                new Lotto(fifthLottoNumbers), new Lotto(sixthLottoNumbers));
        //when
        final List<Lotto> actual = lottoMachine.getLottos();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 결과를 반환한다.")
    void getMatchResult_Test() {
        final Map<WinningKind, Integer> actual = lottoMachine.getMatchResult(winningLotto, bonusNumber)
                .getWinningNumberByWinningKind();
        assertThat(actual).containsExactly(
                entry(LOWER_THAN_THREE, 1), entry(THREE, 1),
                entry(FOUR, 1), entry(FIVE, 1),
                entry(FIVE_BONUS, 1), entry(SIX, 1));
    }

    @Test
    @DisplayName("수익률을 반환한다.")
    void getProfitRate_Test() {
        //given
        final double expected = 2031555000 / (double) 6000;
        //when
        final double actual = lottoMachine.getMatchResult(winningLotto, bonusNumber)
                .getProfitRate();
        //then
        assertThat(actual).isEqualTo(expected);
    }
}
