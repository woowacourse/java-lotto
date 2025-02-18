package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.List;
import java.util.stream.Stream;
import model.numbers.LottoNumber;
import model.numbers.LottoNumbers;
import model.numbers.LottoNumbersGenerator;
import model.numbers.WinningLotto;
import model.rank.LottoRank;
import model.rank.LottoRankCalculator;
import model.rank.LottoRankResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoStoreTest {

    private LottoStore lottoStore = new LottoStore(new LottoNumbersGenerator(), new LottoRankCalculator());

    @Test
    void 당첨결과_개수를_센다() {
        // given
        List<LottoNumbers> lottoNumbers = List.of(
                new LottoNumbers(fromIntegerListToLottoNumberList(List.of(1, 2, 3, 4, 5, 6)))
        );

        WinningLotto winningLotto = new WinningLotto(
                new LottoNumbers(fromIntegerListToLottoNumberList(List.of(1, 2, 3, 4, 5, 7))
                ), new LottoNumber(6)
        );

        // when
        LottoRankResult lottoRankResult = lottoStore.calculateRankMatchCount(lottoNumbers, winningLotto);

        // then
        assertThat(lottoRankResult.getCountByRank(LottoRank.SECOND)).isEqualTo(1);
    }

    @Test
    void 지불한_금액만큼_로또를_구매한다() {
        // given
        PaidAmount paidAmount = new PaidAmount(1000);

        // when
        List<LottoNumbers> purchasedLotto = lottoStore.purchase(paidAmount);

        // then
        assertThat(purchasedLotto).hasSize(1);
    }

    @ParameterizedTest
    @MethodSource("profitRateTestCases")
    void 올바르게_수익률을_계산한다(int paidAmountValue, LottoRank rank, double expectedValue) {
        // given
        PaidAmount paidAmount = new PaidAmount(paidAmountValue);
        LottoRankResult rankResult = new LottoRankResult();
        rankResult.updateRankCount(rank);

        // when
        double profitRate = lottoStore.calculateProfitRate(paidAmount, rankResult);

        // then
        assertThat(profitRate).isCloseTo(expectedValue, within(0.1));
    }

    @Test
    void 당첨_결과를_세는_경우_DEFAULT는_제외하고_계산한다() {
        List<LottoNumbers> lottoNumbers = List.of(
                new LottoNumbers(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)))
        );

        WinningLotto winningLotto = new WinningLotto(
                new LottoNumbers(List.of(
                        new LottoNumber(10), new LottoNumber(11), new LottoNumber(12),
                        new LottoNumber(13), new LottoNumber(14), new LottoNumber(15))
                ), new LottoNumber(16)
        );

        // when
        LottoRankResult rankResult = lottoStore.calculateRankMatchCount(lottoNumbers, winningLotto);

        // then
        assertThat(rankResult.getRanks()).hasSize(0);
    }

    private static Stream<Arguments> profitRateTestCases() {
        return Stream.of(
                Arguments.of(1000, LottoRank.FIRST, 2000000),
                Arguments.of(1000, LottoRank.SECOND, 30000),
                Arguments.of(1000, LottoRank.THIRD, 1500),
                Arguments.of(1000, LottoRank.FOURTH, 50)
        );
    }

    private List<LottoNumber> fromIntegerListToLottoNumberList(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

}
