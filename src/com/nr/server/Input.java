package com.nr.server;

import org.apache.commons.lang3.StringUtils;

public class Input {
    public boolean isValid(String text) {
        if (text.equals("terminate")) {
            return true;
        }

        if (StringUtils.length(text) == 0) {
            return false;
        }

        String[] lines = StringUtils.splitByWholeSeparator(text, "\n");
        for (String line : lines) {
            if (!StringUtils.isNumeric(line) || StringUtils.length(line) != 9) {
                return false;
            }
        }

        return true;
    }
}
