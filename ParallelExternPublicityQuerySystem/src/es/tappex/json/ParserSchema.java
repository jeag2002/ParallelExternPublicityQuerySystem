package es.tappex.json;

import java.util.Map;

/**
 * JSON Parser Interface
 * @author Usuario
 */

public interface ParserSchema {
	public String jsonMapToStringConversion(Map JSONMapping, String key) throws NullPointerException;
}
