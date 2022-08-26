import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Set<String> alphabet = new HashSet<>();

        String fileName = "SymbolsData";
        String format = ".txt";
        String pathToFile =   fileName + format;

        String delimiter = ",";

        try(FileReader fileReader = new FileReader(pathToFile))
        {
            Scanner scanner = new Scanner(fileReader);

            String[] v1PossibleTypesArray = scanner.nextLine().split(delimiter);
            Set<String> v1PossibleTypes = new HashSet<>(Arrays.asList(v1PossibleTypesArray));

            String[] v2PossibleOperatorsArray = scanner.nextLine().split(delimiter);
            Set<String> v2PossibleOperators = new HashSet<>(Arrays.asList(v2PossibleOperatorsArray));

            String[] v3OneTimeSymbolsArray = scanner.nextLine().split(delimiter);
            Set<String> v3OneTimeSymbols = new HashSet<>(Arrays.asList(v3OneTimeSymbolsArray));

            alphabet.addAll(v1PossibleTypes);
            alphabet.addAll(v2PossibleOperators);
            alphabet.addAll(v3OneTimeSymbols);

            String[] QTransitionsString = scanner.nextLine().split(delimiter);
            HashSet<String> QTransitions = new HashSet<>(Arrays.asList(QTransitionsString));

            String SStartState = scanner.nextLine();

            String[] FEndStatesString = scanner.nextLine().split(delimiter);
            HashSet<String> FEndStates = new HashSet<>(Arrays.asList(FEndStatesString));


            Map<Pair<String, String>, String> transitions = new HashMap<>();
            int numberOfTypes = 4;
            for (int k = 0; k < numberOfTypes; k++) {
                int numberOfLinesForIntType = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < numberOfLinesForIntType; i++) {
                    String[] line = scanner.nextLine().split(delimiter);
                    transitions.put(new Pair(line[0], line[1]), line[2]);
                }
                String currState = scanner.nextLine();
                String targetState = scanner.nextLine();
                transitions.putAll(getAllCombos(v2PossibleOperators, currState, targetState));
            }

            FSM fsm = new FSM(alphabet, QTransitions, SStartState, FEndStates, transitions);

            String toTestString = "char hh = d + l + 54443 + 2321.2;";
            Tokenizer token = new Tokenizer();
            ArrayList<String> toTest = token.tokenize(toTestString);
            System.out.println(fsm.test(toTest));
        }
        catch (WrongExpressionException e){
            System.out.println(e);
        }
    }
    public static Map<Pair<String, String>, String> getAllCombos(Set<String> alphabet,
                                                                  String currState, String targetState){
        Map<Pair<String, String>, String> cartesianProduct=new HashMap<>();
        for (String currentSymbol : alphabet) {
            cartesianProduct.put(new Pair(currentSymbol, currState), targetState);
        }
        return cartesianProduct;
    }
}