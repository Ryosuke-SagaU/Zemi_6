public class Main {

    public static void main(String[] args) {


        Zemi zemi = new Zemi();

        double target = 0.000001;
        zemi.calculateWwso(20.0, 30.0,target);
        zemi.calculateWcso(4.0, 8.0, target);
    }
}
