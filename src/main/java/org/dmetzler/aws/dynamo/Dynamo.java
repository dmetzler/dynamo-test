package org.dmetzler.aws.dynamo;

import java.util.UUID;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class Dynamo {

    public static void main(String... args) {
        while (true) {
            try {
                AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                        .withClientConfiguration(new ClientConfiguration().withMaxErrorRetry(0))
                        .build();
                DynamoDB dynamo = new DynamoDB(client);
                Table table = dynamo.getTable("test");

                Item item = new Item().withPrimaryKey("id", UUID.randomUUID().toString()).withString("hello", "world");
                table.putItem(item);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.exit(0);
            }
        }

    }
}
