package src.com.fatec.gerenciamentohotel.boundary.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {
	private static final long serialVersionUID = 1L;
	private int comprimentoMax;

	public JTextFieldLimit(int max) {
		super();
		this.comprimentoMax = max;
	}

	@Override
	public void insertString(int offset, String txt, AttributeSet attr)
			throws BadLocationException {
		if (txt == null)
			return;

		if ((getLength() + txt.length()) <= comprimentoMax) {
			super.insertString(offset, txt, attr);
		}
	}
}
