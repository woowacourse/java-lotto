package lotto.domain.lotto;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    private static final int LOTTO_SIZE = 6;

    public static LottoTicket publishLottoTicketOfRandom() {
        Set<LottoNumber> randomLottoNumbers =
                IntStream.rangeClosed(LottoNumber.MINIMUM_LOTTO_NUMBER, LottoNumber.MAXIMUM_LOTTO_NUMBER)
                        .distinct()
                        .limit(LOTTO_SIZE)
                        .mapToObj(LottoNumber::from)
                        .collect(Collectors.toSet());
        return LottoTicket.from(randomLottoNumbers);
    }

    public static LottoTicket publishLottoTicketFrom(Set<Integer> numbers) {
        Set<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoFactory::publishLottoNumberFrom)
                .collect(Collectors.toSet());
        return LottoTicket.from(lottoNumbers);
    }

    public static LottoNumber publishLottoNumberFrom(int number) {
        return LottoNumber.from(number);
    }

    public static WinningLotto publishWinningLotto(Set<Integer> lottoNumbers, Integer bonusNumber) {
        LottoTicket lottoTicket = publishLottoTicketFrom(lottoNumbers);
        LottoNumber lottoNumber = publishLottoNumberFrom(bonusNumber);
        return new WinningLotto(lottoTicket, lottoNumber);
    }
}

