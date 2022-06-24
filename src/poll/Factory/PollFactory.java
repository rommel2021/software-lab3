package poll.Factory;

import poll.BusinessVoting;
import poll.DinnerOrder;
import poll.Election;
import poll.Poll;

public class PollFactory implements PollFactoryInterface{

    @Override
    public Poll create(String type) {
        switch(type){
            case "BusinessVoting":
                return new BusinessVoting();
            case "DinnerOrder":
                return new DinnerOrder();
            case "Election":
                return new Election();
            default:
                throw new RuntimeException("the type is wrong");
        }
    }
}
