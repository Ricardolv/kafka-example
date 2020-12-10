package deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Vendas;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class VendasDeserializer implements Deserializer<Vendas> {

    @Override
    public Vendas deserialize(String topic, byte[] vendas) {
        try {
            return new ObjectMapper().readValue(vendas, Vendas.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
