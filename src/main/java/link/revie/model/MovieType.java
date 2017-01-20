package link.revie.model;

import java.util.Objects;
import java.util.stream.Stream;

public enum MovieType {
	DOMESTIC("D", "邦画"),
	FOREIGN("F", "洋画");
	
	private final String code;
	private final String name;
	
	private MovieType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static MovieType of(String code) {
		return Stream.of(MovieType.values())
				.filter(m -> Objects.equals(m.getCode(), code))
				.findFirst()
				.orElse(null);
	}

}
