package lotto.view;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.vo.ManualTicketSize;

public enum InputView {

    INSTANCE;

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    public static final String INPUT_TICKET_SIZE_MANUALLY_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String INPUT_LOTTO_NUMBERS_MANUALLY_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

    public interface IndividualInput<T> {

        T get() throws IOException, IllegalArgumentException;

    }
    public <T> T commonInputProcess(IndividualInput<T> individualInputs) {
        try {
            return individualInputs.get();
        } catch (IOException | IllegalArgumentException e) {
            out.println(e.getMessage());
            return commonInputProcess(individualInputs);
        }
    }
    public String inputMoney() throws IOException {
        out.println(INPUT_MONEY_MESSAGE);
        return reader.readLine();
    }

    public String inputLottoTicketSizeManually() throws IOException {
        out.println(INPUT_TICKET_SIZE_MANUALLY_MESSAGE);
        return reader.readLine();
    }

    public List<String> inputTicketNumbersManually(ManualTicketSize manualTicketSize) throws IOException {
        out.println(INPUT_LOTTO_NUMBERS_MANUALLY_MESSAGE);
        List<String> manualTicketNumbers = new LinkedList<>();
        for (int i = 0; i < manualTicketSize.ticketNumber(); i++) {
            manualTicketNumbers.add(reader.readLine());
        }
        return manualTicketNumbers;
    }

    public String inputWinningNumbers() throws IOException {
        out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        return reader.readLine();
    }

    public String inputBonusBall() throws IOException {
        out.println(INPUT_BONUS_BALL_MESSAGE);
        return reader.readLine();
    }

    public void closeResource() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
