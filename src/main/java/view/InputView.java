package view;

import domain.LottoNumber;
import domain.LottoTicket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int requestUserBalance() {
        System.out.println("구입금액을 입력해 주세요.");
        return parseNumber(scanner.nextLine());
    }

    public static int requestManualLottoCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return parseNumber(scanner.nextLine());
    }

    public static List<LottoTicket> requestManualLottoTickets(int manualLottoCount) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottoTickets.add(createLottoTicket());
        }
        return manualLottoTickets;
    }

    public static LottoTicket requestWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return createLottoTicket();
    }

    public static LottoNumber requestBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return createBonusNumber();
    }

    private static int parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력은 숫자여야 합니다.");
        }
    }

    private static LottoTicket createLottoTicket() {
        List<LottoNumber> lottoNumbers = Arrays.stream(scanner.nextLine().split(", "))
                .map(InputView::parseNumber)
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return LottoTicket.createManualLotto(lottoNumbers);
    }

    private static LottoNumber createBonusNumber() {
        int bonusNumberInput = parseNumber(scanner.nextLine());
        return LottoNumber.of(bonusNumberInput);
    }
}
