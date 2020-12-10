package serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Vendas;
import org.apache.kafka.common.serialization.Serializer;

public class VendasSerializer implements Serializer<Vendas> {

    @Override
    public byte[] serialize(String topic, Vendas vendas) {

        try {
            return new ObjectMapper().writeValueAsBytes(vendas);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
