package de.srh.toolify.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomGenerator {
	public static int generate() {

        Set<Integer> generatedNumbers = new HashSet<Integer>();
        Random random = new Random();

        int random5DigitNumber = 0;

        while (true) {
            random5DigitNumber = random.nextInt(90000) + 10000; // Generates a random 5-digit number

            if (generatedNumbers.add(random5DigitNumber)) {
                break;
            }
        }
      return random5DigitNumber;
    }
}
