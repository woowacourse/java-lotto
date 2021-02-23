package lotto;

import lotto.controller.LottoController;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		LottoController lottoController = new LottoController();

		lottoController.start(scanner);

		scanner.close();
	}
}
