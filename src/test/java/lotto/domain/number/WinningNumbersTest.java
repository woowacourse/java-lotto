package lotto.domain.number;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningNumbersTest {
    private List<LottoNumber> inputNumbers = new ArrayList<>();
    private List<LottoNumber> winningLottoNumbers = new ArrayList<>();

    @BeforeEach
    void initiate() {
        IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::of)
                .forEach(inputNumbers::add);

        IntStream.rangeClosed(4, 9)
                .mapToObj(LottoNumber::of)
                .forEach(winningLottoNumbers::add);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_입력_생성자_테스트() {
        assertThat(new WinningNumbers(inputNumbers, LottoNumber.of(7)))
                .isInstanceOf(WinningNumbers.class);
    }

    @ParameterizedTest
    @SuppressWarnings("NonAsciiCharacters")
    @ValueSource(ints = {0, 46})
    void 로또_숫자_범위_외의_값이_들어온_경우(int bonusBall) {
        Assertions.assertThatThrownBy(() -> new WinningNumbers(inputNumbers, LottoNumber.of(bonusBall)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 중복된_숫자가_들어온_경우() {
        Assertions.assertThatThrownBy(() -> new WinningNumbers(inputNumbers, LottoNumber.of(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 당첨_번호와_맞는_수_카운팅() {
        // given
        LottoNumber bonusNumber = LottoNumber.of(45);
        WinningNumbers winningNumbers = new WinningNumbers(winningLottoNumbers, bonusNumber);
        LottoRound lottoRound = new LottoRound(inputNumbers);
        // when
        int correctSize = winningNumbers.calculateCollectNumberSize(lottoRound);
        // then
        assertThat(correctSize).isEqualTo(3);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 로또번호에_보너스번호가_맞는지_확인() {
        // given
        LottoNumber bonusNumber = LottoNumber.of(1);
        WinningNumbers winningNumbers = new WinningNumbers(winningLottoNumbers, bonusNumber);
        LottoRound lottoRound = new LottoRound(inputNumbers);
        // when
        boolean result = winningNumbers.isCorrectBonusNumber(lottoRound);
        // then
        assertThat(result).isTrue();
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 로또번호에_보너스번호가_없는_경우_확인() {
        // given
        LottoNumber bonusNumber = LottoNumber.of(45);
        WinningNumbers winningNumbers = new WinningNumbers(winningLottoNumbers, bonusNumber);
        LottoRound lottoRound = new LottoRound(inputNumbers);
        // when
        boolean result = winningNumbers.isCorrectBonusNumber(lottoRound);
        // then
        assertThat(result).isFalse();
    }
}
