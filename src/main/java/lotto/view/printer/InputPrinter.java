package lotto.view.printer;

public class InputPrinter {
    private InputPrinter() {
    }

    public static void printPurchasePriceInputGuideMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printNumberOfManualPurchaseTicketsInputGuideMessage() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printWinnerLottoNumbersInputGuideMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printBonusNumberInputGuideMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printLottoNumbersToManuallyPurchaseInputGuideMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
