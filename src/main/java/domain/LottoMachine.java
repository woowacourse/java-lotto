package domain;

import java.util.ArrayList;
import java.util.List;

import static domain.LottoInformation.LOTTO_COUNT;
import static domain.LottoNumber.LOTTO_NUMBER_END;
import static domain.LottoNumber.LOTTO_NUMBER_START;

public class LottoMachine {


    public LottoMachine() {
    }

    public Lottos generateLottos(int ticket) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < ticket; i++) {
            List<Integer> randomNumbers = RandomGenerator.generateUniqueRandomNumbers(LOTTO_COUNT, LOTTO_NUMBER_START, LOTTO_NUMBER_END);
            lottoList.add(new Lotto(randomNumbers));
        }
        return new Lottos(lottoList);
    }
}
