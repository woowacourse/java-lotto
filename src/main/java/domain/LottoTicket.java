package domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
    public static final int LOTTO_TICKET_SIZE = 6;
    public static final int TICKET_PRICE = 1000;
    public static final String LOTTO_TICKET_SIZE_ERROR_MESSAGE = "한 티켓의 로또 번호는 6개여야 합니다.";
    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(GenerateStrategy generateStrategy) {
        Set<Integer> generatedNumbers = generateStrategy.generateNumbers();
        validateTicketSize(generatedNumbers);
        lottoNumbers = new HashSet<>();
        for (Integer generatedNumber : generatedNumbers) {
            lottoNumbers.add(new LottoNumber(generatedNumber));
        }
    }

    public Set<Integer> getLottoNumberValues() {
        return lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toSet());
    }

    private void validateTicketSize(Set<Integer> generatedNumbers) {
        if (generatedNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(LOTTO_TICKET_SIZE_ERROR_MESSAGE);
        }
    }
}
