package helper;

public class CommandHelper {
    private CommandHelper() {
    }

    public static String tryParseCommand(String message) {
        String tmp = message + " ";
        int index = tmp.indexOf(" ");
        if (index == -1) {
            return null;
        }
        return message.substring(0, index);
    }
}
