package lotto.domain;

import lotto.Exception.InvalidLottoNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumbersTest {
    List<LottoNumber> lottoNumbers;
    @BeforeEach
    void setUp() {
        lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(2));
        lottoNumbers.add(new LottoNumber(3));
        lottoNumbers.add(new LottoNumber(4));
        lottoNumbers.add(new LottoNumber(5));
    }

    @Test
    void 중복숫자테스트() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            new LottoNumbers(lottoNumbers);
        });
    }
}
