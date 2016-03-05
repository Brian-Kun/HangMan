package hangman;

public class LLCharacterNode {
	
	private char info; //data
	private LLCharacterNode link;
	
	public LLCharacterNode(char info){
		this.info = info;
		link = null;
	}
	
	public char getInfo(){
		return info;
	}
	
	public LLCharacterNode getLink(){
		return link;
	}
	
	public void setInfo(char a){
		info = a;
	}
	
	public void setLink(LLCharacterNode a){
		link = a;
	}
	
	public void addToNode(char guess){
		LLCharacterNode currNode = this;
	    while(currNode.link!=null)currNode=currNode.link;
	    currNode.link= new LLCharacterNode(guess);
	}
	
	public static LLCharacterNode stringToLinkedList(String guessWord){
		char[] charArray = guessWord.toCharArray();
		LLCharacterNode currNode = new LLCharacterNode(charArray[0]);
		for(int i = 1 ; i < charArray.length; i++){
			currNode.addToNode(charArray[i]);
		}
		
		return currNode;
	}
	
	

}
