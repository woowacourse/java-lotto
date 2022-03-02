package lotto.view.input.reader;

import java.util.Iterator;
import java.util.List;

public class CustomReader implements Reader {

    private Iterator<String> textIterator;

    public void initText(final String inputLine) {
        this.textIterator = List.of(inputLine).iterator();
    }

    public void initText(final List<String> inputLines) {
        this.textIterator = inputLines.iterator();
    }

    @Override
    public String readLine() {
        return textIterator.next();
    }

}
