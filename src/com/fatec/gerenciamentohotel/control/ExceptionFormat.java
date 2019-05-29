package src.com.fatec.gerenciamentohotel.control;

import java.util.HashMap;
import java.util.Map;

public class ExceptionFormat {
	public static Map<String, String> formatarTituloECorpo(String exceptionMsg) {
		String title, description;
		Map<String, String> h = new HashMap<>();;
		try {
		String[] msg = exceptionMsg.split("\n");
		title = msg[1].substring(
				msg[1].indexOf("[") + 1,
				msg[1].indexOf("]"));
		
		description = msg[2].substring(
				msg[2].indexOf("[") + 1,
				msg[2].indexOf("]"));
		h.put("title", title);
		h.put("description", description);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return h.isEmpty() ? null : h;
	}
}
