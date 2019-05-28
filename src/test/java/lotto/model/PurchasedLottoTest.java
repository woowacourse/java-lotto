package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchasedLottoTest {
        @Test
        void 로또숫자_중복_검사() {
                assertThrows(LottoNumberDuplicationException.class, () -> {
                        List<LottoNumber> lottoNumbers = new ArrayList<>();
                        lottoNumbers.add(LottoNumberCreator.create(5));
                        lottoNumbers.add(LottoNumberCreator.create(3));
                        lottoNumbers.add(LottoNumberCreator.create(11));
                        lottoNumbers.add(LottoNumberCreator.create(35));
                        lottoNumbers.add(LottoNumberCreator.create(3));

                        new PurchasedLotto(lottoNumbers);
                });
        }
}
