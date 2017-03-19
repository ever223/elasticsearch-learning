package com.xiaogan.elasticsearch.service;

import com.xiaogan.elasticsearch.domain.Account;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoo_gan
 * @date 2017-03-19
 */
@Service
public class AccountService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private static final String INDEX_NAME = "bank";
    private final static String TYPE_NAME = "accounts";

    @Autowired
    private Client client;

    public SearchResponse search(String id) {
        SearchResponse response = client.prepareSearch(INDEX_NAME)
                .setTypes(TYPE_NAME)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("_id", id))
                .setFrom(0)
                .setSize(60)
                .setExplain(true)
                .execute()
                .actionGet();
        return response;
    }

    public void insert(Account account) {
        if (account == null) {
            return;
        }

        String json = JSONObject.fromObject(account).toString();
        IndexResponse response = client.prepareIndex(INDEX_NAME, TYPE_NAME)
                .setSource(json)
                .get();
    }

    public SearchResponse findAll() {
        SearchResponse response = client.prepareSearch(INDEX_NAME)
                .setTypes(TYPE_NAME)
                .execute()
                .actionGet();
        return response;
    }

    public String find(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }

        GetResponse response = client.prepareGet()
                .setIndex(INDEX_NAME)
                .setType(TYPE_NAME)
                .setId(id)
                .get();

        return response.getSourceAsString();
    }

    public boolean update(String id, Account account) {
        if (StringUtils.isEmpty(id) || account == null) {
            return false;
        }

        String json = JSONObject.fromObject(account).toString();

        UpdateResponse response = client.prepareUpdate()
                .setIndex(INDEX_NAME)
                .setType(TYPE_NAME)
                .setId(id)
                .setDoc(json)
                .get();

        return true;
    }

    public boolean delete(String id) {
        if (StringUtils.isEmpty(id)) {
            return false;
        }

        DeleteResponse response = client.prepareDelete()
                .setIndex(INDEX_NAME)
                .setType(TYPE_NAME)
                .setId(id)
                .get();

        return true;
    }
}
