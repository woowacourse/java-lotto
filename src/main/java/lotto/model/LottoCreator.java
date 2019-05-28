package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoCreator {
        public static Lotto creat() {
                List<LottoNumber> lottoNumbers = new ArrayList<>();
                for (int index = 0; index < 6; index++) {
                        lottoNumbers.add(LottoNumberCreator.create(new Random().nextInt(45) + 1));
                }
                return new Lotto(lottoNumbers);
        }
}
