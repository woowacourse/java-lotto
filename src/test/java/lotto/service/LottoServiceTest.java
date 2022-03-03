package lotto.service;

import lotto.domain.LottoMatchKind;
import lotto.domain.LottoNumbers;
import lotto.domain.WinningNumbers;
import lotto.domain.generator.LottoCustomGenerator;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static lotto.domain.LottoMatchKind.*;
import static org.assertj.core.api.Assertions.*;

class LottoServiceTest {
    private static final int ALL_COUNTS_OF_LOTTO_NUMBERS = 5;

    private final WinningNumbers winningNumbers = new WinningNumbers(
            new LottoNumbers(Arrays.asList("2", "3", "4", "5", "6", "7")), LottoNumber.from("1"));
    private final LottoService lottoService = new LottoService(new LottoCustomGenerator());

    @Test
    @DisplayName("수동 구매 개수를 반환한다.")
    void getCountOfManualLottoNumbers() {
        //given
        final int expected = 2;
        final String manualPurchaseCounts = "2";
        //when
        final int actual = lottoService.countOfManualLottoNumbers(manualPurchaseCounts, ALL_COUNTS_OF_LOTTO_NUMBERS);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("수동으로 만들 경우, 완전히 동일한 줄이 존재할 때 예외를 발생시킨다.")
    void create_exceptionByDuplicateLottoNumbersGroupByManual() {
        //given
        final String expectedExceptionMessage = "완전히 동일한 줄이 존재합니다.";
        //when then
        assertThatThrownBy(() -> lottoService.generateLottoNumbersGroup(
                ALL_COUNTS_OF_LOTTO_NUMBERS, Arrays.asList(
                        Arrays.asList("4", "5", "6", "7", "8", "9"),
                        Arrays.asList("4", "5", "6", "7", "8", "9")
                ))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @Test
    @DisplayName("구매 개수를 반환한다.")
    void getCountOfLottoNumbers() {
        //given
        final int expected = 5;
        final String purchaseAmount = "5000";
        //when
        final int actual = lottoService.countOfLottoNumbers(purchaseAmount);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("구매한 모든 로또 숫자들을 반환한다.")
    void getLottoNumbersGroup() {
        //given
        final LottoNumbers first = new LottoNumbers(Arrays.asList("4", "5", "6", "7", "8", "9"));
        final LottoNumbers second = new LottoNumbers(Arrays.asList("5", "6", "7", "8", "9", "10"));
        final LottoNumbers third = new LottoNumbers(Arrays.asList("1", "2", "3", "4", "5", "6"));
        final LottoNumbers fourth = new LottoNumbers(Arrays.asList("2", "3", "4", "5", "6", "7"));
        final LottoNumbers fifth = new LottoNumbers(Arrays.asList("3", "4", "5", "6", "7", "8"));
        //when
        final List<LottoNumbers> actual = generateLottoNumbers();
        //then
        assertThat(actual).contains(first, second, third, fourth, fifth);
    }

    @Test
    @DisplayName("당첨 결과를 반환한다.")
    void getMatchResult() {
        final List<LottoNumbers> lottoNumbersGroup = generateLottoNumbers();
        final Map<LottoMatchKind, Integer> actual = lottoService.getMatchResult(lottoNumbersGroup, winningNumbers);
        assertThat(actual).containsExactly(
                entry(BLANK, 0), entry(THREE, 1), entry(FOUR, 1), entry(FIVE, 1), entry(FIVE_BONUS, 1), entry(SIX, 1));
    }

    @Test
    @DisplayName("수익률을 반환한다.")
    void getProfitRate() {
        //given
        final List<LottoNumbers> lottoNumbersGroup = generateLottoNumbers();
        final Map<LottoMatchKind, Integer> matchResult = lottoService.getMatchResult(lottoNumbersGroup, winningNumbers);
        final double expected = 2031555000 / (double) 5000;
        //when
        final double actual = lottoService.getProfitRate(matchResult, ALL_COUNTS_OF_LOTTO_NUMBERS);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private List<LottoNumbers> generateLottoNumbers() {
        return lottoService.generateLottoNumbersGroup(ALL_COUNTS_OF_LOTTO_NUMBERS, Arrays.asList(
                Arrays.asList("4", "5", "6", "7", "8", "9"),
                Arrays.asList("5", "6", "7", "8", "9", "10")
        ));
    }
}
