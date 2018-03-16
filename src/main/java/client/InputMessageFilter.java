package client;

public class InputMessageFilter {

    String[] commandsArray = {"/hist", "/snd"};

    public boolean isCorrect(String line) {

        int index = line.indexOf(" ");
        String message = line.substring(index, line.length());
        String command = line.substring(0,index);
        if(message.length()>150){
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
