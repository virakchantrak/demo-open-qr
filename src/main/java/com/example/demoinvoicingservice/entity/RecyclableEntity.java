package com.example.demoinvoicingservice.entity;

public interface RecyclableEntity<T> {
    T getStatus();

    void setStatus(T var1);
}
