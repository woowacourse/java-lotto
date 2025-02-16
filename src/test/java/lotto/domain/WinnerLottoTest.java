package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.common.ErrorMessage;
import org.junit.jupiter.api.Test;

class WinnerLottoTest {

    @Test
    void 올바른_생성_테스트() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);
        LottoNumber lottoNumber = new LottoNumber(7);

        assertThatCode(() -> new WinnerLotto(lotto, lottoNumber)).doesNotThrowAnyException();
    }

    @Test
    void 당첨번호와_보너스넘버가_중복되면_에러_반환() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).toList();
        Lotto lotto = new Lotto(lottoNumbers);

        for (LottoNumber bonusNumber : lottoNumbers) {
            assertThatThrownBy(() -> new WinnerLotto(lotto, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
        }

    }
}
