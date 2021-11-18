package com.example.week2;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Week2Application {

	public static OkHttpClient client = new OkHttpClient();
	private static Week2Application OkHttpUtils;

	public static String getAsString(String url) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public static void main(String[] args) throws Exception {

		String url = "http://localhost:8808/test";
		String text = OkHttpUtils.getAsString(url);
		System.out.println("url: " + url + " ; response: \n" + text);

		OkHttpUtils.client = null;
	}

}
