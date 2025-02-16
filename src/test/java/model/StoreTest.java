package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static testfixture.LottoNumberFixture.convertToLottoNumbers;

import java.util.List;
import mock.MockNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StoreTest {

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("구매한 로또를 반환한다.")
        @Test
        void purchaseLottos() {
            // given
            LottoMachine lottoMachine = new LottoMachine(new MockNumberGenerator());
            Store store = new Store(lottoMachine);
            PurchaseAmount purchaseAmount = new PurchaseAmount(1000);

            List<LottoNumber> lottoNumbers = convertToLottoNumbers(1, 2, 3, 4, 5, 6);
            Lotto expectedLotto = new Lotto(lottoNumbers);

            // when
            List<Lotto> lottos = store.purchaseLottos(purchaseAmount);

            // then
            assertSoftly(softly -> {
                softly.assertThat(lottos.size())
                    .isEqualTo(1);
                softly.assertThat(lottos.getFirst())
                    .usingRecursiveComparison()
                    .isEqualTo(expectedLotto);
            });
        }


        @DisplayName("당첨 결과를 반환한다.")
        @Test
        void calculateWinningResult() {
            // given
            LottoMachine lottoMachine = new LottoMachine(new MockNumberGenerator());
            Store store = new Store(lottoMachine);

            Lotto lotto = new Lotto(convertToLottoNumbers(1, 2, 3, 4, 5, 6));
            List<Lotto> lottos = List.of(lotto);

            WinningNumbers winningNumbers =
                new WinningNumbers(convertToLottoNumbers(1, 2, 3, 4, 5, 6), LottoNumber.of(7));

            // when
            WinningResult winningResult = store.calculateWinningResult(winningNumbers, lottos);

            // then
            assertThat(winningResult.getLottoRanks()
                .get(LottoRank.FIRST_PLACE))
                .isEqualTo(1);
        }
    }
}
