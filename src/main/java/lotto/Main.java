package lotto;

import java.util.Scanner;
import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.service.RandomLottoServiceImpl;
import lotto.view.InputView;

public class Main {

    public static void main(String[] args) {
        LottoService lottoService = new LottoService(new RandomLottoServiceImpl());
        InputView inputView = new InputView(new Scanner(System.in));
        LottoController controller = new LottoController(lottoService, inputView);
        controller.run();
    }
}
