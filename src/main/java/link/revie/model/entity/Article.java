package link.revie.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(value = AuditingEntityListener.class)
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLE_ID_SEQ")
	@SequenceGenerator(name="ARTICLE_ID_SEQ", sequenceName="ARTICLE_ID_SEQ", initialValue=100, allocationSize=100)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "TITLE")
	@NotBlank(message="タイトル：タイトルを入力してください。")
	@Size(min = 1, max = 100, message = "タイトル：1文字以上100文字以内で記入してください。")
	private String title;
	
	@Column(name = "YEAR")
	@NotNull(message="年：公開年を入力してください。")
	@Min(value=1900, message="年：1900以上で入力してください。")
	@Max(value=2100, message="年：2100以下で入力してください。")
	private Integer year;
	
	@Column(name = "MONTH")
	@NotNull(message="月：公開月を入力してください。")
	@Min(value=1, message="月：1以上で入力してください。")
	@Max(value=12, message="月：12以下で入力してください。")
	private Integer month;
	
	@Convert(converter = MovieTypeConverter.class)
	@Column(name = "MOVIE_TYPE")
	private MovieType movieType = MovieType.DOMESTIC;
	
	@NotEmpty(message="カテゴリ：カテゴリを1つ以上選択してください。")
	@ElementCollection(targetClass = CategoryType.class)
	@Convert(converter = CategoryTypeConverter.class)
	@CollectionTable(name = "ARTICLE_CATEGORY")
	@Column(name = "CATEGORY")
	private List<CategoryType> categories;
	
	@Column(name = "POINT")
	@NotNull(message="評価：評価を入力してください。")
	@Min(value=0, message="評価：0以上で入力してください。")
	@Max(value=100, message="評価：100以下で入力してください。")
	private Integer point;
	
	@Column(name = "TEXT")
	@Size(max = 5000, message = "内容：5000文字以内で記入してください。")
	private String text;
	
	@OneToMany
	@JoinTable(name = "ARTICLE_ARTICLE",
			joinColumns = { @JoinColumn(name = "FROM_ID", referencedColumnName = "ID") },
			inverseJoinColumns = { @JoinColumn(name = "TO_ID", referencedColumnName = "ID") })
	private List<Article> articles;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME")
	@CreatedDate
	private Date createdTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_TIME")
	@LastModifiedDate
	private Date modifiedTime;

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
	
	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

}
