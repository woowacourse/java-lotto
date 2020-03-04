package domain.buyingstrategy;

import domain.buyingstrategy.buyinginformation.BuyingInformation;
import domain.buyingstrategy.buyinginformation.Money;
import domain.lottonumbers.LottoTicket;
import domain.lottonumbers.lottonumber.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class RandomBuyingStrategy implements BuyingStrategy {

    private static final Random random = new Random();

    @Override
    public boolean isAvailable(BuyingInformation buyingInformation) {
        Money moneyToBuy = buyingInformation.getMoneyAfterBuyingManualTickets();

        return moneyToBuy.getNumberOfTickets() > 0;
    }

    @Override
    public List<LottoTicket> generateTickets(BuyingInformation buyingInformation) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        Money moneyToBuy = buyingInformation.getMoneyAfterBuyingManualTickets();
        int numberOfTickets = moneyToBuy.getNumberOfTickets();
        for (int i = 0; i < numberOfTickets; i++) {
            Set<LottoNumber> newRandomLottoNumbers = parseNumbersToLottoNumbers(generateRandomNumbers());
            lottoTickets.add(new LottoTicket(newRandomLottoNumbers));
        }

        return lottoTickets;
    }

    private Set<LottoNumber> parseNumbersToLottoNumbers(Set<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::of)
                .collect(toSet());
    }

    private Set<Integer> generateRandomNumbers() {
        return random.ints(LottoNumber.MIN_BOUND, LottoNumber.MAX_BOUND + 1)
                .distinct()
                .limit(LottoTicket.SIZE)
                .boxed()
                .collect(toSet());
    }
}
