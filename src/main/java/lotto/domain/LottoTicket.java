package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {

    public static final int PRICE = 1000;
    public static final int SIZE_OF_LOTTO_NUMBERS = 6;

    private List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE_OF_LOTTO_NUMBERS) {
            throw new RuntimeException();
        }

        Set<LottoNumber> set = new HashSet<>(lottoNumbers);
        if (set.size() != lottoNumbers.size()) {
            throw new RuntimeException();
        }

        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public LottoTicket(String lottoNumbersValue) {
        this(covertToLottoNumbers(lottoNumbersValue));
    }

    private static List<LottoNumber> covertToLottoNumbers(String s) {
        return Arrays.stream(s.split(","))
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public boolean contains(LottoNumber bonusBall) {
        return lottoNumbers.contains(bonusBall);
    }

    public List<LottoNumber> getUnmodifiableList() {
        return Collections.unmodifiableList(lottoNumbers);
    }

}
