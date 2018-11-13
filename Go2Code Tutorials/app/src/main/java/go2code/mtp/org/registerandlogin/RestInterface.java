package go2code.mtp.org.registerandlogin;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


public interface RestInterface {
    //chnage your IP here if you working on local
   // String url = "http://192.168.43.175/register-login/v1";
    //For Hosting give the complete path before index.php
    String url = "http://127.0.0.1/register-login/v1";

    @FormUrlEncoded
    @POST("/login")
    void Login(@Field("email") String email,
               @Field("pass") String pass, Callback<LoginModel> cb);

    @FormUrlEncoded
    @POST("/signup")
    void SignUp(@Field("name") String name, @Field("email") String email,
                @Field("pass") String pass, Callback<LoginModel> pm);

}

