package com.assignment1.item;

/** Class as blueprint for Item Entity. */
@SuppressWarnings("PMD.DataClass")
public class ItemEntity {

  /** Name of item. */
  private String name;
  /** type(raw, manufactured, imported) of item. */
  private String type;
  /** quantity of item. */
  private int quantity;
  /** price of item. */
  private double price;
  /** tax of item. */
  private double tax;

  /** Constructor initializing default values. */
  public ItemEntity() {
    name = "";
    type = "";
    quantity = 1;
    price = 0.0;
    tax = 0.0;
  }

  /** Overloaded Constructor. */
  public ItemEntity(final String name, final double price, final int quantity, final String type) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.type = type;
    this.tax = 0;
  }

  /** Getter. */
  public String getName() {
    return name;
  }

  /** Setter. */
  public void setName(final String name) {
    this.name = name;
  }

  /** Getter. */
  public String getType() {
    return type;
  }

  /** Setter. */
  public void setType(final String type) {
    this.type = type;
  }

  /** Getter. */
  public double getPrice() {
    return price;
  }

  /** Setter. */
  public void setPrice(final double price) {
    this.price = price;
  }

  /** Getter. */
  public int getQuantity() {
    return quantity;
  }

  /** Setter. */
  public void setQuantity(final int quantity) {
    this.quantity = quantity;
  }

  /** Getter. */
  public double getTax() {
    return tax;
  }

  /** Setter. */
  public void setTax(final double tax) {
    this.tax = tax;
  }

  /** Overloading toString(). */
  @Override
  public String toString() {
    return name + price + quantity + type;
  }

}
