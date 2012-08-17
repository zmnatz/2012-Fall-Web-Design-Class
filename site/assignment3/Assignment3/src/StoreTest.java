/**
 * @author Zaven Mnatzakanian
 *
 */
public class StoreTest {
	public static void main(String[] args) {
		Item hammer = new Item("Hammer",10,1.99);
		Item nails = new Item("Nails",15,2.79);
		Item fooBar = new Item("Foo Bar",12,6.59);
		
		System.out.println(hammer);
		System.out.println(nails);
		System.out.println(fooBar);
		
		try{
			hammer.sell(3);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try{
			nails.sell(17);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try{
			fooBar.adjustPrice(.10);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		System.out.println(hammer);
		System.out.println(nails);
		System.out.println(fooBar);
		
		Item items[] = {
			hammer, 
			nails,
			fooBar,
			new Item("Axe",6,12.99),
			new Item("Paint",20,8.99)
		};
		for(Item item : items){
			System.out.println(item.getDesc());
		}
	}
}
