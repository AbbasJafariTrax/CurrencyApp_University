package com.example.myapplication.Model;

public class CurrencyModel {
    private int mImageResources;
    private String mNameOfCountry;
    private String mFullNameCurrency;
    private String mAbbrevateCurrency;
    private double mCurrencyOfTheCountry;

    public CurrencyModel(
            int mImageResources,
            String mNameOfCountry,
            String mFullNameCurrency,
            String mAbbrevateCurrency,
            double mCurrencyOfTheCountry
    ) {
        this.mImageResources = mImageResources;
        this.mNameOfCountry = mNameOfCountry;
        this.mFullNameCurrency = mFullNameCurrency;
        this.mAbbrevateCurrency = mAbbrevateCurrency;
        this.mCurrencyOfTheCountry = mCurrencyOfTheCountry;
    }

    public int getmImageResources() {
        return mImageResources;
    }

    public void setmImageResources(int mImageResources) {
        this.mImageResources = mImageResources;
    }

    public String getmNameOfCountry() {
        return mNameOfCountry;
    }

    public void setmNameOfCountry(String mNameOfCountry) {
        this.mNameOfCountry = mNameOfCountry;
    }

    public String getmFullNameCurrency() {
        return mFullNameCurrency;
    }

    public void setmFullNameCurrency(String mFullNameCurrency) {
        this.mFullNameCurrency = mFullNameCurrency;
    }

    public String getmAbbrevateCurrency() {
        return mAbbrevateCurrency;
    }

    public void setmAbbrevateCurrency(String mAbbrevateCurrency) {
        this.mAbbrevateCurrency = mAbbrevateCurrency;
    }

    public double getmCurrencyOfTheCountry() {
        return mCurrencyOfTheCountry;
    }

    public void setmCurrencyOfTheCountry(double mCurrencyOfTheCountry) {
        this.mCurrencyOfTheCountry = mCurrencyOfTheCountry;
    }
}
