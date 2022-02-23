package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class WinningNumbersTest {
    @Test
    void 당첨번호_보너스번호_중복검사_실패테스트() {
        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6));
        assertThatThrownBy(() -> new WinningNumbers(new Ticket(lottoNumbers), new LottoNumber(6)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_보너스번호_중복검사_성공테스트() {
        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6));
        assertThatCode(() -> new WinningNumbers(new Ticket(lottoNumbers), new LottoNumber(7)))
            .doesNotThrowAnyException();
    }
}
