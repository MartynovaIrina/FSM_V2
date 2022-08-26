
import java.util.Map;
import java.util.Set;

public class FSM<T, E>{

    private Set<T> VAlphabet;
    private Set<T> QStates;
    private E SCurrState;
    private Set<E> FFiniteStates;
    private Map<Pair<T, E>, E> transitions;

    public FSM(Set<T> VAlphabet, Set<T> QStates, E SCurrState,
               Set<E> FFiniteStates, Map<Pair<T, E>, E> transitions) {
        setVAlphabet(VAlphabet);
        setQStates(QStates);
        setSCurrState(SCurrState);
        setFFiniteStates(FFiniteStates);
        setTransitions(transitions);
    }
    public boolean test(final Iterable<T> request) throws WrongExpressionException {
        for (T token : request) {
            Pair<T, E> currentPair = new Pair(token, this.SCurrState);
            if (transitions.containsKey(currentPair)) {
                this.SCurrState = transitions.get(currentPair);
            }
            else {
                throw new WrongExpressionException(ExceptionsType.TRANSITION_ERROR.getErrorString());
            }
        }
        return this.FFiniteStates.contains(this.SCurrState);
    }

    public Set<T> getVAlphabet() {
        return VAlphabet;
    }

    public void setVAlphabet(Set<T> VAlphabet) {
        this.VAlphabet = VAlphabet;
    }

    public Set<T> getQStates() {
        return QStates;
    }

    public void setQStates(Set<T> QStates) {
        this.QStates = QStates;
    }

    public E getSCurrState() {
        return SCurrState;
    }

    public void setSCurrState(E SCurrState) {
        this.SCurrState = SCurrState;
    }

    public Set<E> getFFiniteStates() {
        return FFiniteStates;
    }

    public void setFFiniteStates(Set<E> FFiniteStates) {
        this.FFiniteStates = FFiniteStates;
    }

    public Map<Pair<T, E>, E> getTransitions() {
        return transitions;
    }

    public void setTransitions(Map<Pair<T, E>, E> transitions) {
        this.transitions = transitions;
    }
}