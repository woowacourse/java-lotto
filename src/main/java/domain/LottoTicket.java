package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {

    public static final int END_INDEX = 6;
    public static final int FROM_INDEX = 0;

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        Collections.sort(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public static LottoTicket of(List<Integer> lottoNumbersInput) {
        List<LottoNumber> tempLottoNumbers = lottoNumbersInput.stream()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());

        return new LottoTicket(tempLottoNumbers);
    }

    public static LottoTicket ofAuto() {
        List<LottoNumber> autoLottoNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(autoLottoNumbers);
        return new LottoTicket(autoLottoNumbers.subList(FROM_INDEX, END_INDEX));
    }

    public List<LottoNumber> get() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }
}