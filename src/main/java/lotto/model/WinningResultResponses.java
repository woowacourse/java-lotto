package lotto.model;

import java.util.List;

public class WinningResultResponses {

    public WinningResultResponses(List<WinningResultResponse> responses) {
        this.responses = responses;
    }

    private final List<WinningResultResponse> responses;

    public List<WinningResultResponse> getResponses() {
        return responses;
    }

}
