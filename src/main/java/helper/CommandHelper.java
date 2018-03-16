package helper;

public class CommandHelper {
    private CommandHelper() {
    }

    public static String TryParseCommand(String message) {
        String tmp = message + " ";
        int index = tmp.indexOf(" ");
        if (index == -1) return null;
        String command = message.substring(0, index);
        return command;
    }
}
