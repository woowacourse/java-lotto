package lotto.domain.ticket;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumbers;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {
    public static final int SIZE_OF_LOTTO_NUMBERS = 6;

    private static final String NUMBER_SIZE_ERROR_MSG_FORMAT = "로또 번호는 %d개여야 합니다. 현재 개수 : %d";
    private static final String DUPLICATE_ERROR_MSG_FORMAT = "로또 번호가 중복되었습니다.";

    private final LottoNumbers lottoNumbers;

    private LottoTicket(LottoNumbers lottoNumbers){
        validateLottoNumberCount(lottoNumbers);
        validateDuplicatedLottoNumbers(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket createLottoTicket(LottoNumbers lottoNumbers){
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicket createLottoTicket(List<Integer> numbers){
        return new LottoTicket(new LottoNumbers(numbers));
    }

    private void validateLottoNumberCount(LottoNumbers lottoNumbers) {
        if (lottoNumbers.list().size() != SIZE_OF_LOTTO_NUMBERS) {
            throw new IllegalArgumentException(
                    String.format(NUMBER_SIZE_ERROR_MSG_FORMAT, SIZE_OF_LOTTO_NUMBERS, lottoNumbers.list().size())
            );
        }
    }

    private void validateDuplicatedLottoNumbers(LottoNumbers lottoNumbers) {
        Set<LottoNumber> duplicateCheck = new HashSet<>(lottoNumbers.list());
        if (lottoNumbers.list().size() != duplicateCheck.size()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MSG_FORMAT);
        }
    }

    public List<LottoNumber> list() {
        return lottoNumbers.list();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoTicket)) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
