package com.eric.order.generate;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_tbl.id
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_tbl.product_id
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    private Long productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_tbl.total_amount
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    private BigDecimal totalAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_tbl.status
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_tbl
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_tbl.id
     *
     * @return the value of order_tbl.id
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_tbl.id
     *
     * @param id the value for order_tbl.id
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_tbl.product_id
     *
     * @return the value of order_tbl.product_id
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_tbl.product_id
     *
     * @param productId the value for order_tbl.product_id
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_tbl.total_amount
     *
     * @return the value of order_tbl.total_amount
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_tbl.total_amount
     *
     * @param totalAmount the value for order_tbl.total_amount
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_tbl.status
     *
     * @return the value of order_tbl.status
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_tbl.status
     *
     * @param status the value for order_tbl.status
     *
     * @mbg.generated Mon Jan 10 11:42:51 CST 2022
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}