package com.esa.bmap.service.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.esa.bmap.BmapServiceTestContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { BmapServiceTestContext.class })
public class EclipseCheTest {
	@Value("${eclipseche.password}")
	private String eclipseChePwd;

	/**
	 * clear data base before starts tests
	 */
	@Before
	public void initData() {
		// We erase the data base
	}

	/**
	 * clear data base after starts tests
	 */
	@After
	public void clearDb() {
	}

	/**
	 * We check if we can get a place by its id
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 */
	@Test
	@Ignore
	public void getToken() throws ClientProtocolException, IOException {

		HttpUriRequest request = RequestBuilder.post()
				.setUri("http://10.29.150.153:5050/auth/realms/che/protocol/openid-connect/token")
				.setHeader("Content-Type", "application/x-www-form-urlencoded").setHeader("Accept", "application/json")
				.addParameter("username", "kosted").addParameter("client_id", "che-public")
				.addParameter("password", eclipseChePwd).addParameter("grant_type", "password").build();
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();

		// System.out.println(EntityUtils.toString(entity));

		String result = EntityUtils.toString(entity);

		System.out.println("le result est = " + result);
		// We convert the json we get into a json object
		JSONObject myjson;
		try {
			myjson = new JSONObject(result);
			String token = myjson.get("access_token").toString();
			System.out.println("Le token est " + token);

			// On fait une la requete qui permet de récupérer les workspaces de
			// l'utilisateur
			String url = "http://10.29.150.153:8088/api/workspace?skipCount=0&maxItems=30&token=" + token;

			request = RequestBuilder.get().setUri(url).setHeader("Content-Type", "application/x-www-form-urlencoded")
					.setHeader("Accept", "application/json").build();
			client = HttpClientBuilder.create().build();
			response = client.execute(request);
			entity = response.getEntity();

			// We convert the data to string then to a json array
			result = EntityUtils.toString(entity);
			System.out.println(result);

			List<JSONObject> list = new ArrayList<JSONObject>();
			try {
				int i;
				JSONArray array = new JSONArray(result);
				int workspaceLenght = array.length();
				System.out.println("La size est " + array.length());
				for (i = 0; i < workspaceLenght; i++) {
					myjson = new JSONObject(array.getJSONObject(i).toString());
					String idWorkspace = myjson.get("id").toString();
					System.out.println("L'id du workspace est " + idWorkspace);
				}

			} catch (JSONException e) {
				System.out.println(e.getMessage());
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
