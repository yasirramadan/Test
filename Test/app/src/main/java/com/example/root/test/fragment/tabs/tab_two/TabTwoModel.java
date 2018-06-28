package com.example.root.test.fragment.tabs.tab_two;

import android.databinding.ObservableField;

import java.io.Serializable;

public class TabTwoModel implements Serializable {

    /**
     * mobile number to recive money from
     */
    private ObservableField<String> mobileNumber = new ObservableField<>();

    /**
     * amount to be sent
     */
    private ObservableField<String> amount = new ObservableField<>();

    /**
     * password
     */
    private ObservableField<String> password = new ObservableField<>();


    public ObservableField<String> getMobileNumber() {
        return mobileNumber;
    }

    public ObservableField<String> getAmount() {
        return amount;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public void setMobileNumber(ObservableField<String> mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setAmount(ObservableField<String> amount) {
        this.amount = amount;
    }

    public void setPassword(ObservableField<String> password) {
        this.password = password;
    }
}
