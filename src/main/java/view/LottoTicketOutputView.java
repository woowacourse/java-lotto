package view;

import java.util.List;
import java.util.stream.Collectors;
import model.LottoTicketDto;

public class LottoTicketOutputView implements OutputView<List<LottoTicketDto>> {

    private static final String PURCHASE_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String RIGHT_LIST_COVER = "[";
    private static final String LEFT_LIST_COVER = "]";
    private static final String LIST_JOINING_DELIMITER = ", ";

    @Override
    public void printOutputData(List<LottoTicketDto> lottoTickets) {
        System.out.println(lottoTickets.size() + PURCHASE_COUNT_MESSAGE);
        lottoTickets.forEach(lottoTicket -> System.out.println(toLottoTicketPrintForm(lottoTicket)));
    }

    private String toLottoTicketPrintForm(LottoTicketDto lottoTicket) {
        return RIGHT_LIST_COVER + joiningLottoNumbers(lottoTicket) + LEFT_LIST_COVER;
    }

    private String joiningLottoNumbers(LottoTicketDto lottoTicket) {
        return lottoTicket.getLottoNumbers().stream()
                .map(Object::toString)
                .collect(Collectors.joining(LIST_JOINING_DELIMITER));
    }
}
