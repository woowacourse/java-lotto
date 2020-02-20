package lotto.domain.number;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.WinningNumbers;
import lotto.domain.result.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningNumbersTest {
    List<LottoNumber> inputNumbers;

    @BeforeEach
    void initiate() {
        inputNumbers = new ArrayList<>();
        inputNumbers.add(LottoNumber.of(1));
        inputNumbers.add(LottoNumber.of(2));
        inputNumbers.add(LottoNumber.of(3));
        inputNumbers.add(LottoNumber.of(4));
        inputNumbers.add(LottoNumber.of(5));
        inputNumbers.add(LottoNumber.of(6));
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
    void 당첨_번호와_맞는_수_카운팅() {
        // given
        List<LottoNumber> winningLottoNumbers = new ArrayList<>();
        winningLottoNumbers.add(LottoNumber.of(3));
        winningLottoNumbers.add(LottoNumber.of(4));
        winningLottoNumbers.add(LottoNumber.of(5));
        winningLottoNumbers.add(LottoNumber.of(6));
        winningLottoNumbers.add(LottoNumber.of(7));
        winningLottoNumbers.add(LottoNumber.of(8));

        LottoNumber bonusNumber = LottoNumber.of(45);
        WinningNumbers winningNumbers = new WinningNumbers(winningLottoNumbers, bonusNumber);

        List<LottoNumber> lottoNumbersList = new ArrayList<>();
        lottoNumbersList.add(LottoNumber.of(3));
        lottoNumbersList.add(LottoNumber.of(4));
        lottoNumbersList.add(LottoNumber.of(5));
        lottoNumbersList.add(LottoNumber.of(10));
        lottoNumbersList.add(LottoNumber.of(11));
        lottoNumbersList.add(LottoNumber.of(12));
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumbersList);

        // when
        int correctSize = winningNumbers.calculateCollectNumberSize(lottoNumbers);

        // then
        assertThat(correctSize).isEqualTo(3);
    }

    @Test
    void 로또번호에_보너스번호가_맞는지_확인() {
        // given
        List<LottoNumber> winningLottoNumbers = new ArrayList<>();
        winningLottoNumbers.add(LottoNumber.of(3));
        winningLottoNumbers.add(LottoNumber.of(4));
        winningLottoNumbers.add(LottoNumber.of(5));
        winningLottoNumbers.add(LottoNumber.of(6));
        winningLottoNumbers.add(LottoNumber.of(7));
        winningLottoNumbers.add(LottoNumber.of(8));

        LottoNumber bonusNumber = LottoNumber.of(45);
        WinningNumbers winningNumbers = new WinningNumbers(winningLottoNumbers, bonusNumber);

        List<LottoNumber> lottoNumbersList = new ArrayList<>();
        lottoNumbersList.add(LottoNumber.of(3));
        lottoNumbersList.add(LottoNumber.of(4));
        lottoNumbersList.add(LottoNumber.of(5));
        lottoNumbersList.add(LottoNumber.of(10));
        lottoNumbersList.add(LottoNumber.of(11));
        lottoNumbersList.add(LottoNumber.of(45));
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumbersList);

        // when
        boolean result = winningNumbers.isCorrectBonusNumber(lottoNumbers);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 로또번호에_보너스번호가_없는_경우_확인() {
        // given
        List<LottoNumber> winningLottoNumbers = new ArrayList<>();
        winningLottoNumbers.add(LottoNumber.of(3));
        winningLottoNumbers.add(LottoNumber.of(4));
        winningLottoNumbers.add(LottoNumber.of(5));
        winningLottoNumbers.add(LottoNumber.of(6));
        winningLottoNumbers.add(LottoNumber.of(7));
        winningLottoNumbers.add(LottoNumber.of(8));

        LottoNumber bonusNumber = LottoNumber.of(45);
        WinningNumbers winningNumbers = new WinningNumbers(winningLottoNumbers, bonusNumber);

        List<LottoNumber> lottoNumbersList = new ArrayList<>();
        lottoNumbersList.add(LottoNumber.of(3));
        lottoNumbersList.add(LottoNumber.of(4));
        lottoNumbersList.add(LottoNumber.of(5));
        lottoNumbersList.add(LottoNumber.of(10));
        lottoNumbersList.add(LottoNumber.of(11));
        lottoNumbersList.add(LottoNumber.of(13));
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumbersList);

        // when
        boolean result = winningNumbers.isCorrectBonusNumber(lottoNumbers);

        // then
        assertThat(result).isFalse();
    }
}
