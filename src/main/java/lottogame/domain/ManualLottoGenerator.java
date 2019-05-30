package lottogame.domain;

import lottogame.utils.InvalidLottoNumberException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManualLottoGenerator {
    private static final int LOTTO_SIZE = 6;

    public static Lotto create(List<Integer> lotto) {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        for (Integer index : lotto) {
            lottoNumbers.add(LottoNumber.Of(index));
        }
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new InvalidLottoNumberException("6개의 중복되지 않는 숫자를 입력해주세요.");
        }

        return new Lotto(new ArrayList<>(lottoNumbers));
    }
}
