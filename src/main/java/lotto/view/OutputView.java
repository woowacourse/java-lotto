package lotto.view;

import java.util.List;

import lotto.client.OutputClient;
import lotto.dto.LottoTicketResponse;

public class OutputView {

    private final OutputClient outputClient;

    public OutputView(OutputClient outputClient) {
        this.outputClient = outputClient;
    }

    public void outputTickets(List<LottoTicketResponse> responses) {
        outputClient.output(String.format("%d개를 구매했습니다.\n", responses.size()));
        for (LottoTicketResponse response : responses) {
            outputTicket(response);
        }
        outputClient.output("");
    }

    private void outputTicket(LottoTicketResponse response) {
        outputClient.output(response.getNumbers().toString() + "\n");
    }
}
