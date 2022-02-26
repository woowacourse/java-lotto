package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class LottoWinningNumbersTest {

    private Lotto winningLotto;

    @BeforeEach
    void setBefore() {
        List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        winningLotto = new Lotto(lottoNumbers);
    }

    @ParameterizedTest
    @CsvSource(value = "1,2,3,4,5,6:6", delimiter = ':')
    public void 보너스볼_중복_테스트(String value, int number) {
        assertThatThrownBy(() -> new LottoWinningNumbers(winningLotto, new LottoNumber(6)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
