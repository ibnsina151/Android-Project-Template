package com.example.ibnsina.socialtrafficmonitoring;

import android.widget.Toast;

/**
 * Created by ibnsina on 11/22/17.
 */


public class Required_data_Set_Get extends HttpHandler {

    String url = "";
    String response = "";
    Exception excep;
    //Connection con = new Connection();

    public String get_all_recieps_() {
        try {
            url = Server_path.Main + "?operasion=view";
            System.out.println("URL Tampil Biodata: " + url);
            response = makeServiceCall(url);
        } catch (Exception e) {
        }
        return response;
    }

//https://ibnsinam12.000webhostapp.com/RosoiSala/RosoiSala_server.php?operasi=insert&name=Munim
//https://ibnsinam12.000webhostapp.com/RosoiSala/server.php?operasi=insert&name=Unknown


    public String registration(String androidid, String name, String photo,int gender,String mobile,String password) {
        try {
            url = Server_path.Main + "?operasion=registration&androidid="+androidid+"&name=" +name+ "&photo="+photo+"&gender="+gender+"&mobile="+mobile+"&password="+password+"";
            System.out.println("URL Insert User_login : " + url);
            response = makeServiceCall(url);
        } catch (Exception e) {
            excep =e;
        }
        return response;
    }

    public String User_Login(String mobile,String password) {
        try {
            url = Server_path.Main + "?operasion=loginuser&mobile=" + mobile + "&password="+password+"";
            System.out.println("URL Insert User_login : " + url);
            response = makeServiceCall(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getmagflag(int id) {
        try {
            url = Server_path.Main +"?areaid="+id;
            System.out.println("URL Get Flag : " + url);
            response = makeServiceCall(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String updateBiodata(String id, String nama, String alamat) {
        try {
            url = Server_path.Main + "?operasion=update&id=" + id + "&nama=" + nama + "&alamat=" + alamat;
            System.out.println("URL Insert Biodata : " + url);
            response = makeServiceCall(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String deleteBiodata(int id) {
        try {
            url = Server_path.Main + "?operasion=delete&id=" + id;
            System.out.println("URL Insert Biodata : " + url);
            response = makeServiceCall(url);
        } catch (Exception e) {
        }
        return response;
    }
}

