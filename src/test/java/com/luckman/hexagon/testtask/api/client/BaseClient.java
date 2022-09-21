package com.luckman.hexagon.testtask.api.client;

import okhttp3.Response;

/**
 *
 * @param <D> - dto class. Currently it is not used, because we have only getAll() scenario.
 * @param <DA> - set of dto classes (for example, in getAll response)
 */
public class BaseClient<D, DA> {
    private String apiPath;
    private Class<D> dClass;
    private Class<DA> daClass;
    private RestUtility restUtility;

    public BaseClient(String apiPath, Class<D> dtoClass, Class<DA> dtoSetClass) {
        this.apiPath = apiPath;
        this.dClass = dtoClass;
        this.daClass = dtoSetClass;
        restUtility = new RestUtility();    // but it also can be singletone
    }

    public DA getAll() {
        return restUtility.getForObject(apiPath, daClass);
    }

    public Response getAllAsResponse() {
        return restUtility.getForResponse(apiPath);
    }

    public DA getPage(int page) {
        return restUtility.getForObject(apiPath + "?page=" + page, daClass);
    }

    // create, get one, and other methods
    // public D getOne()

}
