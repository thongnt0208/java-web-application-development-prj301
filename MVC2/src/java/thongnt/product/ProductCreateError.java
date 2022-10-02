/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thongnt.product;

/**
 *
 * @author trung
 */
public class ProductCreateError {

    private String skuPatternInvalid;
    private String skuIsExisted;
    private String nameLengthError;
    private String priceMinusError;

    public ProductCreateError() {
    }

    public ProductCreateError(String skuPatternInvalid, String skuIsExisted, String nameLengthError, String priceMinusError) {
        this.skuPatternInvalid = skuPatternInvalid;
        this.skuIsExisted = skuIsExisted;
        this.nameLengthError = nameLengthError;
        this.priceMinusError = priceMinusError;
    }

    public String getSkuPatternInvalid() {
        return skuPatternInvalid;
    }

    public void setSkuPatternInvalid(String skuPatternInvalid) {
        this.skuPatternInvalid = skuPatternInvalid;
    }

    public String getSkuIsExisted() {
        return skuIsExisted;
    }

    public void setSkuIsExisted(String skuIsExisted) {
        this.skuIsExisted = skuIsExisted;
    }

    public String getNameLengthError() {
        return nameLengthError;
    }

    public void setNameLengthError(String nameLengthError) {
        this.nameLengthError = nameLengthError;
    }

    public String getPriceMinusError() {
        return priceMinusError;
    }

    public void setPriceMinusError(String priceMinusError) {
        this.priceMinusError = priceMinusError;
    }
    
    
}