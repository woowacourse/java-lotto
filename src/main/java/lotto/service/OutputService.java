package lotto.service;

import java.util.List;
import lotto.costant.OutputMessage;
import lotto.domain.Lotto;
import lotto.view.OutputView;

public class OutputService {

    private final OutputView outputView;

    public OutputService(OutputView outputView) {
        this.outputView = outputView;
    }

    public void printLottos(List<Lotto> lottos) {
        String content = String.format(OutputMessage.PURCHASE_COMPLETE.getContent(), lottos.size());
        outputView.printLine(content);
        for (Lotto lotto : lottos) {
            String lottoNumbers = lotto.getNumbers().toString();
            outputView.printLine(lottoNumbers);
        }
    }
}
