import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class RestRed {
  /*  Rpos Proff   =   500-07.0PL
      NT           =   430-04.0SL
      client       =   500-07.0UL  */

    private String leadCode="";
    private static final Logger logger= LogManager.getLogger(RestRed.class.getName());

    @Test
    public void createBaseProductOrder(){
        Map<String,String> creds=getKeyAndAuthToken();
        createLead(creds.get("key"),creds.get("authtoken"));
        createAddOns(creds.get("key"),creds.get("authtoken"),"500-07.0PL");
        createAddOns(creds.get("key"),creds.get("authtoken"),"430-04.0SL");
        createAddOns(creds.get("key"),creds.get("authtoken"),"500-07.0UL");
    }

    private void createAddOns(String key,String auth,String skew){
        Response response=given()
                .contentType("application/json")
                .header("key",key)
                .header("authtoken",auth)
                .header("currentversion","")
                .body(createOrderJson(leadCode,skew).toString())
                .when()
                .post("https://labtest.gofrugal.com/ismile/order_conversion_submit.php")
                .then()
                .extract()
                .response();
        logger.info("Order number for "+skew+" : "+response.jsonPath().getString("order_no"));
        System.out.println("Order number ="+response.jsonPath().getString("order_no"));
    }

    private void createLead(String key,String auth){
        Response response=given()
                .contentType("application/json")
                .header("key",key)
                .header("authtoken",auth)
                .header("currentversion","")
                .body(leadJson().toString())
                .when()
                .post("https://labtest.gofrugal.com/ismile/new_lead.php")
                .then()
                .extract()
                .response();
        leadCode=response.jsonPath().getString("lead_code");
        logger.info("Created Lead ID : "+leadCode);
        System.out.println("lead code "+leadCode);
    }

    private Map<String, String> getKeyAndAuthToken(){
        Map<String,String> map=new HashMap<String,String>();
        JSONObject js=new JSONObject()
                .put("type","employee")
                .put("app_version","2.6.0.0")
                .put("user","kalaiselvan.a")
                .put("password","ebdb115480737d73c6437d85a004d020")
                .put("otp","12345");
        Response response= given()
                .contentType("application/json")
                .body(js.toString())
                .when()
                .post("https://labtest.gofrugal.com/ismile/ismile_login.php")
                .then()
                .extract()
                .response();
        map.put("key",response.path("key").toString());
        map.put("authtoken",response.path("authtoken").toString());
        return map;
    }

    private JSONObject leadJson() {
        String timeLog = new SimpleDateFormat("dd_HH_mm_ss").format(Calendar.getInstance().getTime());
        String timeLog1 = new SimpleDateFormat("ddHHmmss").format(Calendar.getInstance().getTime());
        String shop_name = "AutoLeadRs" + timeLog + "";
        String cont_no = "77" + timeLog1;

        JSONArray dtl = new JSONArray();
        dtl.put(new JSONObject().put("contact_type", "1")
                                .put("contact_info", cont_no));
        dtl.put(new JSONObject().put("contact_type", "4")
                                .put("contact_info", cont_no + "@gmail.com"));

        JSONArray array = new JSONArray();
        array.put(new JSONObject().put("contact_person_name", "1")
                .put("contact_dtl", dtl)
                .put("contact_designation", "1")
                .put("contact_group", "1"));
        return new JSONObject()
                .put("shop_name", shop_name)
                .put("vertical", "8")
                .put("lead_address", new JSONObject()
                        .put("pin_code", "601204")
                        .put("addr_street", "1")
                        .put("location_name", "1"))
                .put("contact_list", array)
                .put("submit_type", "1");
    }


    private JSONObject createOrderJson(String leadCode,String productSkew){
        String timeLog2 = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        JSONArray array=new JSONArray();
        array.put("0");
        JSONArray licDetails=new JSONArray();
        licDetails.put(new JSONObject()
                .put("product",productSkew)
                .put("quantity","1")
                .put("selling_price","12711.86")
                .put("discount_value","0")
                .put("discount_percent","0")
                .put("net_amount","12711.86")
                .put("ref_server","[]")
                .put("list_price","12711.86")
                .put("sales_tax_percent","null")
                .put("service_tax_percent","null"));

        return new JSONObject()
                .put("quotation_no","356076718000001")
                .put("action_type","convert_to_order")
                .put("cust_id",leadCode)
                .put("order_no","9"+timeLog2)
                .put("splitable_order","0")
                .put("tax_mode","4")
                .put("currency_type","INR")
                .put("order_type",array)
                .put("quotation_license_order",licDetails)
                .put("order_amount","15000")
                .put("payment_type","2-0.00")
                .put("collection_date","2018-02-15")
                .put("incentive_emp","0-0-0")
                .put("approval_day","30")
                .put("required_product_delivery","Yes")
                .put("product_delivery_by","1")
                .put("product_consultant","1112")
                .put("questions",new JSONObject()
                        .put("292","no")
                        .put("304","2018-02-01 00:01")
                        .put("305","pp")
                        .put("306","do")
                        .put("coupon_list","2")
                        .put("comments","sc"));
    }
}
