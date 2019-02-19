package avro.UserCreation;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import java.io.File;
import java.io.IOException;

public class CreateUSerWithoutCodeGenaration {

    Schema schema = null;

    GenericRecord user1 = null;
    GenericRecord user2 = null;

    public Schema getSchema() {
        return schema;
    }

    public GenericRecord getUser1() {
        return user1;
    }

    public GenericRecord getUser2() {
        return user2;
    }

    public void createUsersWithOutCodeGeneration() throws IOException {

        schema = new Schema.Parser().parse(new File("src/main/java/avro/user.avsc"));
        user1 = new GenericData.Record(schema);
        user1.put("name", "Alyssa");
        user1.put("favorite_number", 256);

        user2 = new GenericData.Record(schema);
        user2.put("name", "Ben");
        user2.put("favorite_number", 7);
        user2.put("favorite_color", "red");

    }
}
