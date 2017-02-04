package link.revie.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.thymeleaf.util.StringUtils;

public enum CategoryType {
	
	ALL("AL", "全般", "All"),
	ACTION("AC", "アクション", "Action"),
	DRAMA("DR", "ドラマ", "Drama"),
	SUSPENCE("SU", "サスペンス", "Suspence"),
	MYSTERY("MY", "ミステリー", "Mystery"),
	FANTASY("FA", "SF/ファンタジー", "Fantasy"),
	MELODRAMA("ME", "恋愛", "MeloDrama"),
	HORROR("HO", "ホラー", "Horror"),
	COMEDY("CO", "コメディ", "Comedy"),
	HISTORY("HI", "歴史・戦争", "History"),
	ANIME("AN", "アニメ", "Anime"),
	NONFICTION("NO", "ノンフィクション", "NonFiction");

	private final String code;
	private final String name;
	private final String path;
	
	private CategoryType(String code, String name, String path) {
		this.code = code;
		this.name = name;
		this.path = path;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public static List<CategoryType> itemValues() {
		return Stream.of(CategoryType.values())
			.filter(c -> c != CategoryType.ALL)
			.collect(Collectors.toList());
	}
	
	public static CategoryType of(String code) {
		return Stream.of(values())
				.filter(c -> StringUtils.equals(c.getCode(), code))
				.findFirst()
				.orElse(null);
	}

	public static Optional<CategoryType> getFromPath(String path) {
		return Stream.of(values())
				.filter(c -> StringUtils.equals(c.getPath(), path))
				.findFirst();
	}
}
