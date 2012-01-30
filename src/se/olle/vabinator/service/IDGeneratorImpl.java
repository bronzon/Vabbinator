package se.olle.vabinator.service;

import java.util.Date;
import java.util.Random;

public class IDGeneratorImpl implements IDGenerator {
    @Override
    public long generateRandomLongId() {
        Random random = new Random(new Date().getTime());
        return random.nextLong();
    }
}
