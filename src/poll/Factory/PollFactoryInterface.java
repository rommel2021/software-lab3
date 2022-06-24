package poll.Factory;

import poll.Poll;

public interface PollFactoryInterface {
    Poll create(String type);
}
