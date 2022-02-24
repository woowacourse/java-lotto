package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("Lotto 객체가 정상적으로 생성되는 경우")
    void createLotto() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());

        Lotto lotto = new Lotto(lottoNumbers);

        assertThat(lotto).isNotNull();
    }

    @Test
    @DisplayName("Lotto 객체 생성 시 LottoNumber 갯수 유효하지 않은 경우")
    void createLottoNotInSize() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 6)
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());

        assertThatThrownBy(() ->
            new Lotto(lottoNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Lotto 객체 생성 시 LottoNumber 가 중복되는 경우")
    void createLottoFromDuplicatedNumber() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 4, 6)
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());

        assertThatThrownBy(() ->
            new Lotto(lottoNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Lotto와 WinningLotto의 일치 갯수를 확인하는 경우")
    void calculateSameLottoNumber() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 14, 4, 6)
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
        Lotto lotto = new Lotto(lottoNumbers);

        List<LottoNumber> winningNumbers = Stream.of(1, 2, 13, 15, 16, 17)
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
        Lotto winningLotto = new Lotto(winningNumbers);

        int matchCount = lotto.calculateSameNumber(winningLotto);
        assertThat(matchCount).isEqualTo(2);
    }
}
