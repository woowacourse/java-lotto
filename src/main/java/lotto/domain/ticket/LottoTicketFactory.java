package lotto.domain.ticket;

import lotto.domain.machine.PurchaseInformation;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketFactory {
    public static LottoTickets getLottoTickets(PurchaseInformation purchaseInformation) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(generateRandomTickets(purchaseInformation));
        lottoTickets.addAll(generateManualTickets(purchaseInformation));
    return new LottoTickets(lottoTickets);
    }

    private static List<LottoTicket> generateRandomTickets(PurchaseInformation purchaseInformation) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i =0 ; i < purchaseInformation.getAutoOrManualInformation().get(LottoType.AUTOMATIC); i++){
            lottoTickets.add(generateRandomTicket());
        }
        return lottoTickets;
    }

    private static LottoTicket generateRandomTicket(){
        return LottoTicket.of(LottoType.AUTOMATIC.generate());
    }

    private static List<LottoTicket> generateManualTickets(PurchaseInformation purchaseInformation) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i =0 ; i < purchaseInformation.getAutoOrManualInformation().get(LottoType.MANUAL); i++){
            lottoTickets.add(generateManualTicket(purchaseInformation.getManualNumbers().get(i)));
        }
        return lottoTickets;
    }
    private static LottoTicket generateManualTicket(List<Integer>numbers){
        return LottoTicket.of(LottoType.MANUAL.generate(numbers));
    }
}
