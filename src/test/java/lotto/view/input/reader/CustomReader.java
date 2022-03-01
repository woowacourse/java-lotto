package lotto.view.input.reader;

import java.util.Iterator;
import java.util.List;

public class CustomReader implements Reader {

    private Iterator<String> textIterator;

    public void initText(final String textValue) {
        this.textIterator = List.of(textValue).iterator();
    }

    @Override
    public String readLine() {
        return textIterator.next();
    }

}
