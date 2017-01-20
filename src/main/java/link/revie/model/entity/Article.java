package link.revie.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import link.revie.model.CategoryType;
import link.revie.model.CategoryTypeConverter;
import link.revie.model.MovieType;
import link.revie.model.MovieTypeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ARTICLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Column(name = "TITLE")
	private String title;
	@NotNull
	@Column(name = "YEAR")
	private Integer year;
	@NotNull
	@Column(name = "MONTH")
	private Integer month;
	@NotNull
	@Convert(converter = MovieTypeConverter.class)
	@Column(name = "MOVIE_TYPE")
	private MovieType movieType;
	@NotNull
	@ElementCollection(targetClass = CategoryType.class)
	@Convert(converter = CategoryTypeConverter.class)
	@CollectionTable(name = "ARTICLE_CATEGORY")
	@Column(name = "CATEGORY")
	private List<CategoryType> categories;
	@Column(name = "TEXT")
	private String text;
	@OneToMany
	@JoinTable(name = "ARTICLE_ARTICLE",
			joinColumns = { @JoinColumn(name = "FROM_ID", referencedColumnName = "ID") },
			inverseJoinColumns = { @JoinColumn(name = "TO_ID", referencedColumnName = "ID") })
	private List<Article> articles;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public MovieType getMovieType() {
		return movieType;
	}

	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	public List<CategoryType> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryType> categories) {
		this.categories = categories;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
