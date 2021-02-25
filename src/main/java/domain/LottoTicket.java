package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {

    public static final int LOTTO_TICKET_SIZE = 6;

    private static final String ERROR_CONTAINS_DUPLICATE = "중복 숫자가 존재합니다.";
    private static final String ERROR_WRONG_SIZE = "로또 숫자의 개수가 6이 아닙니다.";

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(final List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public static LottoTicket generateRandom() {
        return new LottoTicket(LottoNumber.generateList(LOTTO_TICKET_SIZE));
    }

    public static LottoTicket generateManual(final List<Integer> lottoNumbers) {
        List<LottoNumber> numbers = lottoNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        return new LottoTicket(numbers);
    }

    public Rank checkRanking(final LottoTicket winningTicket, LottoNumber bonusNumber) {
        int matching = (int) lottoNumbers.stream()
                .filter(winningTicket::contains)
                .count();

        boolean bonusMatching = contains(bonusNumber);

        return Rank.select(matching, bonusMatching);
    }

    private void validate(final List<LottoNumber> lottoNumbers) {
        validateDuplicateNumbers(lottoNumbers);
        validateIncorrectSize(lottoNumbers);
    }

    private void validateDuplicateNumbers(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != new HashSet<>(lottoNumbers).size()) {
            throw new IllegalArgumentException(ERROR_CONTAINS_DUPLICATE);
        }
    }

    private void validateIncorrectSize(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(ERROR_WRONG_SIZE);
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> toList() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
