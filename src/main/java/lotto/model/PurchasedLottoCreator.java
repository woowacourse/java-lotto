package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PurchasedLottoCreator {
        private static final int NUMBER_OF_LOTTO_NUMBERS = 6;
        private static final int MAX_LOTTO_NUMBER = 45;

        public static Lotto create() {
                List<LottoNumber> lottoNumbers = new ArrayList<>();
                while (lottoNumbers.size() < NUMBER_OF_LOTTO_NUMBERS) {
                        int randomNumber = new Random().nextInt(MAX_LOTTO_NUMBER) + 1;
                        LottoNumber randomLottoNumber = LottoNumberCreator.create(randomNumber);
                        addLottoNumber(lottoNumbers, randomLottoNumber);
                }
                return new Lotto(lottoNumbers);
        }

        private static void addLottoNumber(List<LottoNumber> lottoNumbers, LottoNumber randomLottoNumber) {
                if (!lottoNumbers.contains(randomLottoNumber)) {
                        lottoNumbers.add(randomLottoNumber);
                }
        }
}
