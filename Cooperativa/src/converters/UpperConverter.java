package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="upperConverter")
public class UpperConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value)
			throws ConverterException {
		return value.toUpperCase();
	}

	public String getAsString(FacesContext context, UIComponent component, Object value)
			throws ConverterException {
		return ((String)value).toUpperCase();
	}
}
