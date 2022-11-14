package Player;

import java.util.Optional;

public class Player {
    private Optional<String> name;
    private Optional<Integer> order;
    private Optional<Integer> points;

    public Player(Optional<String> name){
        this.name=name;
        this.points=Optional.ofNullable(0);
        this.order=Optional.empty();
    }

    public Optional<String> getName(){
        return name;
    }
    public Optional<Integer> getOrder(){
        return order;
    }
    public Optional<Integer> getPoints(){
        return points;
    }
    public void setOrder(Optional<Integer> order){
        this.order=order;
    }
    public void setPoints(Optional<Integer> points){
        this.points=points;
    }
}
