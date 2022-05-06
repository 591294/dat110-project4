package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		OkHttpClient client = new OkHttpClient();

		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody reqBody = RequestBody.create(JSON, message);
		Request request = new Request.Builder().url("http://localhost:8080" + logpath).post(reqBody).build();
		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String codepath = "/accessdevice/code";

	public AccessCode doGetAccessCode() {

		AccessCode code = null;

		// TODO: implement a HTTP GET on the service to get current access code
		Gson gson = new Gson();

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url("http://localhost:8080" + codepath).get().build();

		try (Response response = client.newCall(request).execute()) {

			code.setAccesscode(gson.fromJson(response.body().toString(), int[].class));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return code;
	}
}
