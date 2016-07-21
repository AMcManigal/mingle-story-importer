package dataobject;

public class Card {

    private CardType type;
    private int cardNumber = 0;
    private String name;
    private String description;

    public Card ofType(CardType type){
        this.type = type;
        return this;
    }
    public CardType type() {return this.type;}

    public Card withCardNumber(int number){
        this.cardNumber = number;
        return this;
    }
    public int cardNumber() {return this.cardNumber;}

    public Card withName(String name){
        this.name = name;
        return this;
    }
    public String name(){return this.name;}

    public Card withDescription(String description){
        this.description = description;
        return this;
    }
    public String description(){return this.description;}

}
