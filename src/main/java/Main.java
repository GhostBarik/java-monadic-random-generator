import generator.Generators;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Random g = new Random(123);

        System.out.println("Generating random structures ... ");
        System.out.println(Generators.randomText(50).generate(g));
        System.out.println("*********************************");
        System.out.println(Generators.randomUser().generate(g));
        System.out.println("*********************************");
        System.out.println(Generators.randomGroup().generate(g));
    }
}
