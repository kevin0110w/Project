import java.util.ArrayList;
import java.util.List;

public class UserList {
	private List<User> list;
	public UserList() {
		list = new ArrayList<User>();
	}
	
	public void addUser(User u) {
		this.list.add(u);
	}
	
	public void printUsers() {
		int i = 0;
		for (User u : list) {
			System.out.println(i + ": " + u);
			i++;
		}
	}
}
