package lotto.domain.lotto;

import lotto.utils.ParseUtil;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {
    public static final int LOTTO_TICKET_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validateLottoNumbers(numbers);
        Collections.sort(numbers);
        this.lottoNumbers = new ArrayList<>(numbers);
    }

    public static LottoTicket auto() {
        return new LottoTicket(AutoNumbersFactory.generateAutoLottoTicket());
    }

    public static LottoTicket manual(List<String> lottoNumberStrings) {
        List<LottoNumber> lottoNumbers = lottoNumberStrings.stream()
                .map(i -> LottoNumber.valueOf(ParseUtil.parseInt(i)))
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
    }

    private void validateLottoNumbers(List<LottoNumber> numbers) {
        checkLottoSize(numbers);
        checkDuplicateNumber(numbers);
    }

    private void checkLottoSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 티켓 숫자는 6개 입니다.");
        }
    }

    private void checkDuplicateNumber(List<LottoNumber> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 티켓 내 중복된 로또 숫자가 있습니다.");
        }
    }

    public List<LottoNumber> lottoTicket() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
