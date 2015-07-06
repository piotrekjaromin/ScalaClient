import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.node.NodeBuilder.*;
import static org.elasticsearch.common.xcontent.XContentFactory.*;


public class ClientTest {
    Node node;
    Client client;
    ClientTest(){
        node = nodeBuilder().client(true).clusterName("pjaromin").node();
        client = node.client();
    }

    public void add(int id){
        IndexResponse indexResponse; //= null;
        try {
            indexResponse = client.prepareIndex("index" , "typ", "" + id)  //index, type
                    .setSource(jsonBuilder()
                                    .startObject()
                                    .field("user", "user" + id )
                                    .field("postDate", new Date())
                                    .field("message", "message No. 2" + id)
                                    .endObject()
                    )
                    .execute()
                    .actionGet();
        } catch (IOException e) {
            System.out.print("blad indexresponse");
        }
    }

    public void get(int id){
        GetResponse getResponse = client.prepareGet("index", "typ", "" + id)
                .execute()
                .actionGet();
    }

    public void delete(int id){
        DeleteResponse response = client.prepareDelete("index", "typ" , "" + id)
                .execute()
                .actionGet();
    }



    public void update(int id, String id2){
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("index");
        updateRequest.type("typ");
        updateRequest.id(""+id);
        try {
            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("user", id2)
                    .endObject());

            client.update(updateRequest).get();

        } catch (IOException e) {
            System.out.println("blad updateRequest");
        }catch (InterruptedException e) {
            System.out.println("blad updateRequest2");
        } catch (ExecutionException e) {
            System.out.println("blad updateRequest3");
        }

    }

    public void end(){
        node.close();
    }

}
