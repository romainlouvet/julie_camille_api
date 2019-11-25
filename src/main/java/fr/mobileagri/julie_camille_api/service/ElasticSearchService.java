package fr.mobileagri.julie_camille_api.service;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ElasticSearchService {

  private static Logger logger = LoggerFactory.getLogger(ElasticSearchService.class);
  private RestHighLevelClient client;

  public ElasticSearchService() {
    client = new RestHighLevelClient(RestClient.builder(
        new HttpHost("localhost", 9200)));
  }

  public IndexResponse send(String index, Object object) {

    SimpleDateFormat indexFormat = new SimpleDateFormat("yyyy.MM.dd");
    IndexRequest request = new IndexRequest(index + indexFormat.format(new Date()), "_doc");
    String json;
    try {
      json = new Gson().toJson(object);
      IndexRequest indexRequest = request.source(json, XContentType.JSON);

      //Execute async request
      return client.index(indexRequest, RequestOptions.DEFAULT);
    } catch (Exception e) {
      logger.error("fail to parse json ? {} {}", e, object);
    }
    return null;
  }

  public void close() throws IOException {
    if (null != client) {
      client.close();
    }
  }
}
