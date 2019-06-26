package com.fatec.gerenciamentohotel.boundary.window.filtro_usuario;

import java.awt.Component;

public class FiltroDeComponentes {
	public FiltroDeComponentes(Component... components) {
		for (Component c : components) {
			c.setEnabled(false);
			c.setVisible(false);
		}
	}
}
