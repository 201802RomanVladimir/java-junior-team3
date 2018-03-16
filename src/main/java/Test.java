import client.InputMessageFilter;

public class Test {
    public static void main(String[] args) {
        InputMessageFilter imf = new InputMessageFilter();
        System.out.println(imf.isCorrect("/snd aaaaa"));
        System.out.println(imf.isCorrect("/snddsa aaaaa"));
        System.out.println(imf.isCorrect("/hist aaaaa"));
        System.out.println(imf.isCorrect("/sn aaaaa"));
        System.out.println(imf.isCorrect("/snd aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }
}
