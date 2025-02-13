package model;

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
            PurchaseAmount purchaseAmount = new PurchaseAmount(1000);

            List<LottoNumber> lottoNumbers = new ArrayList<>(
                List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                    new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
            Lotto expectedLotto = new Lotto(lottoNumbers);
            // when
            List<Lotto> lottos = lottoMachine.issueLottos(purchaseAmount);

            // then
            assertSoftly(softly -> {
                softly.assertThat(lottos.size())
                    .isEqualTo(1);
                softly.assertThat(lottos.getFirst())
                    .usingRecursiveComparison()
                    .isEqualTo(expectedLotto);
            });
        }

    }
}
