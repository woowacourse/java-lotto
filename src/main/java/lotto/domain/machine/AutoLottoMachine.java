package lotto.domain.machine;

import lotto.domain.Money;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTickets;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoMachine {
    public static final int FROM_INDEX = 0;
    public static final int TO_INDEX = 6;

    private static final List<Integer> lottoNumbers = IntStream
            .rangeClosed(LottoNumber.MIN_RANGE, LottoNumber.MAX_RANGE)
            .boxed()
            .collect(Collectors.toList());

    private final LottoMachine lottoMachine;

    public AutoLottoMachine(Money lottoPrice) {
        this.lottoMachine = new LottoMachine(lottoPrice);
    }

    public LottoTickets createTickets(int numberOfTickets) {
        return IntStream.range(0, numberOfTickets)
                .mapToObj(i -> new LottoNumbers(shuffleNumbers()))
                .collect(Collectors.collectingAndThen(Collectors.toList()
                        , lottoNumbersBundle -> lottoMachine.createTickets(numberOfTickets, lottoNumbersBundle)));
    }

    private List<Integer> shuffleNumbers() {
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(FROM_INDEX, TO_INDEX);
    }

    public int calculateNumberOfTickets(Money lottoPurchaseMoney) {
        return lottoMachine.calculateNumberOfTickets(lottoPurchaseMoney);
    }

    public Money getLottoPrice() {
        return lottoMachine.getLottoPrice();
    }
}
