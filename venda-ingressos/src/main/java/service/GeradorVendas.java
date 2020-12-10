package service;

import model.Vendas;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import serializer.VendasSerializer;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

public class GeradorVendas {

    private static Random random = new Random();
    private static long operacao =0;

    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, VendasSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VendasSerializer.class.getName());

       try ( KafkaProducer<String, Vendas> producer = new KafkaProducer<>(properties)) {

           while (true) {
               Vendas vendas = gerarVenda();
               ProducerRecord<String, Vendas> record = new ProducerRecord<>("venda-ingressos", vendas);
               producer.send(record);
               Thread.sleep(200);
           }

       }

    }

    private static Vendas gerarVenda() {
        BigDecimal valorIngresso = BigDecimal.valueOf(500);
        int quantidadeIngresso = random.nextInt(10);
        return new Vendas(random.nextLong(),
                          operacao++,
                          quantidadeIngresso,
                          valorIngresso.multiply(BigDecimal.valueOf(quantidadeIngresso)));
    }
}
