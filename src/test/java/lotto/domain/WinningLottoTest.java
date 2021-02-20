package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.ManualLottoGeneratorTest.createCustomLotto;
import static lotto.view.InputView.convertStringsToInts;

class WinningLottoTest {
    public static WinningLotto createCustomWinningLotto(String customNumbers, String bonusNumber) {
        return new WinningLotto(createCustomLotto(customNumbers), new LottoNumber(Integer.parseInt(bonusNumber)));
    }

    //TODO:
    // 테스트 완성하기
    @Test
    void getWinningLottoNumbers() {
    }

    @Test
    void getBonusNumberAsInt() {
    }
}