package util;

import domain.Numbers;

public interface NumberPicker {

    Numbers pickUnique(final int start, final int end, final int count);
}
