package lotto.view;

public class OutputView {
	public static final String PURCHASE_COMPLETE_MESSAGE = "%d개를 구매했습니다.\n";

	public static void printExceptionMessage(String exceptionMessage) {
		System.out.println(exceptionMessage);
	}
	
	public static void printPurchaseCompleteMessage(int numberOfLotto) {
		System.out.printf(PURCHASE_COMPLETE_MESSAGE, numberOfLotto);
	}
}
