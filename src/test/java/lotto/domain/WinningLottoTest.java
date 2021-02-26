package lotto.domain;

import lotto.exception.LottoNumberException;
import lotto.util.LottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    private List<Integer> lotto;
    private LottoNumber bonusNumber;

    @BeforeEach
    void setUp() {
        lotto = Arrays.asList(1, 2, 3, 4, 5, 6);
        bonusNumber = LottoNumber.of(7);
    }

    @Test
    @DisplayName("로또 번호와 보너스 번호가 중복 시 발생하는 예외 테스트")
    void checkDuplicateWinningNumberWithBonusNumber() {
        assertThatThrownBy(() -> new WinningLotto(lotto, LottoNumber.of(6))).isInstanceOf(
                LottoNumberException.class);
    }

    @ParameterizedTest
    @DisplayName("로또 번호를 입력 시 나오는 랭크 확인 테스트")
    @CsvSource(value = {"1,2,3,4,5,6:FIRST", "1,2,3,4,5,7:SECOND", "1,2,3,4,5,33:THIRD"}, delimiter = ':')
    void checkRankWithInputLotto(String numbers, LottoRank expectedRank) {
        WinningLotto winningLotto = new WinningLotto(lotto, this.bonusNumber);
        List<Integer> lottoNumbers = Arrays
                .stream(numbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LottoRank rank = winningLotto.matchRank(LottoGenerator.generate(lottoNumbers));
        assertThat(rank).isEqualTo(expectedRank);
    }
}