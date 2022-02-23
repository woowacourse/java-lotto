package view;

import java.util.List;
import java.util.stream.Collectors;
import model.LottoTicketDto;

public class LottoTicketOutputView implements OutputView<List<LottoTicketDto>> {

    @Override
    public void printOutputData(List<LottoTicketDto> lottoTickets) {
        System.out.println(lottoTickets.size() + "개를 구매했습니다.");
        lottoTickets.forEach(lottoTicket -> System.out.println(toLottoTicketPrintForm(lottoTicket)));
    }

    private String toLottoTicketPrintForm(LottoTicketDto lottoTicket) {
        return "[" +
                lottoTicket.getLottoNumbers().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "))
                + "]";
    }
}
