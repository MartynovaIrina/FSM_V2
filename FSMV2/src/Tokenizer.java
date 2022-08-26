import java.util.*;

public class Tokenizer <K> {
    public ArrayList<String> tokenize(K lineToBreakDown) throws WrongExpressionException{

        ArrayList<String> tokens = new ArrayList<>();

        String lineToBreakDownString = lineToBreakDown.toString();

        int currIndex = 0;
        String currentChar;

        String[] possibleFullSymbolsString = new String[] {"-", "+", "*", "/", "=", ";"};
        Set<String> possibleFullSymbols = new HashSet<>(Arrays.asList(possibleFullSymbolsString));

        String[] possibleNumbersString = "1234567890".split("");
        Set<String> possibleNumbers = new HashSet<>(Arrays.asList(possibleNumbersString));

        String[] possibleNumbersFloatDoubleString = "1234567890.f".split("");
        Set<String> possibleNumbersFloatDouble = new HashSet<>(Arrays.asList(possibleNumbersFloatDoubleString));

        String[] possibleTypesString = new String[] {"char", "int", "float",
                "double"};
        Set<String> possibleTypes = new HashSet<>(Arrays.asList(possibleTypesString));

        Set<String> possibleLetters = new HashSet<>();
        //adding small and big letters
        int asciiTableSmallLettersStart = 97;
        int asciiTableSmallLettersEnd = 122;
        int asciiTableBigLettersStart = 65;
        int differenceBetweenSmallAndBigLetters = asciiTableSmallLettersStart - asciiTableBigLettersStart;
        String smallLetter;
        String bigLetter;
        for (int i = asciiTableSmallLettersStart; i <= asciiTableSmallLettersEnd; i++){
            smallLetter = String.valueOf((char) i);
            bigLetter = String.valueOf((char) (i - differenceBetweenSmallAndBigLetters));
            possibleLetters.add(smallLetter);
            possibleLetters.add(bigLetter);
        }
        //end of adding small and big letters

        while (currIndex < lineToBreakDownString.length()){
            currentChar = String.valueOf(lineToBreakDownString.charAt(currIndex));
            if (possibleFullSymbols.contains(currentChar)){
                tokens.add(currentChar);
                currIndex++;
            } else if (possibleNumbers.contains(currentChar)) {
                StringBuilder wholeNumber = new StringBuilder();
                int fCounter = 0;
                int dotCounter = 0;
                String number;
                char lastElement;
                do {
                    wholeNumber.append(currentChar);
                    currIndex++;
                    if (currIndex >= lineToBreakDownString.length()) {
                        break;
                    }
                    else if (currentChar.equals(".")){
                        dotCounter++;
                    }
                    else if (currentChar.equals("f")){
                        fCounter++;
                    } else if (fCounter > 1 || dotCounter > 1) {
                        throw new WrongExpressionException(ExceptionsType.WRONG_SYMBOL.getErrorString());
                    }
                    currentChar = String.valueOf(lineToBreakDownString.charAt(currIndex));
                } while (possibleNumbersFloatDouble.contains(currentChar));
                number = wholeNumber.toString();
                lastElement = number.charAt(number.length() - 1);
                if (fCounter == 1 && dotCounter == 1 && lastElement == 'f'){ //checking if value is float
                    tokens.add("floatVar");
                }
                else if (dotCounter == 1){ //checking if value is double
                    tokens.add("doubleVar");
                }
                else {
                    tokens.add("intVar");
                }
            } else if (possibleLetters.contains(currentChar)) {
                StringBuilder wholeString = new StringBuilder();
                String var;
                int startIndex = currIndex;
                do {
                    wholeString.append(currentChar);
                    currIndex++;
                    if (currIndex >= lineToBreakDownString.length()) {
                        break;
                    }
                    currentChar = String.valueOf(lineToBreakDownString.charAt(currIndex));
                } while (possibleLetters.contains(currentChar));
                var = wholeString.toString();
                if (possibleTypes.contains(var) && startIndex == 0){
                    tokens.add(var);
                }
                else {
                    tokens.add("varName");
                }
            } else {
                if (!currentChar.equals(" ")) {
                    throw new WrongExpressionException(ExceptionsType.WRONG_SYMBOL.getErrorString());
                }
                currIndex++;
            }
        }
        return tokens;
    }
}