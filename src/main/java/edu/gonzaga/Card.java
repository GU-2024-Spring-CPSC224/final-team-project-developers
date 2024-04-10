package src;

import java.util.Objects;

public class Card {
    private final int rank;
    private final int suit;

    // Suits
    public final static int DIAMONDS = 1;
    public final static int CLUBS    = 2;
    public final static int HEARTS   = 3;
    public final static int SPADES   = 4;

    // Ranks
    public final static int ACE   = 1;
    public final static int DEUCE = 2;
    public final static int THREE = 3;
    public final static int FOUR  = 4;
    public final static int FIVE  = 5;
    public final static int SIX   = 6;
    public final static int SEVEN = 7;
    public final static int EIGHT = 8;
    public final static int NINE  = 9;
    public final static int TEN   = 10;
    public final static int JACK  = 11;
    public final static int QUEEN = 12;
    public final static int KING  = 13;

    public Card(int rank, int suit) {
        assert isValidRank(rank);
        assert isValidSuit(suit);
        this.rank = rank;
        this.suit = suit;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public static boolean isValidRank(int rank) {
        return ACE <= rank && rank <= KING;
    }

    public static boolean isValidSuit(int suit) {
        return DIAMONDS <= suit && suit <= SPADES;
    }

    public static String rankToString(int rank) {
        switch (rank) {
            case ACE:
                return "Ace";
            case DEUCE:
                return "Deuce";
            case THREE:
                return "Three";
            case FOUR:
                return "Four";
            case FIVE:
                return "Five";
            case SIX:
                return "Six";
            case SEVEN:
                return "Seven";
            case EIGHT:
                return "Eight";
            case NINE:
                return "Nine";
            case TEN:
                return "Ten";
            case JACK:
                return "Jack";
            case QUEEN:
                return "Queen";
            case KING:
                return "King";
            default:
                
                return null;
        }
    }

    public static String suitToString(int suit) {
        switch (suit) {
            case DIAMONDS:
                return "Diamonds";
            case CLUBS:
                return "Clubs";
            case HEARTS:
                return "Hearts";
            case SPADES:
                return "Spades";
            default:
                return null;
        }
    }

    public static void main(String[] args) {

        assert Objects.equals(rankToString(ACE), "Ace");
        assert Objects.equals(rankToString(DEUCE), "Deuce");
        assert Objects.equals(rankToString(THREE), "Three");
        assert Objects.equals(rankToString(FOUR), "Four");
        assert Objects.equals(rankToString(FIVE), "Five");
        assert Objects.equals(rankToString(SIX), "Six");
        assert Objects.equals(rankToString(SEVEN), "Seven");
        assert Objects.equals(rankToString(EIGHT), "Eight");
        assert Objects.equals(rankToString(NINE), "Nine");
        assert Objects.equals(rankToString(TEN), "Ten");
        assert Objects.equals(rankToString(JACK), "Jack");
        assert Objects.equals(rankToString(QUEEN), "Queen");
        assert Objects.equals(rankToString(KING), "King");

        assert Objects.equals(suitToString(DIAMONDS), "Diamonds");
        assert Objects.equals(suitToString(CLUBS), "Clubs");
        assert Objects.equals(suitToString(HEARTS), "Hearts");
        assert Objects.equals(suitToString(SPADES), "Spades");

    }
}