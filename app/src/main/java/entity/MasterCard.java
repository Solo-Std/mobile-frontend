package entity;

/**
 * Created by Heri on 7/3/2018.
 */

public class MasterCard {
    private int item_id;
    private int price;
    private String date;
    private String item_code;
    private String item_name;
    private Boolean confirmation_dh, confirmation_pm, confirmation_fc, confirmation_gm;

    //1=dh,2=pm,3=fc,4=gm
    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDate()
    {
        return this.date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getPrice()
    {
        return this.price;
    }

    public void setPrice(int price)
    {
       this.price = price;
    }

    public Boolean getConfirmation_dh()
    {
        return this.confirmation_dh;
    }
    public Boolean getConfirmation_pm()
    {
        return this.confirmation_pm;
    }
    public Boolean getConfirmation_fc()
    {
        return this.confirmation_fc;
    }
    public Boolean getConfirmationS_gc()
    {
        return this.confirmation_gm;
    }

    public void setConfirmation_dh(Boolean x)
    {
        this.confirmation_dh = x;
    }

    public void setConfirmation_pm(Boolean x)
    {
        this.confirmation_pm = x;
    }

    public void setConfirmation_fc(Boolean x)
    {
        this.confirmation_fc = x;
    }

    public void setConfirmation_gm(Boolean x)
    {
        this.confirmation_gm = x;
    }


}
