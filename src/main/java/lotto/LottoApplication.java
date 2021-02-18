package lotto;

import lotto.controller.LottoController;

import java.util.Scanner;

public class LottoApplication {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        LottoController lottoController = new LottoController(scanner);
        lottoController.run();
    }
}
