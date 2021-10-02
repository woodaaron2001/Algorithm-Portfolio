package HanoiGame;
public class stack {

	//array data struct used here could of also been implemented with linked list 
	//but just wanted a quick implementation 
	disc stack[] = new disc[10];
	private int stackHeight;
	
	
	//getters + setter
	public int height() {
		return stackHeight;
	}
	public disc getDisc() {
		return stack[stackHeight];
	}
	public String printStack(int x) {

			return stack[x].toString();
	}
	public void setHeight(int x) {
		if( stack[0].size() != 0)
		stackHeight += x;
	}

	
	//constructor
	public stack(int n,int init ) {
		for(int x =0 ;x <n; x++) {
			//deals with stacks with no discs
			if(init != -1) {
			stack[x] = new disc(n-x,n);
			stackHeight = n-1;
			}
			//stacs with discs
			else {
				stack[x] = new disc(0,n);
				stackHeight = 0;
			}
		}
	}
	
	
	
}
