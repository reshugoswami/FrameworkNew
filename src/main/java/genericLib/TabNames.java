package genericLib;

public enum TabNames {

	ORGANIZATIONS("Organizations"), LEADS("Leads"),CONTACTS("Contacts");
	
	private String tabName;
	
	private TabNames(String tab) {
		this.tabName = tab;
	}
	
	public String getTabName() {
		return tabName;
	}
}
