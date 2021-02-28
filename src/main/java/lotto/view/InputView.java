package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String getUserInput() {
        try {
            return br.readLine().trim();
        } catch (IOException ie) {
            return getUserInput();
        }
    }

    public static List<String> getManualLottoTickets(int manualTicketsCount) {
        List<String> manualLottoTicketInputs = new ArrayList<>();
        for (int i = 0; i < manualTicketsCount; i++) {
            manualLottoTicketInputs.add(getUserInput());
        }
        return manualLottoTicketInputs;
    }
}
