
public abstract class DocumentToData {
		
	final void getData() {
		createDoc();
		fetchElements();
		dealData();
	}
	
	abstract void createDoc();
	
	abstract void fetchElements();
	
	abstract void dealData();
}
