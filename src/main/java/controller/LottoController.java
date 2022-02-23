package controller;

import java.util.List;
import java.util.stream.Collectors;
import model.LottoTicket;
import model.LottoTicketDto;
import view.LottoTicketOutputView;
import view.PurchaseMoneyInputView;

public class LottoController {

    public Integer getPurchaseMoney() {
        return (new PurchaseMoneyInputView()).printInputPurchaseMoneyAndGet();
    }

    public void printGeneratedLottoTickets(List<LottoTicket> lottoTickets) {
        List<LottoTicketDto> dto = lottoTickets.stream()
                .map(lottoTicket -> new LottoTicketDto(lottoTicket.lottoNumberValues()))
                .collect(Collectors.toList());
        (new LottoTicketOutputView()).printOutputData(dto);
    }


}
