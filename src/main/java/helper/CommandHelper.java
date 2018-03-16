package helper;

public class CommandHelper {
    private CommandHelper() {
    }

    public static String TryParseCommand(String message) {
        String tmp = message + " ";
        int index = tmp.indexOf(" ");
        if (index == -1) {
            return null;
        }
        return message.substring(0, index);
    }
}
