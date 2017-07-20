package io.github.devas.net;

import java.util.HashMap;
import java.util.Map;

class T3Protocol implements Protocol {

    public static Map<Integer, String> messages;

    static {
        messages = new HashMap<>();
        messages.put(0, "");
        messages.put(1, "Locale settings\n'E' English | 'P' Polish | Press key: ");
        messages.put(2, "Set X board size: ");
        messages.put(3, "Set Y board size: ");
        messages.put(4, "Set marks to win (cannot be less than 3): ");
        messages.put(5, "John plays first? 'N' = NO, other key = YES: ");
    }

}
