package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("로또와 당첨 번호를 비교하여 일치 개수를 반환한다.")
    @Test
    void getMatchCountTest() {
        List<Integer> lottoNumberList = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> winnerNumberList = List.of(1, 2, 3, 4, 5, 7);
        int bonusNumberInt = 10;
        long expected = 5;

        Lotto lotto = new Lotto(lottoNumberList.stream().map(LottoNumber::new).toList());

        Lotto winnerNumbers = new Lotto(winnerNumberList.stream().map(LottoNumber::new).toList());
        LottoNumber bonusNumber = LottoNumber.from(bonusNumberInt);

        WinnerLotto winnerLotto = new WinnerLotto(winnerNumbers, bonusNumber);

        assertThat(winnerLotto.getMatchCount(lotto)).isEqualTo(expected);
    }

    @DisplayName("보너스 넘버가 존재하면 true 반환한다.")
    @Test
    void hasBonusNumber() {
        List<Integer> lottoNumberList = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> winnerNumberList = List.of(1, 2, 3, 4, 5, 7);
        int bonusNumberInt = 6;

        Lotto lotto = new Lotto(lottoNumberList.stream().map(LottoNumber::new).toList());

        Lotto winnerNumbers = new Lotto(winnerNumberList.stream().map(LottoNumber::new).toList());
        LottoNumber bonusNumber = LottoNumber.from(bonusNumberInt);

        WinnerLotto winnerLotto = new WinnerLotto(winnerNumbers, bonusNumber);

        assertThat(winnerLotto.hasBonusNumber(lotto)).isTrue();
    }

    @DisplayName("보너스 넘버가 존재하지 않으면 false 반환한다.")
    @Test
    void notHaveBonusNumber() {
        List<Integer> lottoNumberList = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> winnerNumberList = List.of(1, 2, 3, 4, 5, 7);
        int bonusNumberInt = 8;

        Lotto lotto = new Lotto(lottoNumberList.stream().map(LottoNumber::new).toList());

        Lotto winnerNumbers = new Lotto(winnerNumberList.stream().map(LottoNumber::new).toList());
        LottoNumber bonusNumber = LottoNumber.from(bonusNumberInt);

        WinnerLotto winnerLotto = new WinnerLotto(winnerNumbers, bonusNumber);

        assertThat(winnerLotto.hasBonusNumber(lotto)).isFalse();
    }

}
