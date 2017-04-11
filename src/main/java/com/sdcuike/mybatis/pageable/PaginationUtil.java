package com.sdcuike.mybatis.pageable;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by beaver on 2017/4/12.
 */
public final class PaginationUtil {
    
    public static HttpHeaders generatePaginationHttpHeaders(Page<?> page, String baseUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + page.getTotalElements());
        String urlConnector = "?";
        if (baseUrl.contains("?")) {
            urlConnector = "&";
        }
        String link = "";
        if ((page.getNumber() + 1) < page.getTotalPages()) {
            link = getNextUrl(page, baseUrl, urlConnector);
        }
        // prev link
        if ((page.getNumber()) > 0) {
            link += getPrevUrl(page, baseUrl, urlConnector);
        }
        // last and first link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += getLastUrl(page, baseUrl, urlConnector, lastPage);
        link += getFirstUrl(page, baseUrl, urlConnector);
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }
    
    private static String getFirstUrl(Page<?> page, String baseUrl, String urlConnector)  {
        try {
            return "<" +
                    (new URI(baseUrl + urlConnector + "page=" + 0 +
                            "&size=" + page.getSize())).toString() +
                    ">; rel=\"first\"";
        } catch (URISyntaxException e) {
            throw  new RuntimeException(e);
        }
    }
    
    private static String getLastUrl(Page<?> page, String baseUrl, String urlConnector, int lastPage)   {
        try {
            return "<" +
                    (new URI(baseUrl + urlConnector + "page=" + lastPage +
                            "&size=" + page.getSize())).toString() +
                    ">; rel=\"last\",";
        } catch (URISyntaxException e) {
            throw  new RuntimeException(e);
        }
    }
    
    private static String getPrevUrl(Page<?> page, String baseUrl, String urlConnector)   {
        try {
            return "<" +
                    (new URI(baseUrl + urlConnector + "page=" + (page.getNumber() - 1) +
                            "&size=" + page.getSize())).toString()
                    + ">; rel=\"prev\",";
            
        } catch (URISyntaxException e) {
            throw  new RuntimeException(e);
        }
    }
    
    private static String getNextUrl(Page<?> page, String baseUrl, String urlConnector)  {
        try {
            return "<" +
                    (new URI(baseUrl + urlConnector + "page=" + (page.getNumber() + 1) +
                            "&size=" + page.getSize())).toString()
                    + ">; rel=\"next\",";
            
        } catch (URISyntaxException e) {
            throw  new RuntimeException(e);
        }
    }
    
}
