package com.anish.syrus2020;

public class Cases {
    private String case_details,case_assigned,type,mobile,signature_link;
    public Cases() {
    }

    public Cases(String case_details, String case_assigned, String type, String mobile, String signature_link) {
        this.case_details = case_details;
        this.case_assigned = case_assigned;
        this.type = type;
        this.mobile = mobile;
        this.signature_link = signature_link;
    }

    public String getCase_details() {
        return case_details;
    }

    public void setCase_details(String case_details) {
        this.case_details = case_details;
    }

    public String getCase_assigned() {
        return case_assigned;
    }

    public void setCase_assigned(String case_assigned) {
        this.case_assigned = case_assigned;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSignature_link() {
        return signature_link;
    }

    public void setSignature_link(String signature_link) {
        this.signature_link = signature_link;
    }


}
