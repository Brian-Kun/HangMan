package hangman;

/**
 * The Array implementation of the GameModel interface.
 */
public class ArrayGameModel implements GameModel {
	
	//counter for adding new variables in previusGuesses array
	int guessArrCount = -1;
	
	/** The number of characters (lower/upper). */
	private static final int ALPHABET_COUNT = 26*2;
	
	//The word that we are trying to guess
	String guessWord;
	
	//Previous guesses with one character in it that gets erased
	char[] previousGuesses = new char[ALPHABET_COUNT];
	
	
	/** hung state */
	private int		state;
	
	/**
	 * Creates a new ArrayGameModel object.
	 * 
	 *  guessWord the word to guess
	 */
	public ArrayGameModel(String guessWord) {// this is the word that we are trying to guess
		// TODO (1)
//		System.out.println("word to guess is "+ guessWord);
		state    = STARTING_STATE;
		this.guessWord = guessWord;
	}
	
	public boolean isPriorGuess(char guess) {
		for(char c: previousGuesses){
			if(c == guess){
				return true;
			}
		}
		return false;
	}
	
	public int numberOfGuesses() {
		String previous = previousGuessString();
		//gets rid of commas, spaces and brackets
		previous = previous.replaceAll("[^a-zA-Z ]", "");
		previous = previous.replaceAll("\\s","");
		return previous.length();
	}
	
	public boolean isCorrectGuess(char guess) {

		if(guessWord.contains(Character.toString(guess))){
			return true;
		}
		return false;
	}
	
	public boolean doMove(char guess) {
		//add the guess to the guess array
		
		
		//if the guess is correct update 
		if(isCorrectGuess(guess) && !isPriorGuess(guess)){
			guessArrCount++;
			previousGuesses[guessArrCount] = guess;
			return true;
		}else{
			if(!isPriorGuess(guess)){
				guessArrCount++;
				previousGuesses[guessArrCount] = guess;
				state++;
			}
		}
		return false;
		
	}

	public boolean inWinningState() {
		
		if(toString().replaceAll("\\s","").equals(guessWord)){
			return true;
		}
		
		return false;
	}

	public boolean inLosingState() {
		if (state == 10){
			return true;
		}
		return false;
	}
	
	public String toString() {
		// Convert everything into an array so I can traverse through it and find the right answer
		char[] charAnswer = new char[guessWord.length()];
		char[] wordCharArr = guessWord.replaceAll("[^a-zA-Z ]", "").toCharArray();
		char[] guessesArr = previousGuessString().toCharArray();
		
		//iterate through the right answer and the guessed words to display the words that have been guessed right next to the blanks
		for(int i = 0; i < wordCharArr.length ; i++){
			for(int j = 0; j < guessesArr.length ; j++){
					if(wordCharArr[i]== guessesArr[j]){
						charAnswer[i] = guessesArr[j];
						break;
					}else{
						charAnswer[i] = '_';
					}//end of else
			}// end of j for loop
		}// end of i for loop
		
		//Add spaces after each blank or word but not at the end 
		String answer = "";
		for(int i = 0; i <charAnswer.length; i++){
			if(i != charAnswer.length-1){
				answer = answer + charAnswer[i] + " ";
			}else{
				answer = answer + charAnswer[i];
			}
			
		}
		
		// TODO (8)
		
		return answer;
	}

	public String previousGuessString() {
		String w = "";
		for(char c : previousGuesses){
			if(c != '\0'){
				w = w + c+", ";
			}
		}
		
		w = w.replaceAll(", $", "");
		String s = "["+ w + "]";
		
//		
		
		return s;
	}
	
	public int getState() {
		return state;
	}

	public String getWord() {

		return guessWord;
	}
	
	
}
