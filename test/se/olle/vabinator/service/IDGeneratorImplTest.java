package se.olle.vabinator.service;

import org.junit.Test;


public class IDGeneratorImplTest {
    @Test
    public void testGenerator() {
        IDGeneratorImpl generator = new IDGeneratorImpl();
        System.out.println(generator.generateRandomLongId());
    }
}
