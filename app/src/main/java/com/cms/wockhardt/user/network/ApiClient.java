//package com.cms.wockhardt.user.network;
//
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//import static com.cms.wockhardt.user.application.AppConstants.BASE_URL;
//
//public class ApiClient {
//
//    private static Retrofit retrofit = null;
//
//
//    public static Retrofit getClient() {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(30, TimeUnit.SECONDS)
//                .addInterceptor(logging)
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .build();
//
//
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
//}
