package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Heri on 7/3/2018.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterCard {
//    private int item_id;
//    private int price;
//    private String date;
//    private String item_code;
//    private String item_name;
//    private Boolean confirmation_dh, confirmation_pm, confirmation_fc, confirmation_gm;

    private int request_header_id;
    private String requested_by;
    private String request_date;
    private String number;
    private int counter;
    private String rh_desc;
    private String type;
    //1=dh,2=pm,3=fc,4=gm
    private String app_status1;
    private String app_date1;
    private String app_desc1;
    private String app_by1;
    private String app_status2;
    private String app_date2;
    private String app_desc2;
    private String app_by2;
    private String app_status3;
    private String app_date3;
    private String app_desc3;
    private String app_by3;
    private String app_status4;
    private String app_date4;
    private String app_desc4;
    private String app_by4;
    private int total_trans;
    private int discount;
    private int ppn;
    private int grand_total;

    ///////////Details//////
    private int details;





//    public int getItem_id() {
//        return item_id;
//    }
//
//    public void setItem_id(int item_id) {
//        this.item_id = item_id;
//    }
//
//    public String getItem_code() {
//        return item_code;
//    }
//
//    public void setItem_code(String item_code) {
//        this.item_code = item_code;
//    }
//
//    public String getItem_name() {
//        return item_name;
//    }
//
//    public void setItem_name(String item_name) {
//        this.item_name = item_name;
//    }
//
//    public String getDate()
//    {
//        return this.date;
//    }
//
//    public void setDate(String date)
//    {
//        this.date = date;
//    }
//
//    public int getPrice()
//    {
//        return this.price;
//    }
//
//    public void setPrice(int price)
//    {
//       this.price = price;
//    }
//
//    public Boolean getConfirmation_dh()
//    {
//        return this.confirmation_dh;
//    }
//    public Boolean getConfirmation_pm()
//    {
//        return this.confirmation_pm;
//    }
//    public Boolean getConfirmation_fc()
//    {
//        return this.confirmation_fc;
//    }
//    public Boolean getConfirmationS_gc()
//    {
//        return this.confirmation_gm;
//    }
//
//    public void setConfirmation_dh(Boolean x)
//    {
//        this.confirmation_dh = x;
//    }
//
//    public void setConfirmation_pm(Boolean x)
//    {
//        this.confirmation_pm = x;
//    }
//
//    public void setConfirmation_fc(Boolean x)
//    {
//        this.confirmation_fc = x;
//    }
//
//    public void setConfirmation_gm(Boolean x)
//    {
//        this.confirmation_gm = x;
//    }
//
//    public MasterCard(int item_id, int price) {
//        this.item_id = item_id;
//        this.price = price;
//    }
}

