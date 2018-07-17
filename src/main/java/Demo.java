public class Demo {

    public static void main(String[] args) {
        System.out.println(fun1());
    }

    public static int fun1() {
        try {
            System.out.println("a");
            return 1;
        } finally {
            System.out.println("c");

        }
    }
}
