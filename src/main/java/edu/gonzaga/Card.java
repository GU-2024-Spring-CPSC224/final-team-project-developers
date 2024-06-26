package edu.gonzaga;

import java.util.Objects;

import javax.swing.ImageIcon;

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
    private String getSuitString() {
        switch (suit) {
            case DIAMONDS: return "d";
            case CLUBS:    return "c";
            case HEARTS:   return "h";
            case SPADES:   return "s";
            default:       return "unknown";
        }
    }

    private String getRankString() {
        return rank < 10 ? "0" + rank : String.valueOf(rank);
    }

     public String getCardImage() {
        String imageName = getSuitString() + getRankString() + ".png";
        String imagePath = "Classic/" + imageName;
        return (imagePath);
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
                return "A";
            case DEUCE:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
            default:
                return "?"; 
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


    private String suitToSymbol(int suit) {
        switch (suit) {
            case Card.DIAMONDS: return "♦";
            case Card.CLUBS:    return "♣";
            case Card.HEARTS:   return "♥";
            case Card.SPADES:   return "♠";
            default:            return "?";
        }
    }
    

    @Override
    public String toString() {
    String rank = rankToString(this.rank);
    String suitSymbol = suitToSymbol(this.suit);
    
    // Формируем строку карты вида |K of ♥|
    return String.format("|%s of %s|", rank, suitSymbol);
}






    public static void main(String[] args) {

        assert Objects.equals(rankToString(ACE), "A");
        assert Objects.equals(rankToString(DEUCE), "2");
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
