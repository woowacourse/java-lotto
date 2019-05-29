package lotto.model.creator;

import lotto.model.object.Lotto;
import lotto.model.object.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class WinningLottoCreator {
        public static Lotto create(String[] inputs) {
                List<LottoNumber> lottoNumbers = new ArrayList<>();
                for (String input : inputs) {
                        int number = Integer.parseInt(input);
                        lottoNumbers.add(LottoNumberCreator.create(number));
                }
                return new Lotto(lottoNumbers);
        }
}
