package util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Utility for safe console input parsing.
 */
public final class InputHelper {
    private final Scanner scanner;

    /**
     * Creates an InputHelper using the provided Scanner.
     *
     * @param scanner Shared scanner instance.
     */
    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads a non empty line.
     *
     * @param prompt Prompt to show.
     * @return Trimmed input string.
     */
    public String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty.");
        }
    }

    /**
     * Reads an integer.
     *
     * @param prompt Prompt to show.
     * @return Parsed integer.
     */
    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    /**
     * Reads a BigDecimal value.
     *
     * @param prompt Prompt to show.
     * @return Parsed BigDecimal.
     */
    public BigDecimal readBigDecimal(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return new BigDecimal(s);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid decimal number.");
            }
        }
    }

    /**
     * Reads a LocalDate in ISO format.
     *
     * @param prompt Prompt to show.
     * @return Parsed LocalDate.
     */
    public LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return LocalDate.parse(s);
            } catch (DateTimeParseException e) {
                System.out.println("Enter date as YYYY-MM-DD.");
            }
        }
    }

    /**
     * Reads a LocalTime in HH:MM format.
     *
     * @param prompt Prompt to show.
     * @return Parsed LocalTime.
     */
    public LocalTime readTime(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return LocalTime.parse(s);
            } catch (DateTimeParseException e) {
                System.out.println("Enter time as HH:MM.");
            }
        }
    }
}