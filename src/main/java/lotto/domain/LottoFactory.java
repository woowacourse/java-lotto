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
    private static Map<LottoType, LottoCreator> creators = new HashMap<>();

    static {
        creators.put(LottoType.PAID_LOTTO, new PaidLottoCreator());
        creators.put(LottoType.WINNING_LOTTO, new WinningLottoCreator());
    }

    public static Lotto createLottoAuto(final LottoType lottoType) {
        Objects.requireNonNull(lottoType);
        LottoCreator lottoCreator = creators.get(lottoType);

        List<Integer> randomLottoNumbers = RandomUtils.getRandomIntList(
            Lotto.LOTTO_LENGTH,
            Lotto.MINIMUM_LOTTO_NUMBER,
            Lotto.MAXIMUM_LOTTO_NUMBER
        );
        return lottoCreator.create(randomLottoNumbers);
    }

    public static Lotto createLottoManual(final LottoType lottoType,
        final List<Integer> inputLottoNumbers) {
        Objects.requireNonNull(lottoType);
        Objects.requireNonNull(inputLottoNumbers);

        LottoCreator lottoCreator = creators.get(lottoType);
        return lottoCreator.create(inputLottoNumbers);
    }
}

class PaidLottoCreator implements LottoCreator {
    @Override
    public Lotto create(final List<Integer> lottoNumbers) {
        List<LottoNumber> lottoNumberList = new ArrayList<>();

        for (int lottoNumber : lottoNumbers) {
            lottoNumberList.add(LottoNumber.of(lottoNumber));
        }

        return new PaidLotto(lottoNumberList);
    }
}

class WinningLottoCreator implements LottoCreator {
    @Override
    public Lotto create(final List<Integer> lottoNumbers) {
        List<LottoNumber> lottoNumberList = new ArrayList<>();

        for (int lottoNumber : lottoNumbers) {
            lottoNumberList.add(LottoNumber.of(lottoNumber));
        }

        return new WinningLotto(lottoNumberList);
    }
}
