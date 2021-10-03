package com.nr.server;

import org.apache.commons.lang3.StringUtils;

public class Input {
    public boolean isValid(String text) {
        return text.equals("terminate") || (StringUtils.isNumeric(text) && StringUtils.length(text) == 9);
    }
}
