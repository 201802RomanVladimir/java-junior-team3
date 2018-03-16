package client;

import helper.CommandHelper;

class InputMessageFilter {

    private String[] commandsArray = {"/hist", "/snd"};

    public boolean isCorrect(String line) {
        String tmp = line + " ";
        int index = tmp.indexOf(" ");
        if (index == -1) return  false;

        String command = CommandHelper.TryParseCommand(line);
        if (command == null) return false;

        String message = line.substring(index, line.length());
        if(message.length() > 150){
            System.out.println("Message should not be longer than 150 symbols");
            return false;
        }


        for(String cmd: commandsArray){
            if(cmd.equals(command)){
                return true;
            }
        }

        return false;
    }
}