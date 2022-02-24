package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final int LOTTO_SIZE = 6;

    private static final List<LottoNumber> allLottoNumbers = LottoNumber.getAllLottoNumbers();

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket createAutoLotto() {
        Collections.shuffle(allLottoNumbers);
        return new LottoTicket(allLottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted()
                .collect(Collectors.toList()));
    }

    public static LottoTicket createManualLotto(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException();
        }
        Collections.sort(lottoNumbers);
        return new LottoTicket(lottoNumbers);
    }

    public List<LottoNumber> getNumbers() {
        return lottoNumbers;
    }
}
