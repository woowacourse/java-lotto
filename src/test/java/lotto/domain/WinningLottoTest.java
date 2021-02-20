package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.view.InputView.convertStringsToInts;

class WinningLottoTest {
    public static WinningLotto createCustomWinningLotto(String customNumbers, String bonusNumber) {
        int[] numbers = convertStringsToInts(customNumbers.split(", "));
        return new WinningLotto(new Lotto(
                Arrays.asList(
                        new LottoNumber(numbers[0]),
                        new LottoNumber(numbers[1]),
                        new LottoNumber(numbers[2]),
                        new LottoNumber(numbers[3]),
                        new LottoNumber(numbers[4]),
                        new LottoNumber(numbers[5]))),
                new LottoNumber(Integer.parseInt(bonusNumber)));
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