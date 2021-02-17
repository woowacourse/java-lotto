package lotto;

import java.util.Scanner;
import lotto.controller.LottoController;

public class LottoApplication {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        LottoController lottoController = new LottoController(scanner);
        lottoController.run();
    }
}
