package lotto.model;

import java.util.List;

public class WinningResults {

    private final List<WinningResult> responses;

    public WinningResults(List<WinningResult> responses) {
        this.responses = responses;
    }

    public List<WinningResult> getResponses() {
        return responses;
    }

}
