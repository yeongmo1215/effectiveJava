package study.effectivejava.item1;

public class ImmutableString {
    private final String name;

    ImmutableString(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
