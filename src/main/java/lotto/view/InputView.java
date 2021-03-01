package lotto.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String getInput() {
        try {
            return br.readLine().trim();
        } catch (IOException ie) {
            return getInput();
        }
    }

    public static List<String> getManualTickets(int manualTicketsCount) {
        List<String> manualLottoTicketInputs = new ArrayList<>();
        for (int i = 0; i < manualTicketsCount; i++) {
            manualLottoTicketInputs.add(getInput());
        }
        return manualLottoTicketInputs;
    }
}
