package model;

public interface DocumentListener {
	public enum DocumentEvent{
		ADD_FIGURE,  //SE AÑADIO FIGURA
		REMO_FIGURE, //SE REMOVIO FIGURE
		SEL_FIGURE,  //SE SELCCIONO UNA FIGURA
		SEL_FIGURES, //SE SELECCIONARON VARIAS FIGURAS
		SAVED,
		NEW,
		LOADED,
		DESELECTED,
		//ESTOS SON REFERENCIAS A OBJETOS
	}
	
	public void documentChange(DocumentEvent de);

}
