package mapper;

import user.User;

import java.util.List;

public interface userinfoMapper {
    List<User> selectAll();
    void insertOne(User user);

}
