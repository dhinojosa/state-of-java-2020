package com.xyzcorp.records;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordsTest {

    @Test
    void testCreatingABasicRecord() {
        Album album = new Album("Purple Rain",
            new Genre("Rock"),
            new Artist("Prince", "Nelson", "Prince"));
        assertThat(album.artist().firstName()).isEqualTo("Prince");
    }

    record Tuple2<A, B>(A item1, B item2) {}

    @Test
    void testTupleTwo() {
        var list = List.of("Cabbage", "Lettuce", "Tomatoes", "Broccoli",
            "Bread").stream().sorted().collect(Collectors.toUnmodifiableList());

        var idx = IntStream.range(0, list.size());

        String result = idx
            .mapToObj(i -> new Tuple2<>(i + 1, list.get(i)))
            .map(t -> String.format("%d. %s", t.item1(), t.item2()))
            .collect(Collectors.joining("\n"));

        assertThat(result).isEqualTo("""
            1. Bread
            2. Broccoli
            3. Cabbage
            4. Lettuce
            5. Tomatoes""");
    }
}
