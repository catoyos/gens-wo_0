package main_logic.io;

import java.util.Collection;
import java.util.LinkedList;

class IOMenuOption {
	String key;
	String text;
	int idx;

	public IOMenuOption(String key, String text, int idx) {
		this.key = key;
		this.text = text;
		this.idx = idx;
	}

	@Override
	public String toString() {
		return "[" + key + "]:\t" + text;
	}
}

public class IOMenu {
	private String titulo;
	private Collection<IOMenuOption> options;
	private String errorMessage;

	public IOMenu() {
		this("", new LinkedList<IOMenuOption>(), IOStrings.MENU_OPT_UNKNOWN);
	}

	public IOMenu(String titulo) {
		this(titulo, new LinkedList<IOMenuOption>(), IOStrings.MENU_OPT_UNKNOWN);
	}

	public IOMenu(String titulo, Collection<IOMenuOption> options) {
		this(titulo, options, IOStrings.MENU_OPT_UNKNOWN);
	}

	public IOMenu(String titulo, String errorMessage) {
		this(titulo, new LinkedList<IOMenuOption>(), errorMessage);
	}

	public IOMenu(Collection<IOMenuOption> options) {
		this("", options, IOStrings.MENU_OPT_UNKNOWN);
	}

	public IOMenu(Collection<IOMenuOption> options, String errorMessage) {
		this("", options, errorMessage);
	}

	public IOMenu(String titulo, Collection<IOMenuOption> options, String errorMessage) {
		this.titulo = titulo;
		this.options = options;
		this.errorMessage = errorMessage;
	}

	public void addOption(String key, String text, int idx) {
		options.add(createOption(key, text, idx));
	}

	public static IOMenuOption createOption(String key, String text, int idx) {
		return new IOMenuOption(key, text, idx);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Collection<IOMenuOption> getOptions() {
		return options;
	}

	public void setOptions(Collection<IOMenuOption> options) {
		this.options = options;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
