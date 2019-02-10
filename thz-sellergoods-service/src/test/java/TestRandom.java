import java.util.Random;

public class TestRandom {

    public static void main(String[] args) {
        byte[] bytes=new byte[10];
        Random random=new Random();
        random.nextBytes(bytes);
        System.out.println(bytes);

        Random random2=new Random();
        random2.nextBytes(bytes);
        System.out.println(bytes);
    }
}
