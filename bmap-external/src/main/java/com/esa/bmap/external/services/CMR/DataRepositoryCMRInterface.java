package com.esa.bmap.external.services.CMR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Collections;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.GranuleCriteria;

public interface DataRepositoryCMRInterface {

	public abstract void downloadTheDataFromS3(String TargetUrl, String Filename)
			throws BmapException;

	public abstract void downloadTheData(String TargetUrl, String Filename)
			throws BmapException;

	public abstract boolean deleteAllGranuleInCollection(String CollectiondataSetId)
			throws BmapException;

	public abstract void save(com.esa.bmap.model.Granule bmaapGranule) throws BmapException;

	public abstract com.esa.bmap.external.model.cmr.collections.Collection getCollectionCMR(String collectionShortName) throws BmapException;

	public abstract Collection<com.esa.bmap.model.Granule> findByCriteria(GranuleCriteria dataCriteria) throws BmapException;

}
