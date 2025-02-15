package service;

import domain.AnswerLotto;
import domain.Lotto;
import domain.Lottos;
import domain.numbergenerator.FakeNumberGenerator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {
    @DisplayName("1개의 로또 구매 후 2등 당첨 시 30000의 수익률을 얻는다.")
    @Test
    void test() {
        // given
        AnswerLotto answerLotto = new AnswerLotto(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), 7);
        FakeNumberGenerator fakeNumberGenerator = new FakeNumberGenerator(List.of(1, 2, 3, 4, 5, 7));
        Lottos lottos = Lottos.of(fakeNumberGenerator, 1);
        LottoService lottoService = new LottoService();
        lottoService.calculatePrize(answerLotto, lottos);

        // when
        double rateOfReturn = lottoService.calculateRateOfReturn();

        // then
        Assertions.assertThat(rateOfReturn).isEqualTo(30000);
    }

}