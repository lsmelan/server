package com.nr.server;

import org.apache.commons.lang3.StringUtils;

public class Input {
    public void validate(String text) throws Exception {
        if (text.equals("terminate")) {
            return;
        }

        if (StringUtils.length(text) == 0) {
            throw new Exception("input is empty");
        }

        String[] lines = StringUtils.splitByWholeSeparator(text, "\n");
        for (String line : lines) {
            if (!StringUtils.isNumeric(line) || StringUtils.length(line) != 9) {
                throw new Exception("Invalid input provided");
            }
        }
    }
}
