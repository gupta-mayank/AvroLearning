package avro;

import avro.UserCreation.CreateUSerWithoutCodeGenaration;
import avro.UserCreation.CreateUsersWithCodeGenaration;
import example.avro.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {


        try {
            CreateUsersWithCodeGenaration createUsersWithCodeGeneration = new CreateUsersWithCodeGenaration();
            createUsersWithCodeGeneration.createUsersWithCodeGenaration();
            serializeWithCodeGeneration(createUsersWithCodeGeneration);
            deserializeWithCodeGeneration();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*try {
            CreateUSerWithoutCodeGenaration createUsersWithoutCodeGenaration = new CreateUSerWithoutCodeGenaration();
            createUsersWithoutCodeGenaration.createUsersWithOutCodeGenaration();
            serializeWithOutCodegenartion(createUsersWithoutCodeGenaration);
            desearlizeWithoutCodeGeneration(createUsersWithoutCodeGenaration);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    /**
     * Serialize the inputs by using class objects
     * @param createUsers
     * @throws IOException
     */
    static void serializeWithCodeGeneration (CreateUsersWithCodeGenaration createUsers) throws IOException {

        // Serialize user1, user2 and user3 to disk
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(createUsers.getUser1().getSchema(), new File("users.avro"));
        dataFileWriter.append(createUsers.getUser1());
        dataFileWriter.append(createUsers.getUser2());
        dataFileWriter.append(createUsers.getUser3());
        dataFileWriter.close();

    }

    /**
     * Deserialize the inputs using class objects
     * @throws IOException
     */
    static void deserializeWithCodeGeneration() throws IOException {
        // Deserialize Users from disk
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = null;
        try {
            dataFileReader = new DataFileReader<User>(new File("users.avro"), userDatumReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = null;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println(user);
        }

    }

    static void serializeWithOutCodegenartion (CreateUSerWithoutCodeGenaration createUsers) throws IOException {

        File file = new File("users_nocodegeneration.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(createUsers.getSchema());
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(createUsers.getSchema(), file);
        dataFileWriter.append(createUsers.getUser1());
        dataFileWriter.append(createUsers.getUser2());
        dataFileWriter.close();

    }

    static void desearlizeWithoutCodeGeneration(CreateUSerWithoutCodeGenaration createUsers) throws IOException {
        // Deserialize users from disk
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(createUsers.getSchema());
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File("users_nocodegeneration.avro"), datumReader);
        GenericRecord user = null;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println(user);

        }
    }

}
