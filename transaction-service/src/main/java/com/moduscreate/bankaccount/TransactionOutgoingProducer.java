package com.moduscreate.bankaccount;

import java.util.UUID;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;

@ApplicationScoped
public class TransactionOutgoingProducer {

//  Sempre que eu mando  uma mensagem do resource para o canal transaction

    private final Logger log = Logger.getLogger(TransactionOutgoingProducer.class.getName());

//  Pega ela por parametro e gera um random uuid e uma kafkarecord
//  Vai emitir para o setor cluster do kafka, para que os subscribes possam conversar entre si


    @Incoming("transactions")
    @Outgoing("transactions")
    public KafkaRecord<String, Transaction> produce(Transaction transaction) {
        log.info("Sending transaction with description " + transaction.description);
        return KafkaRecord.of(UUID.randomUUID().toString(), transaction);
    }
    
}