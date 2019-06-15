package lotto.domain.factory;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketsFactory {
    private static LottoTicketsFactory FACTORY_INSTANCE;

    private LottoTicketsFactory() {
    }

    public static LottoTicketsFactory getInstance() {
        if (FACTORY_INSTANCE == null) {
            FACTORY_INSTANCE = new LottoTicketsFactory();
        }

        return FACTORY_INSTANCE;
    }

    public LottoTickets create(Money money, List<String> customLottoNumbers) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int amountOfAutos = money.getTicketCount() - customLottoNumbers.size();

        lottoTickets.addAll(new CustomLottoTickets(customLottoNumbers).purchaseLottoTickets());
        lottoTickets.addAll(new AutoLottoTickets(amountOfAutos).purchaseLottoTickets());

        return new LottoTickets(lottoTickets);
    }

    private class CustomLottoTickets implements LottoTicketsFactoryStrategy {
        private final List<String> customLottoNumbers;

        CustomLottoTickets(final List<String> customLottoNumbers) {
            this.customLottoNumbers = customLottoNumbers;
        }

        @Override
        public List<LottoTicket> purchaseLottoTickets() {
            List<LottoTicket> customLottoTickets = new ArrayList<>();

            for (String customLottoNumber : customLottoNumbers) {
                customLottoTickets.add(LottoTicketFactory.getInstance().create(customLottoNumber));
            }

            return customLottoTickets;
        }
    }

    private class AutoLottoTickets implements LottoTicketsFactoryStrategy {
        private final int amountOfAutos;

        AutoLottoTickets(final int amountOfAutos) {
            this.amountOfAutos = amountOfAutos;
        }

        @Override
        public List<LottoTicket> purchaseLottoTickets() {
            List<LottoTicket> autoLottoTickets = new ArrayList<>();

            for (int i = 0; i < amountOfAutos; i++) {
                autoLottoTickets.add(LottoTicketFactory.getInstance().create());
            }

            return autoLottoTickets;
        }
    }
}
