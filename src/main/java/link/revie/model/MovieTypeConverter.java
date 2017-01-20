package link.revie.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MovieTypeConverter implements AttributeConverter<MovieType, String> {

	@Override
	public String convertToDatabaseColumn(MovieType arg0) {
		return arg0.getCode();
	}

	@Override
	public MovieType convertToEntityAttribute(String arg0) {
		return MovieType.of(arg0);
	}

}
