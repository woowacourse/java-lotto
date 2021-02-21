package lotto.view.printer;

public class InputPrinter {
    private InputPrinter() {
    }

    public static void printInputGuideMessageOfPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printInputGuideMessageOfNumberOfManualPurchaseTickets() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printInputGuideMessageOfWinnerLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printInputGuideMessageOfBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printInputGuideMessageOfLottoNumbersToPurchaseManually() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
