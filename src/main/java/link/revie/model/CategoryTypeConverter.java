package link.revie.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CategoryTypeConverter implements AttributeConverter<CategoryType, String> {

	@Override
	public String convertToDatabaseColumn(CategoryType arg0) {
		return arg0.getCode();
	}

	@Override
	public CategoryType convertToEntityAttribute(String arg0) {
		return CategoryType.of(arg0);
	}

}
