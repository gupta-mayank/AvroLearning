package avro.UserCreation;

import example.avro.User;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.io.File;
import java.io.IOException;

public class CreateUsersWithCodeGenaration {

    User user1 = null;
    User user2 = null;
    User user3 = null;


    public User getUser1() {
        return user1;
    }
    public User getUser2() {
        return user2;
    }
    public User getUser3() {
        return user3;
    }

    public void createUsersWithCodeGenaration() {

        user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);

        user2 = new User("Ben", 7, "red");

        user3 = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(null)
                .build();
    }
}
