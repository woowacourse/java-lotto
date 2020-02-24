package lotto.domain;

import java.util.*;
import lotto.util.RandomUtils;

/**
 * LottoFactory 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class LottoFactory {
    private static LottoCreator lottoCreator = new LottoCreator();

    public static Lotto createLottoAuto() {
        List<Integer> randomLottoNumbers = RandomUtils.getRandomIntList(
            Lotto.LOTTO_LENGTH,
            LottoNumber.MINIMUM_LOTTO_NUMBER,
            LottoNumber.MAXIMUM_LOTTO_NUMBER
        );
        return lottoCreator.create(randomLottoNumbers);
    }

    public static Lotto createLottoManual(final List<Integer> inputLottoNumbers) {
        Objects.requireNonNull(inputLottoNumbers);
        return lottoCreator.create(inputLottoNumbers);
    }
}

class LottoCreator {
    public Lotto create(final List<Integer> lottoNumbers) {
        List<LottoNumber> lottoNumberList = new ArrayList<>();

        for (int lottoNumber : lottoNumbers) {
            lottoNumberList.add(LottoNumber.of(lottoNumber));
        }

        return new Lotto(lottoNumberList);
    }
}
