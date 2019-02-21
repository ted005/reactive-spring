package io.github.ted005.reactivespring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveSpringApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void create() {
		String[] arr = new String[]{"hello", "world"};
		Flux<String> flux = Flux.just(arr);
		flux.subscribe(System.out::println);

		StepVerifier.create(flux)
				.expectNext("hello")
				.expectNext("world")
				.expectComplete();

		Mono<String> mono = Mono.just("hi world");
		mono.subscribe(System.out::println);

		StepVerifier.create(mono)
				.expectNext("hi world")
				.expectComplete();
	}

	@Test
	public void createFromCollection() {
		List<String> list = Arrays.asList("hello", "world");
		Flux<String> flux = Flux.fromIterable(list);

		StepVerifier.create(flux)
				.expectNext("hello")
				.expectNext("world")
				.expectComplete();

		Flux<Integer> range = Flux.range(0, 5);

		StepVerifier.create(range)
				.expectNext(0)
				.expectNext(1)
				.expectNext(2)
				.expectNext(3)
				.expectNext(4)
				.expectNext(5)
				.expectComplete();

		Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1)).take(5);
		StepVerifier.create(longFlux)
				.expectNext(1L)
				.expectNext(2L)
				.expectNext(3L)
				.expectNext(4L)
				.expectNext(5L)
				.expectComplete();

	}

	@Test
	public void mergeFlux() {
		Flux<String> source1 = Flux.just("hello", "world");
		Flux<String> source2 = Flux.just("hi", "ted");

		Flux<String> merge = source1.mergeWith(source2);
		merge.subscribe(System.out::println);

		StepVerifier.create(merge)
				.expectNext("hello")
				.expectNext("world")
				.expectNext("hi")
				.expectNext("ted")
				.expectComplete();

	}

	@Test
	public void zipFlux() {
		Flux<String> source1 = Flux.just("hello", "world");
		Flux<String> source2 = Flux.just("hi", "ted");

		Flux<Tuple2<String, String>> zip = source1.zipWith(source2);
		zip.subscribe(tuple -> {
			System.out.println(tuple.getT1() + " -> " + tuple.getT2());
		});

	}

	@Test
	public void skipFlux() {
		Flux<String> source1 = Flux.just("hello", "world", "hi", "ted");

		Flux<String> skip = source1.skip(2);
		skip.subscribe(System.out::println);
	}

	@Test
	public void takeFlux() {
		Flux<String> source1 = Flux.just("hello", "world", "hi", "ted");

		Flux<String> skip = source1.take(2);
		skip.subscribe(System.out::println);
	}

	@Test
	public void filterFlux() {
		Flux<String> source1 = Flux.just("hello", "world", "hi", "ted");

		Flux<String> skip = source1.filter(s -> s.startsWith("h"));
		skip.subscribe(System.out::println);
	}

	@Test
	public void distinctFlux() {
		Flux<String> source1 = Flux.just("hello", "hello", "world", "hi", "ted");

		Flux<String> skip = source1.filter(s -> s.startsWith("h")).distinct();
		skip.subscribe(System.out::println);

		StepVerifier.create(source1)
				.expectNext("hello")
				.expectNext("world")
				.expectNext("hi")
				.expectNext("ted")
				.expectComplete();
	}

	@Test
	public void mapFlux() {
		Flux<String> source1 = Flux.just("hello", "world", "hi", "ted");

		Flux<String> skip = source1.map(s -> s + " is mapped");
		skip.subscribe(System.out::println);
	}

	@Test
	public void flatMapFlux() {
		Flux<String> source1 = Flux.just("hello world", "hi ted");

		Flux<String> flatMap = source1.flatMap(s -> Mono.just(s).map(s1 -> {
			String[] strings = s1.split("\\s");
			return new String(strings[0] + " - " + strings[1]);
		}));

		flatMap.subscribe(System.out::println);
	}

	@Test
	public void bufferFlux() {
		Flux<String> source1 = Flux.just("hello", "world", "hi", "ted");
		Flux<List<String>> buffer = source1.buffer(2);
		buffer.subscribe(strings -> System.out.println(strings.size()));

	}

	@Test
	public void collectListFlux() {
		Flux<String> source1 = Flux.just("hello", "world", "hi", "ted");
		Mono<List<String>> mono = source1.collectList();

		mono.subscribe(System.out::println);
	}

	@Test
	public void collectMapFlux() {
		Flux<String> source1 = Flux.just("hello", "world", "ted");

		Mono<Map<Character, String>> map = source1.collectMap(s -> s.charAt(0));
		map.subscribe(characterStringMap -> System.out.println(characterStringMap.get('t')));
	}

	@Test
	public void allFlux() {
		Flux<String> source1 = Flux.just("hello", "world", "ted");

		Mono<Boolean> mono = source1.all(s -> s.contains("e"));
		mono.subscribe(System.out::println);
	}

	@Test
	public void anyFlux() {
		Flux<String> source1 = Flux.just("hello", "world", "ted");

		Mono<Boolean> mono = source1.any(s -> s.contains("e"));
		mono.subscribe(System.out::println);
	}

}
