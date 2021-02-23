package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoTicket {
    public static final int LOTTO_TICKET_SIZE = 6;

    private final List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> numbers) {
        validateLottoNumbers(numbers);
        Collections.sort(numbers);
        this.lottoTicket = new ArrayList<>(numbers);
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
        return Collections.unmodifiableList(lottoTicket);
    }
}
