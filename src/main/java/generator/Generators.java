package generator;

import domain.Group;
import domain.User;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generators {

    public static RandomGenerator<Group> randomGroup() {

        List<String> names = Arrays.asList("Greyson", "Pukavci", "Admini");

        return randomItemFromList(names)          .flatMap( name ->
               randomRange(3,6)                   .flatMap( listSize ->
               randomList(randomUser(), listSize)     .map( users ->

                        new Group(name, users)))
        );
    }

    public static RandomGenerator<User> randomUser() {

        List<String> names = Arrays.asList("Pukavec", "Martin", "Olda");

        return randomItemFromList(names)   .flatMap( name ->
               randomBoolean()             .flatMap( married ->
               randomRange(10,50)              .map( age ->

                       new User(name, age, married)))
        );
    }

    public static RandomGenerator<Boolean> randomBoolean() {
        return Random::nextBoolean;
    }

    public static RandomGenerator<Integer> randomRange(int from, int to) {
        return r -> {
            int bound = to - from + 1;
            return from + r.nextInt(bound);
        };
    }

    public static <T> RandomGenerator<List<T>> randomList(RandomGenerator<T> itemGenerator, int length) {
        return r -> Stream.generate(() -> itemGenerator.generate(r)).limit(length).collect(Collectors.toList());
    }

    public static <T> RandomGenerator<T> randomItemFromList(List<T> list) {
        return r -> list.get(r.nextInt(list.size()));
    }

    public static <T> RandomGenerator<String> randomText(int length) {
        return r -> Stream.generate(() -> randomCharacter().generate(r).toString()).limit(length).collect(Collectors.joining());

    }

    public static <T> RandomGenerator<Character> randomCharacter() {
        return r -> {
            final String possibilities = "abcdefghijklmnopqrstuvwxyz0123456789";
            int charIndex = r.nextInt(possibilities.length());
            return possibilities.charAt(charIndex);
        };
    }
}
