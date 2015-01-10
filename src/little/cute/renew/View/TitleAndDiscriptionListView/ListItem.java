package little.cute.renew.View.TitleAndDiscriptionListView;

public class ListItem {
	 
	  private String title;
	  private String description;
	 
	  public ListItem(String title, String description){
	    this.title = title;
	    this.description = description;
	  }
	  public ListItem(String description){
		    this.description = description;
		  }
	 
	  public String getTitle(){
	    return title;
	  }
	 
	  public String getDescription(){
	    return description;
	  }
}