package domain;

import static utils.Messages.LOTTO_NUMBER_RANGE_ERROR_MESSAGE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {

    public static final int END_INDEX = 6;
    public static final int FROM_INDEX = 0;

    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        Collections.sort(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public static LottoTicket of(List<Integer> lottoNumbersInput) {
        validate(lottoNumbersInput);
        List<LottoNumber> tempLottoNumbers = lottoNumbersInput.stream()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());

        return new LottoTicket(tempLottoNumbers);
    }

    private static void validate(List<Integer> lottoNumbersInput) {
        int wrongNumberCount = (int) lottoNumbersInput.stream()
            .filter(
                number -> LOTTO_NUMBER_LOWER_BOUND <= number && number <= LOTTO_NUMBER_UPPER_BOUND)
            .count();
        if (wrongNumberCount != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
        }
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