package view;

public interface ToolListener {
	
	public enum ToolEvent{
		CLIN,
		CREC,
		CELI,
		CTXT,
		SELT, 
	}
	
	public void toolChange(ToolEvent e);

}
