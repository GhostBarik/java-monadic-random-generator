package generator;

import java.util.Random;
import java.util.function.Function;

public interface RandomGenerator<A> {

    A generate(Random random);

    default <B> RandomGenerator<B> map(Function<A, B> f) {
        return r -> f.apply(this.generate(r));
    }

    default <B> RandomGenerator<B> flatMap(Function<A, RandomGenerator<B>> f) {
        return r -> {
            A generatedValue = this.generate(r);
            RandomGenerator<B> nextGenerator = f.apply(generatedValue);
            return nextGenerator.generate(r);
        };
    }
}
