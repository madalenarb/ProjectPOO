package main;

public class cmdTextFormatter {
    public static final String BOLD = "\033[1m";
    public static final String UNDERLINE = "\033[4m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String CYAN = "\033[36m";

    public static String format(String text, String... formatOptions){
        StringBuilder formattedText = new StringBuilder();
        for(String option : formatOptions){
            formattedText.append(option);
        }

        formattedText.append(text).append("\033[0m");

        return formattedText.append(text).append("\033[0m").toString();
    }
}
