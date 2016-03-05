package hangman;

public class LinkedListGameModel implements GameModel {
	
	String guessWord;
	
	LLCharacterNode previousGuesses = new LLCharacterNode('\0');
	
	int numberOfGuesses = 0;
	/** hung state */
	private int	state;
	
	public LinkedListGameModel(String guessWord){
		System.out.println("The Word is "+ guessWord);
		state = STARTING_STATE;
		this.guessWord = guessWord;
	}

	@Override
	public boolean isPriorGuess(char guess) {
		if(Character.isDigit(guess)){
			
		}
		LLCharacterNode currNode = previousGuesses;
		while(currNode != null){
			if(currNode.getInfo() == guess){
				return true;
			}
			currNode = currNode.getLink();
		}
		return false;
	}

	@Override
	public int numberOfGuesses() {
		return numberOfGuesses;
	}

	@Override
	public boolean isCorrectGuess(char guess) {
		if(guessWord.contains(Character.toString(guess))){
			return true;
		}
		return false;
	}

	@Override
	public boolean doMove(char guess) {
		if(isCorrectGuess(guess) && !isPriorGuess(guess)){
			previousGuesses.addToNode(guess);
			numberOfGuesses++;
			return true;
		}else{
			if(!isPriorGuess(guess)){
				previousGuesses.addToNode(guess);
				state++;
				numberOfGuesses++;
			}
		}
		return false;
	}

	@Override
	public boolean inWinningState() {
		if(toString().replaceAll("\\s","").equals(guessWord)){
			return true;
		}
		return false;
	}

	@Override
	public boolean inLosingState() {
		if(state == 10){
			return true;
		}
		return false;
	}

	@Override
	public int getState() {
		return state;
	}
	
	public String toString(){
		
		String answer = "";
		
		for(int i = 0; i <guessWord.length(); i++){
			if(isPriorGuess(guessWord.charAt(i))){
				answer += guessWord.charAt(i);
				if(i != guessWord.length()-1){
					answer = answer + " ";
				}
			}else{
				if(i != guessWord.length()-1){
					answer = answer + "_ ";
				}
				else if(i == guessWord.length()-1){
					answer = answer +  "_";
				}
			}
		}
		 
		return answer;
	}

	@Override
	public String previousGuessString() {
		 String logString ="";
		 LLCharacterNode node = previousGuesses;
		 while (node != null) {
		 logString += node.getInfo( ) + ", ";
		 node = node.getLink( );
		 }
		 logString = logString.substring(3, logString.length() - 2);
		 return "[" + logString + "]";	
	}

	@Override
	public String getWord() {
		return guessWord;
	}

}
